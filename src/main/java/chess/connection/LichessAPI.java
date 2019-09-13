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
import java.util.logging.Level;
import java.util.logging.Logger;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class LichessAPI {
    private HTTPHandler http;
    private final String token;
    private TestBot bot;
    private String gameId;
    
    public LichessAPI(TestBot bot) {
        this.bot = bot;
        
        this.token = bot.getToken();
        
        HashMap<String, String> headers = new HashMap<>();
        
        headers.put("Authorization", "Bearer " + token);
        
        this.http = new HTTPHandler(headers);
    }
    
    /**
     * Get Lichess account information
     * @return Profile for the account associated with the given Lichess token
     */
    public Profile getAccount() {
        String json;
        try {
            json = (String) this.http.get("https://lichess.org/api/account")
                    .asString().getBody();
            
            Profile profile = Profile.parseFromJson(json);
        
            return profile;
        } catch (UnirestException ex) {
            Logger.getLogger(LichessAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    // This is mainly just for show now, calling it results in infinite loop
    /**
     * Read events from Lichess and react to them
     * Will accept all Challenges
     */
    public void getEvents() {
        Unirest.get("https://lichess.org/api/stream/event")
                .header("Authorization", "Bearer " + token)
                .thenConsume(r -> {
                    BufferedReader reader = new BufferedReader(r.getContentReader());
            
                    reader.lines().forEach(line -> {
                        Event event = Event.parseFromJson(line);
                
                        System.out.println("New event: " + event.type + " id: " + event.id);
                        
                        switch (event.type) {
                            case Challenge:
                                System.out.println("Accepting challenge: " + event.id);
                                System.out.println(acceptChallenge(event.id));
                                break;
                            case GameStart:
                                System.out.println("Game starting...");
                                
                                this.gameId = event.id;
                                
                                playGame();
                                
                                break;
                        }
                    });
            
                });
    }
    
    public void playGame() {
        String playerId = this.getAccount().id;
        
        Unirest.get("https://lichess.org/api/bot/game/stream/" + gameId)
                .header("Authorization", "Bearer " + token)
                .thenConsume(r -> {
                    BufferedReader reader = new BufferedReader(r.getContentReader());
                    GameState gs = new GameState();
                    reader.lines().forEach(line -> {
                        gs.updateFromJson(line);
                        
                        if (gs.moves.size() % 2 == 0 && gs.playingWhite.equals(playerId)) {
                            // Call the bot
                            String move = bot.nextMove(gs);
                            
                            System.out.println("Making move: " + move);
                            
                            System.out.println(makeMove(move));
                        } else if (gs.moves.size() % 2 != 0 && gs.playingBlack.equals(playerId)) {
                            // Call the bot
                            String move = bot.nextMove(gs);
                            
                            System.out.println("Making move: " + move);
                            
                            System.out.println(makeMove(move));
                        }
                    });
                });
    }
    
    /**
     * Accept a Lichess challenge
     * @param id The ID of the challenge event
     * @return The HTTP status text of the POST request response
     */
    public String acceptChallenge(String id) {
        return Unirest.post("https://lichess.org/api/challenge/" + id + "/accept")
                .header("Authorization", "Bearer " + token)
                .asEmpty().getStatusText();
    }
    
    /**
     * Decline a Lichess challenge
     * @param id The ID of the challenge event
     * @return The HTTP status text of the POST request response
     */
    public String declineChallenge(String id) {
        return Unirest.post("https://lichess.org/api/challenge/" + id + "/decline")
                .header("Authorization", "Bearer " + token)
                .asEmpty().getStatusText();
    }
    
    /**
     * Make a move in the current Lichess game
     * @param move The chess move in UCI format
     * @return The HTTP status text of the POST request response
     */
    public String makeMove(String move) {
        return Unirest.post("https://lichess.org/api/bot/game/" + this.gameId + "/move/" + move)
                .header("Authorization", "Bearer " + token)
                .field("offeringDraw", "false").asEmpty().getStatusText();
    }
}
