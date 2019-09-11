package chess.connection;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import java.util.Map;

public class HTTPHandler {

    private Map<String, String> headerFields;

    public HTTPHandler(Map<String, String> headerFields) {
        this.headerFields = headerFields;
    }

    public HttpRequest get(String urlString) {
        return Unirest.get(urlString)
                .headers(headerFields);
    }
    /* POST DATA  NOT APPLIED YET */ 
    public HttpRequest post(String urlString, String postData) {
        return Unirest.post(urlString)
                .headers(headerFields);
    }
    

}
