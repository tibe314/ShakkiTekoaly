/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.model;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

public class GameState {
    public String id;
    
    public String playingBlack;
    public String playingWhite;
    
    public ArrayList<String> moves;
    
    /**
     * Parses a full game state from a gameFull JSON object
     * <b>Note:</b> Use only to gain the initial game state,
     * game state should be updated via updateFromJson()
     * @param json
     * @return A full, initial game state
     */
    public static GameState parseFromJson(String json) {
        GameState gameState = new GameState();
        
        JSONObject jsonGameState = new JSONObject(json);
        
        if (jsonGameState.getString("type").equals("gameFull")) {
            gameState.id = jsonGameState.getString("id");
            
            gameState.playingWhite = jsonGameState
                    .getJSONObject("white").getString("id");
            gameState.playingBlack = jsonGameState
                    .getJSONObject("black").getString("id");
            
            String[] moves = jsonGameState
                    .getJSONObject("state").getString("moves").split(" ");
            
            gameState.moves = new ArrayList<>(Arrays.asList(moves));
        }
        
        return gameState;
    }
    
    /**
     * Update a GameState object from JSON
     * @param json 
     */
    public void updateFromJson(String json) {
        JSONObject jsonGameState = new JSONObject(json);
        
        if (jsonGameState.getString("type").equals("gameFull")) {
            this.id = jsonGameState.getString("id");
            
            String[] moves = jsonGameState
                    .getJSONObject("state").getString("moves").split(" ");
            
            this.moves = new ArrayList<>(Arrays.asList(moves));
        } else if (jsonGameState.getString("type").equals("gameState")) {
            String[] moves = jsonGameState.getString("moves").split(" ");
            
            this.moves = new ArrayList<>(Arrays.asList(moves));
        } else {
            // This would only have chat stuff, we probably don't need it.
        }
    }
}
