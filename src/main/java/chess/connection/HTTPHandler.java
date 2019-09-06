package chess.connection;

import java.io.*;
import java.net.*;

public class HTTPHandler {

    public String get(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String body = parseGetBody(conn.getInputStream());
        conn.disconnect();
        return body;
    }

    public String post(String urlString, String postData) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST"); //Default method is GET
        conn.setDoOutput(true); //False by default
        writePost(conn.getOutputStream(),postData);
        String body = parseGetBody(conn.getInputStream());
        conn.disconnect();
        return body;

    }

    private String parseGetBody(InputStream is) throws IOException {
        BufferedReader bReader = new BufferedReader(
                new InputStreamReader(is));
        return bReader.lines()
                .reduce((all, newLine) -> all.concat(newLine)) //Parse lines to single line, i.e. no new-lines
                .orElse(""); //Return empty string if input was empty
    }

    private void writePost(OutputStream output, String postBody) throws IOException {
        try {
            output.write(postBody.getBytes("UTF-8"));
        } finally {
            output.flush();
            output.close();
        }
    }

}
