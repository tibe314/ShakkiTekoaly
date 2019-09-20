package chess.connection;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class HTTPStream implements Iterator<String>, Closeable {

    private HttpURLConnection conn;
    private Iterator<String> iterator;

    public HTTPStream() {

    }

    public HTTPStream get(String urlString) {
        try {
            URL url = new URL(urlString);
            this.conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
        } catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }

    public HTTPStream post(String urlString, String postData) {
        try {
            URL url = new URL(urlString);
            this.conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
        } catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }

    public HTTPStream setHeaders(Map<String, String> headerFields) {
        try {
            headerFields.entrySet().forEach((entry) -> {
                this.conn.setRequestProperty(entry.getKey(), entry.getValue());
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }

    public HTTPStream connect() {
        try {
            this.conn.connect();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            this.conn.getInputStream()));
            this.iterator = reader.lines().iterator();
        } catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }
    
    public int getHTTPStatus() {
        try {
            return this.conn.getResponseCode();
        } catch (IOException e) {
            System.out.println(e);
        }
        return 999;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public String next() {
        return this.iterator.next();
    }

    @Override
    public void close() throws IOException {
        this.conn.disconnect();
    }

}
