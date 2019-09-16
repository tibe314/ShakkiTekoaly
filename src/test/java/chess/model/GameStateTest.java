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

    public String gameStateFullJson = Testdata.gameStateFullJson;
    public String gameStateJson = Testdata.gameStateJson;
    public String gameStateJsonShort = Testdata.gameStateJsonShort;
    
    @Test
    public void gameStateParsesJson() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        assert (true);
    }
    
    @Test
    public void gameStateParsesSingleLineJson() {
        GameState gameState = GameState.parseFromJson(gameStateJsonShort);
        
        assert (true);
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
        String s = "e2e4 c7c5 f2f4 d7d6 g1f3 ";
        s = s.concat("b8c6 f1c4 g8f6 d2d3 g7g6 e1g1 f8g7");
        ArrayList<String> moves = new ArrayList<>(Arrays.asList(s.split(" ")));
        
        assertEquals(gameState.moves, moves);
    }
    
    @Test
    public void gameStateMovesUpdateCorrectly() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        gameState.updateFromJson(gameStateJson);
        String s = "e2e4 c7c5 f2f4 d7d6 g1f3 b8c6 f1c4 ";
        s = s.concat("g8f6 d2d3 g7g6 e1g1 f8g7 b1c3");
        ArrayList<String> moves = new ArrayList<>(Arrays.asList(s.split(" ")));
        
        assertEquals(gameState.moves, moves);
    }
    
    @Test
    public void gameStateUpdateFromGameFullWorks() {
        GameState gameState = GameState.parseFromJson(gameStateFullJson);
        
        gameState.updateFromJson(gameStateFullJson);
        
        assert(true);
    }
}
