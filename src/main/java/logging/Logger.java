/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logging;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.logging.Level;




public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    private boolean useStdOut;
    private boolean useLogFile;
    private String filePath;
    
    public Logger() {
        useStdOut = false;
        useLogFile = false;
        filePath = "./log.txt";
    }
    
    public Logger useStdOut() {
        this.useStdOut = true;
        
        return this;
    }
    
    public Logger useLogFile() {
        this.useLogFile = true;
        
        return this;
    }
    
    public Logger alternatePath(String path) {
        this.filePath = path;
        
        return this;
    }
    
    public void logMessage(String message) {
        String messageWithDate = LocalDateTime.now().toString() + textInGreen(" MESSAGE: ") + message;
        
        if (useStdOut) {
            System.out.println(messageWithDate);
        }
        
        if (useLogFile) {
            FileWriter out = null;
            try {
                out = new FileWriter(filePath, true);
                out.write(messageWithDate + "\n");
                out.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    out.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void logError(String message) {
        String messageWithDate = LocalDateTime.now().toString() + textInRed(" ERROR: ") + message;
        
        if (useStdOut) {
            System.out.println(messageWithDate);
        }
        
        if (useLogFile) {
            FileWriter out = null;
            try {
                out = new FileWriter(filePath, true);
                out.write(messageWithDate + "\n");
                out.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    out.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public String textInRed(String text) {
        return ANSI_RED + text + ANSI_RESET;
    }
    
    public String textInGreen(String text) {
        return ANSI_GREEN + text + ANSI_RESET;
    }
}
