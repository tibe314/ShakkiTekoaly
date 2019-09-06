/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import java.io.IOException;
import java.util.HashMap;
import org.json.*;

public class LichessApi {
    private HTTPHandler http;
    private String gameId;
    
    public LichessApi(String token) {
        HashMap<String, String> headers = new HashMap<>();
        
        headers.put("Authorization", "Bearer " + token);
        
        this.http = new HTTPHandler(headers);
    }
    
    public String getAccount() throws IOException {
        return this.http.get("https://lichess.org/api/account");
    }
    
    public String getEvents() throws IOException {
        return this.http.get("https://lichess.org/api/stream/event");
    }
    
    public String acceptChallenge(String challengeId) throws IOException {
        return this.http.post("https://lichess.org/api/challenge/%s/accept".format(challengeId), "");
    }
    
    public String getGamestate() throws IOException {
        return this.http.get("https://lichess.org/api/bot/game/stream/" + gameId);
    }
    
    public String makeMove(String move) throws IOException {
        return this.http.post("https://lichess.org/api/bot/game/%s/move/%s".format(gameId, move), "offeringDraw=false");
    }
}
