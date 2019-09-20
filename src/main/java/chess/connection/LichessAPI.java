/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import chess.TestBot;
import chess.model.Event;
import chess.model.GameState;
import chess.model.Profile;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Iterator;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import logging.Logger;
/**
 * Java implementation of the Lichess.org HTTP API for chess bots
 * 
 */
public class LichessAPI {
    private final String token;
    private TestBot bot;
    private String gameId;
    private String playerId;
    private Logger logger;
    
    private HashMap<String, String> headers;
    
    public LichessAPI(TestBot bot) {
        this(bot, new Logger().useStdOut());
    }
    
    public LichessAPI(TestBot bot, Logger logger) {
        this.bot = bot;
        this.logger = logger;
        
        this.token = bot.getToken();
        
        headers = new HashMap<>();
        
        // Add token to HTTP headers
        headers.put("Authorization", "Bearer " + token);
        
    }
    
    /**
     * Get Lichess account information
     * @return Profile for the account associated with the given Lichess token
     */
    public Profile getAccount() {
        String json;
        try {
            json = Unirest.get("https://lichess.org/api/account")
                    .header("Authorization", "Bearer " + token)
                    .asString().getBody();
            
            Profile profile = Profile.parseFromJson(json);
            
            if (profile.id.isEmpty()) {
                logger.logError("Returned profile does not have an ID, is your Lichess token valid?");
            }
            
            return profile;
        } catch (UnirestException ex) {
            logger.logError(LichessAPI.class.getName() + " - " + ex.toString());
        }
        
        return null;
    }
    
    /**
     * Starts reading Lichess events
     * 
     * Accepts all received Challenge events, on GameStart event
     * enters gameplay loop
     */
    public void beginEventLoop() {
        Unirest.get("https://lichess.org/api/stream/event")
                .header("Authorization", "Bearer " + token)
                .thenConsume(r -> {
                    BufferedReader reader = new BufferedReader(r.getContentReader());
            
                    reader.lines().forEach(line -> {
                        if (!line.isEmpty()) {
                            Event event = Event.parseFromJson(line);
                
                            logger.logMessage("New event: " + event.type + " id: " + event.id);
                        
                            switch (event.type) {
                                case Challenge:
                                    logger.logMessage("Accepting challenge: " + event.id);
                                    System.out.println(acceptChallenge(event.id));
                                    break;
                                case GameStart:
                                    this.gameId = event.id;
                                
                                    playGame();
                                
                                    break;
                                default: break;
                            }   
                        }
                    });
                });
    }
    
    /**
     * Opens the game event stream and starts playing moves from the bot
     */
    public void playGame() {
        this.playerId = this.getAccount().id;
        
        logger.logMessage("Game starting: " + gameId);
        
        Unirest.get("https://lichess.org/api/bot/game/stream/" + gameId)
                .header("Authorization", "Bearer " + token)
                .thenConsume(r -> {
                    BufferedReader reader = new BufferedReader(r.getContentReader());
                    GameState gs = new GameState();
                    
                    boolean gameRunning = true;
                    
                    Iterator<String> gameEvents = reader.lines().iterator();
                    
                    while (gameRunning && gameEvents.hasNext()) {

                        String line = gameEvents.next();
                        String move = getNextMove(line, gs, playerId);

                        if (move == null) {
                             gameRunning = false;
                        } else if (move.equals("nomove")) {
                            logger.logMessage("Cannot make a move yet.");
                        } else {
                            int statusCode = makeMove(move);
                            
                            if (statusCode != 200) {
                                logger.logError("Lichess returned Bad Request status code, illegal move? Move was: " + move);
                            }
                        }
                    }
                });
    }
    
    /**
     * Updates gamestate based on given JSON and plays a bot move
     * @param jsonLine A line of JSON data according to https://lichess.org/api#operation/botGameStream
     * @param gamestate The state of the currently running game
     * @param playerId The Lichess ID of the bot
     * @return The move made by the bot in UCI format or "nomove" if the bot cannot make a move
     */
    public String getNextMove(String jsonLine, GameState gamestate, String playerId) {
        if (!jsonLine.isEmpty()) {
            gamestate.updateFromJson(jsonLine);
        }
        
        if ((gamestate.moves.size() % 2 == 0 && gamestate.playingWhite.equals(playerId)) 
                || (gamestate.moves.size() % 2 != 0 && gamestate.playingBlack.equals(playerId))) {
            // Call the bot
            String move = bot.nextMove(gamestate);
            
            if (move == null) {
                logger.logMessage("Bot returned no moves.");
                
            } else {
                logger.logMessage("Bot made move: " + move);
                return move;
            }
        } else {
            return "nomove";
        }
    
        
        return null;
    }
    /**
     * Accept a Lichess challenge
     * @param id The ID of the challenge event
     * @return The HTTP status code of the POST request response
     */
    public int acceptChallenge(String id) {
        return Unirest.post("https://lichess.org/api/challenge/" + id + "/accept")
                .header("Authorization", "Bearer " + token)
                .asEmpty().getStatus();
    }
    
    /**
     * Decline a Lichess challenge
     * @param id The ID of the challenge event
     * @return The HTTP status code of the POST request response
     */
    public int declineChallenge(String id) {
        return Unirest.post("https://lichess.org/api/challenge/" + id + "/decline")
                .header("Authorization", "Bearer " + token)
                .asEmpty().getStatus();
    }
    
    /**
     * Make a move in the current Lichess game
     * @param move The chess move in UCI format
     * @return The HTTP status code of the POST request response
     */
    public int makeMove(String move) {
        return Unirest.post("https://lichess.org/api/bot/game/" + this.gameId + "/move/" + move)
                .header("Authorization", "Bearer " + token)
                .field("offeringDraw", "false").asEmpty().getStatus();
    }

    public void setPlayerId(String newPlayerId) {
        this.playerId = newPlayerId;
    }
    
    public String getPlayerId() {
        return this.playerId;
    }
}
