/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.model;

import java.util.ArrayList;
import java.util.Arrays;
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
public class GameStateTest {
    
    public GameStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    public String gameStateFullJson = "{\n" +
"\n" +
"    \"type\": \"gameFull\",\n" +
"    \"id\": \"5IrD6Gzz\",\n" +
"    \"rated\": true,\n" +
"    \"variant\": \n" +
"\n" +
"{\n" +
"\n" +
"    \"key\": \"standard\",\n" +
"    \"name\": \"Standard\",\n" +
"    \"short\": \"Std\"\n" +
"\n" +
"},\n" +
"\"clock\": \n" +
"{\n" +
"\n" +
"    \"initial\": 1200000,\n" +
"    \"increment\": 10000\n" +
"\n" +
"},\n" +
"\"speed\": \"classical\",\n" +
"\"perf\": \n" +
"{\n" +
"\n" +
"    \"name\": \"Classical\"\n" +
"\n" +
"},\n" +
"\"createdAt\": 1523825103562,\n" +
"\"white\": \n" +
"{\n" +
"\n" +
"    \"id\": \"lovlas\",\n" +
"    \"name\": \"lovlas\",\n" +
"    \"provisional\": false,\n" +
"    \"rating\": 2500,\n" +
"    \"title\": \"IM\"\n" +
"\n" +
"},\n" +
"\"black\": \n" +
"{\n" +
"\n" +
"    \"id\": \"leela\",\n" +
"    \"name\": \"leela\",\n" +
"    \"rating\": 2390,\n" +
"    \"title\": null\n" +
"\n" +
"},\n" +
"\"initialFen\": \"startpos\",\n" +
"\"state\": \n" +
"\n" +
"    {\n" +
"        \"type\": \"gameState\",\n" +
"        \"moves\": \"e2e4 c7c5 f2f4 d7d6 g1f3 b8c6 f1c4 g8f6 d2d3 g7g6 e1g1 f8g7\",\n" +
"        \"wtime\": 7598040,\n" +
"        \"btime\": 8395220,\n" +
"        \"winc\": 10000,\n" +
"        \"binc\": 10000\n" +
"    }\n" +
"\n" +
"}";
    
    public String gameStateJson = "{\n" +
"\n" +
"    \"type\": \"gameState\",\n" +
"    \"moves\": \"e2e4 c7c5 f2f4 d7d6 g1f3 b8c6 f1c4 g8f6 d2d3 g7g6 e1g1 f8g7 b1c3\",\n" +
"    \"wtime\": 7598040,\n" +
"    \"btime\": 8395220,\n" +
"    \"winc\": 10000,\n" +
"    \"binc\": 10000\n" +
"\n" +
"}";
    
    @Test
    public void gameStateParsesJson() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        assert(true);
    }
    
    @Test
    public void gameStateIdReadCorrectly() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        assertEquals(gameState.id, "5IrD6Gzz");
    }
    
    @Test
    public void gameStateWhitePlayerReadCorrectly() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        assertEquals(gameState.playingWhite, "lovlas");
    }
    
    @Test
    public void gameStateBlackPlayerReadCorrectly() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        assertEquals(gameState.playingBlack, "leela");
    }
    
    @Test
    public void gameStateMovesReadCorrectly() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        ArrayList<String> moves = new ArrayList<>(Arrays.asList("e2e4 c7c5 f2f4 d7d6 g1f3 b8c6 f1c4 g8f6 d2d3 g7g6 e1g1 f8g7".split(" ")));
        
        assertEquals(gameState.moves, moves);
    }
    
    @Test
    public void gameStateMovesUpdateCorrectly() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        gameState.updateFromJson(gameStateJson);
        
        ArrayList<String> moves = new ArrayList<>(Arrays.asList("e2e4 c7c5 f2f4 d7d6 g1f3 b8c6 f1c4 g8f6 d2d3 g7g6 e1g1 f8g7 b1c3".split(" ")));
        
        assertEquals(gameState.moves, moves);
    }
}
