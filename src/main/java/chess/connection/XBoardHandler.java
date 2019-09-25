package chess.connection;

import chess.TestBot;
import chess.model.GameState;
import java.io.BufferedReader;
import java.io.IOException;

public class XBoardHandler {
    private TestBot bot;
    private GameState gamestate;
    private BufferedReader in;

    public XBoardHandler(TestBot bot, BufferedReader in) {
        this.bot = bot;
        this.in = in;
        String input;
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

    public void run() {
        this.gamestate = new GameState();
        while (true) {            
            try {
                String command = this.in.readLine();
                switch (command.split(" ")[0]) {
                    case "new":
                        //tells the engine the match has started and it plays as black
                        break;
                    
                    case "go": 
                        //tells the engine to start playing as white
                        System.out.println("move " + nextMove() + "\n");
                        System.out.flush(); 
                        break;

                    case "usermove": 
                        handleMove(command.split(" ")[1]);
                        System.out.println("move " + nextMove() + "\n");
                        System.out.flush();
                        break;
                    
                    default:
                        continue;
                }
                
            } catch (IOException e) { 
                e.printStackTrace();
            }
        }
    }
    

    public void handleMove(String move) {
        this.gamestate.moves.add(move);
        gamestate.parseLatestMove();
    }

    public String nextMove() {
        String move = bot.nextMove(gamestate);
        gamestate.moves.add(move);
        gamestate.parseLatestMove();
        return move;
    }
}