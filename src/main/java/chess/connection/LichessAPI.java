/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import chess.model.Event;
import chess.model.EventType;
import chess.model.Profile;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.json.*;

public class LichessAPI {
    private HTTPHandler http;
    private String gameId;
    private String token;
    
    public LichessAPI(String token) {
        HashMap<String, String> headers = new HashMap<>();
        
        headers.put("Authorization", "Bearer " + token);
        
        this.http = new HTTPHandler(headers);
        this.token = token;
    }
    
    
    public Profile getAccount() {
        String json;
        try {
            json = (String) this.http.get("https://lichess.org/api/account").asString().getBody();
            
            Profile profile = Profile.parseFromJson(json);
        
            return profile;
        } catch (UnirestException ex) {
            Logger.getLogger(LichessAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    // This is mainly just for show now, calling it results in infinite loop
    public void getEvents() {
        Unirest.get("https://lichess.org/api/stream/event").header("Authorization", "Bearer " + token).thenConsume(r -> {
            BufferedReader reader = new BufferedReader(r.getContentReader());
            
            reader.lines().forEach(line -> {
                Event event = Event.parseFromJson(line);
                
                System.out.println("New event: " + event.type + " id: " + event.id);
                
                if (event.type == EventType.Challenge) {
                    System.out.println("Accepting challenge: " + event.id);
                    System.out.println(acceptChallenge(event.id));
                }
            });
            
        });
    }
    
    public String acceptChallenge(String id) {
        return Unirest.post("https://lichess.org/api/challenge/" + id + "/accept")
                .header("Authorization", "Bearer " + token)
                .asEmpty().getStatusText();
    }
    
    public String declineChallenge(String id) {
        return Unirest.post("https://lichess.org/api/challenge/" + id + "/decline")
                .header("Authorization", "Bearer " + token)
                .asEmpty().getStatusText();
    }
    
    public String makeMove(String move) {
        return Unirest.post("https://lichess.org/api/bot/game/{gameId}/move/" + move)
                .header("Authorization", "Bearer " + token)
                .field("offeringDraw", "false").asString().getBody();
    }
}
