package chess.connection;

import chess.bot.ChessBot;
import chess.engine.GameState;
import java.io.BufferedReader;
import java.io.IOException;

import logging.Logger;
/**
 * Class for handling game input coming from XBoard
 */
public class XBoardHandler {
    private ChessBot bot;
    private GameState gamestate;
    private BufferedReader in;

    private Logger logger;
    
    /**
     * Initializes XBoardHandler with a 
     * @param bot A chess bot 
     * @param in Reader for standard input
     */
    public XBoardHandler(ChessBot bot, BufferedReader in) {
        this.bot = bot;
        this.in = in;
        String input = "";
        this.logger = new Logger().useLogFile();
        this.gamestate = new GameState();
        
        while (true) {
            try {
                input = this.in.readLine();
                if (input.startsWith("protover")) {
                    System.out.println("feature done=0 sigint=0 sigterm=0 reuse=0 "
                            + "usermove=1 myname=\"tira-chess\" done=1\n");
                    //set reuse=1 if bot is able to play multiple games in a session
                    System.out.flush();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Executes the game loop for XBoard
     */
    public void run() {
        Boolean run = true;
        while (run) {            
            try {
                String command = this.in.readLine();
                logger.logMessage(command);
                switch (command.split(" ")[0]) {
                    case "new":
                        this.gamestate = new GameState();
                        break;
                    
                    case "white": 
                        //tells the engine to start playing as white
                        System.out.println("move " + nextMove() + "\n");
                        System.out.flush(); 
                        break;

                    case "usermove": 
                        handleMove(command.split(" ")[1]);
                        System.out.println("move " + nextMove() + "\n");
                        System.out.flush();
                        break;
                    
                    case "endloop":
                        run = false;
                        break;
                        
                    default:
                        continue;
                }
                
            } catch (IOException e) { 
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Processes a single move from XBoard and updates GameState
     * @param move XBoard-supplied move
     */
    public void handleMove(String move) {
        this.gamestate.moves.add(move);
        gamestate.parseLatestMove();
        this.logger.logMessage(gamestate.moves.toString());
    }
    
    /**
     * Asks the chess bot for a move and updates the GameState
     * @return 
     */
    public String nextMove() {
        String move = bot.nextMove(gamestate);
        gamestate.moves.add(move);
        gamestate.parseLatestMove();
        return move;
    }

    public GameState getGameState() {
        return this.gamestate;
    }
}