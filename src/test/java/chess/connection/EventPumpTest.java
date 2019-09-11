/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sami
 */
public class EventPumpTest {
    
    public EventPumpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    private InputStream testStream = new ByteArrayInputStream("test\n test2\n test3\n".getBytes());
    private ArrayDeque<String> testQueue;
    
    private EventPump pump;
    private Thread testThread;
    
    @Before
    public void setUp() {
        testQueue = new ArrayDeque<>();
        
        pump = new EventPump(testStream, testQueue);
        
        testThread = new Thread(pump, "test-thread");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hasNextReturnsFalseWhenEmptyQueue() {
        assertEquals(pump.hasNext(), false);
    }
    
    @Test
    public void hasNextReturnsTrueWhenNotEmpty() {
        testQueue.add("test0");
        
        assertEquals(pump.hasNext(), true);
    }
    
    @Test
    public void readingInputStreamAddsToQueue() {
        testThread.start();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(EventPumpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertEquals(pump.hasNext(), true);
    }
    
    @Test
    public void allILinesAreInQueue() {
        testThread.start();
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(EventPumpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String first = pump.next();
        String second = pump.next();
        String third = pump.next();
        
        assert(true);
    }
}
