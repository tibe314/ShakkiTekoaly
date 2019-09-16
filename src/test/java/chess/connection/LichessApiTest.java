/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import chess.TestBot;
import chess.model.GameState;
import chess.model.Testdata;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class LichessApiTest {
    private TestBot bot;
    private LichessAPI api;
    public LichessApiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.bot = new TestBot("");
        this.api = new LichessAPI(bot);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getNextMoveReturnsNoMoveIfMissingLine(){
        GameState gameState = GameState.parseFromJson(Testdata.gameStateFullJson);
        String botmove = api.getNextMove("", gameState, api.getPlayerId());
        assertEquals(botmove, "nomove");
    }

    @Test
    public void getNextMoveReturnsValidMove(){
        GameState gameState = GameState.parseFromJson(Testdata.gameStateFullJson);
        api.setPlayerId("leela");
        String botmove = api.getNextMove(Testdata.gameStateJson, gameState, api.getPlayerId());
        assertNotNull(botmove);

    }
}