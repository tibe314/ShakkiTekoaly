package chess.connection;
import java.util.Scanner;
import chess.TestBot;
import chess.model.GameState;

public class XBoardHandler {
    private Scanner scanner;
    private TestBot bot;
    private GameState gamestate;

    public XBoardHandler(TestBot bot) {
        this.bot = bot;
        scanner = new Scanner(System.in);
        while (true){
            if (scanner.nextLine().startsWith("protover")){
                System.out.append("feature sigint=0 sigterm=0 reuse=0 myname=\"tiraengine\" done=1\n");
                //set reuse=1 if bot is able to play multiple games in a session
                System.out.flush();
            }
            run();
        }

    }

    public void run() {
        this.gamestate = new GameState();
        while (true) {
            String command = scanner.nextLine();
            switch (command){
                case "new":
                //tells the engine the match has started and it plays as black, may not require implementation at all.
                break;
                
                case "go": 
                //tells the engine to start playing as white
                nextMove(); 
                break;

                default:
                handleMove(command);
                nextMove();
                break;
            }
        }
    }

    public void handleMove(String move){
        this.gamestate.moves.add(command);
    }

    public String nextMove(){
        return gamestate.moves.add(bot.makeMove());
    }
}