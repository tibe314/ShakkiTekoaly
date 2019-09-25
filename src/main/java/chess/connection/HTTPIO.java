/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract representation of a HTTP input/output object
 */
public interface HTTPIO {
    public HTTPIO get(String url);
    
    public HTTPIO post(String url, String postData);
    
    public HTTPIO setHeaders(Map<String, String> headers);
    
    public HTTPIO connect();
    
    public int getHTTPStatus();
    
    public void close() throws IOException;
    
    public Iterator<String> getIterator();
}
