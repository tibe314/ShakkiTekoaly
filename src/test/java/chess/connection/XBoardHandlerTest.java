package chess.connection;

import chess.bot.TestBot;
import chess.engine.GameState;
import chess.model.Profile;
import chess.model.Testdata;
import static chess.model.Testdata.profileJson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import static org.junit.Assert.*;

public class XBoardHandlerTest {
    private TestBot bot;
    private BufferedReader in;
    public XBoardHandlerTest() {
    }

    @Before
    public void setUp() {
        this.bot = new TestBot("");   
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void xBoardHandlerDoesntCrashOnStartupAsWhite() {
        String data = "protover\n" + "new\n" + "white \n" + "endloop \n";
        
        this.in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));

        XBoardHandler xb = new XBoardHandler(bot, in);
        xb.run();
        
        assert(true);
        
    }
    @Test
    public void xBoardHandlerDoesntCrashOnStartupAsWhiteWithBlankInputIncluded() {
        String data = "protover\n" + "new\n" + "white \n" + "\n" + "endloop \n";
        
        this.in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));

        XBoardHandler xb = new XBoardHandler(bot, in);
        xb.run();
        
        assert(true);
        
    }

    @Test
    public void xBoardHandlerDoesntCrashOnStartupAsWhiteWithBlankInputBeforeProtover() {
        String data = "\n" + "protover\n" + "new\n" + "white \n" + "\n" + "endloop \n";
        
        this.in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));

        XBoardHandler xb = new XBoardHandler(bot, in);
        xb.run();
        
        assert(true);
        
    }
    @Test
    public void xBoardHandlerDoesntCrashOnStartupAsBlack() {
        String data = "protover\n" + "new\n" + "endloop \n";
        
        this.in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));

        XBoardHandler xb = new XBoardHandler(bot, in);
        xb.run();

        assert(true);
        
    }

    @Test
    public void xBoardHandlerDoesntCrashOnStartupAsBlackWithOpeningMove() {
        String data = "protover\n" + "new\n" + "white \n" + "usermove e2e4\n" + "endloop \n";
        
        this.in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));

        XBoardHandler xb = new XBoardHandler(bot, in);
        xb.run();
        
        assert(true);
        
    }
    @Test
    public void xBoardHandlerIsAbleToHandleIncomingMoves() {
        String data = "protover \n" + "new \n" + "endloop \n";
        
        this.in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));

        XBoardHandler xb = new XBoardHandler(bot, in);
        xb.run();
        xb.handleMove("e2e4");
   

        GameState gs = xb.getGameState();
        assert(gs.moves.contains("e2e4"));
    }

    @Test
    public void xBoardHandlerIsAbleToMakeNewMoves() {
        String data = "protover \n" + "new \n" + "endloop \n";
        
        this.in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));

        XBoardHandler xb = new XBoardHandler(bot, in);
        xb.run();
        xb.nextMove();

        GameState gs = xb.getGameState();
        assert(gs.moves.size() == 1);
    }
}