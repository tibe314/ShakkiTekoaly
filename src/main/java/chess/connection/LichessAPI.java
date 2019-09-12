/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import chess.model.Profile;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

public class LichessAPI {
    private HTTPHandler http;
    private String gameId;
    
    public LichessAPI(String token) {
        HashMap<String, String> headers = new HashMap<>();
        
        headers.put("Authorization", "Bearer " + token);
        
        this.http = new HTTPHandler(headers);
    }
    
    
    public Profile getAccount() {
        String json;
        try {
            json = this.http.get("https://lichess.org/api/account").asString().getBody();
            
            Profile profile = Profile.parseFromJson(json);
        
            return profile;
        } catch (UnirestException ex) {
            Logger.getLogger(LichessAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
