/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.connection;

import chess.TestBot;
import chess.model.GameState;
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
import static org.junit.Assert.*;

public class LichessApiTest {

    private TestBot bot;
    private Logger logger;
    private LichessAPI api;
    private MockHTTPIOFactory httpFactory;

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
        this.logger = new Logger().useLogFile();
        this.httpFactory = new MockHTTPIOFactory();
        this.api = new LichessAPI(bot, logger, httpFactory);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getNextMoveReturnsNoMoveIfMissingLine() {
        GameState gameState = GameState.parseFromJson(Testdata.gameStateFullJson);
        String botmove = api.getNextMove("", gameState, api.getPlayerId());
        assertEquals(botmove, "nomove");
    }

    @Test
    public void getNextMoveReturnsValidMove() {
        GameState gameState = GameState.parseFromJson(Testdata.gameStateFullJson);
        api.setPlayerId("leela");
        String botmove = api.getNextMove(Testdata.gameStateJson, gameState, api.getPlayerId());
        assertNotNull(botmove);
    }

    @Test
    public void getProfileMakesGetRequest() {
        MockHTTPIO mockStream = new MockHTTPIO();

        String[] linesArray = profileJson.split("\n");
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(linesArray));
        mockStream.setOutput(lines.iterator());

        httpFactory.addMockHTTPIOToQueue(mockStream);

        Profile profile = this.api.getAccount();

        assertEquals("GET", mockStream.requestType);
    }

    @Test
    public void acceptChallengeMakesPostRequest() {
        MockHTTPIO mockStream = new MockHTTPIO();

        httpFactory.addMockHTTPIOToQueue(mockStream);

        int status = api.acceptChallenge("asd");

        assertEquals("POST", mockStream.requestType);
    }

    @Test
    public void declineChallengeMakesPostRequest() {
        MockHTTPIO mockStream = new MockHTTPIO();

        httpFactory.addMockHTTPIOToQueue(mockStream);

        int status = api.declineChallenge("asd");

        assertEquals("POST", mockStream.requestType);
    }

    @Test
    public void makeMoveMakesPostRequest() {
        MockHTTPIO mockStream = new MockHTTPIO();

        httpFactory.addMockHTTPIOToQueue(mockStream);

        int status = api.makeMove("asd");

        assertEquals("POST", mockStream.requestType);
    }

    @Test
    public void beginEventLoopMakesGetRequest() {
        MockHTTPIO mockStream = new MockHTTPIO();

        ArrayList<String> events = new ArrayList<>();

        mockStream.setOutput(events.iterator());

        httpFactory.addMockHTTPIOToQueue(mockStream);

        api.beginEventLoop();

        assertEquals("GET", mockStream.requestType);
    }

    @Test
    public void playGameLegalMoves() throws IOException {
        this.api = new LichessAPI(new MockBot(Arrays.asList("d7d5", "c7c5", "b7b5", "d8d7", "e8d7")), logger, this.httpFactory);
        Iterator<String> iterator = new ArrayList<>(Arrays.asList(Testdata.testGameJSON)).iterator();
        this.logger.useStdOut();
        this.api.playGame(iterator);
        byte[] stdr = new byte[100];
        System.in.read(stdr);
        for (int i = 0; i < stdr.length; i++) {
            assertEquals(stdr[i], 0);
        }

    }

    @Test
    public void correctErrorMessageWhenTryingToMakeMoveTooQuick() throws IOException {
        this.api = new LichessAPI(new MockBot(Arrays.asList("d7d5", "c7c5", "b7b5", "d8d7", "e8d7")), logger, this.httpFactory);
        Iterator<String> iterator = new ArrayList<>(Arrays.asList(Testdata.testGameJSONStops)).iterator();
        this.logger.useStdOut();
        this.api.playGame(iterator);
        assertFalse("Error reading message from std", System.in.read() == 0);
    }
}
