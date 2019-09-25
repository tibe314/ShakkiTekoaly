/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.model;

import java.util.ArrayList;
import java.util.Arrays;
import chess.engine.Engine;

import org.json.JSONObject;

/**
 * Stores the state of a game of chess
 * Provides access to currently available moves
 */
public class GameState {
    
    public String id;
    
    public String playingBlack;
    public String playingWhite;
    
    public ArrayList<String> moves;
    
    public Engine engine = new Engine();
    
    public GameState() {
        this.moves = new ArrayList();
    }

    /**
     * Parses a full game state from a gameFull JSON object
     * <b>Note:</b> Use only to gain the initial game state, game state should
     * be updated via updateFromJson()
     *
     * @param json String of JSON data according to https://lichess.org/api#operation/botGameStream
     * @return A full, initial game state
     */
    public static GameState parseFromJson(String json) {
        GameState gameState = new GameState();
        
        JSONObject jsonGameState = new JSONObject(json);
        
        if (jsonGameState.getString("type").equals("gameFull")) {
            gameState.id = jsonGameState.getString("id");
            
            gameState.playingWhite = jsonGameState
                    .getJSONObject("white").optString("id");
            gameState.playingBlack = jsonGameState
                    .getJSONObject("black").optString("id");
            
            String[] moves = jsonGameState
                    .getJSONObject("state").getString("moves").split(" ");
            
            gameState.moves = new ArrayList<>(Arrays.asList(moves));
        }
        
        return gameState;
    }

    /**
     * Update a GameState object from JSON
     *
     * @param json String of JSON data according to https://lichess.org/api#operation/botGameStream
     */
    public void updateFromJson(String json) {
        JSONObject jsonGameState = new JSONObject(json);
        
        if (jsonGameState.getString("type").equals("gameFull")) {
            this.id = jsonGameState.getString("id");
            
            this.playingWhite = jsonGameState
                    .getJSONObject("white").optString("id");
            this.playingBlack = jsonGameState
                    .getJSONObject("black").optString("id");
            
            String[] moves;
            
            if (!jsonGameState.getJSONObject("state").getString("moves").isEmpty()) {
                moves = jsonGameState
                        .getJSONObject("state").getString("moves").trim().split(" ");
            } else {
                moves = new String[0];
            }
            
            this.moves = new ArrayList<>(Arrays.asList(moves));
            
            for (String i : this.moves) {
                System.out.println(i);
            }
            
        } else if (jsonGameState.getString("type").equals("gameState")) {
            String[] moves = jsonGameState.getString("moves").split(" ");
            
            this.moves = new ArrayList<>(Arrays.asList(moves));
        } else {
            // This would only have chat stuff, we probably don't need it.
        }
        
        parseLatestMove();
    }
    
    /**
     * Parses a move in UCI move into the chess engine's move data type and
     * updates the engine's board state
     */
    public void parseLatestMove() {
        this.engine = new Engine();
        // We play all of the moves onto a new board to ensure a previously
        // started game can be resumed correctly, inefficient but it works
        this.engine = new Engine();
        if (!this.moves.isEmpty()) {
            this.moves.forEach(moveString -> {
                String startingString = moveString.substring(0, 2).toUpperCase();
                String endingString = moveString.substring(2, 4).toUpperCase();
                String promoteString = moveString.length() > 4 ? moveString
                        .substring(4).toUpperCase() : "".toUpperCase();
                this.engine.setMove(startingString, endingString, promoteString);
            });
        }
    }
}
