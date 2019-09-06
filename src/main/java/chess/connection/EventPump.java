/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Asynchronous line reader
 * 
 */
public class EventPump implements Runnable {

    private ArrayDeque<String> eventQueue;
    private InputStream inputStream;
    
    public EventPump(InputStream stream, ArrayDeque<String> eventQueue) {
        this.eventQueue = eventQueue;
        this.inputStream = stream;
    }
    
    /**
     * Check if the queue contains a line
     * @return true if queue is not empty, false otherwise
     */
    public synchronized boolean hasNext() {
        return !eventQueue.isEmpty();
    }
    
    /**
     * Return the next line in the queue
     * 
     * @return 
     */
    public synchronized String next() {
        return eventQueue.removeFirst();
    }
    
    /**
     * Synchronously push a new event into the queue
     * @param event 
     */
    private synchronized void pushEvent(String event) {
        eventQueue.add(event);
    }
    
    @Override
    public void run() {
        String line;
        
        BufferedReader jsonStream;
        
        try {
            jsonStream = new BufferedReader(new InputStreamReader(inputStream));
                    
            while (jsonStream.ready()) {
                line = jsonStream.readLine();
                        
                pushEvent(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(EventPump.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
}
