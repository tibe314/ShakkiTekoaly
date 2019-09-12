package chess.connection;


import java.util.Map;
import kong.unirest.HttpRequest;
import kong.unirest.Unirest;

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
