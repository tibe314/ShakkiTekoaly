/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.model;

import org.json.*;

public class Profile {
    public String id;
    public String username;
    
    public static Profile parseFromJson(String json) {
        Profile newProfile = new Profile();
        
        JSONObject jsonProfile = new JSONObject(json);
        
        newProfile.id = jsonProfile.getString("id");
        newProfile.username = jsonProfile.getString("username");
        
        return newProfile;
    }
}
