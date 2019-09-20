package chess.connection;
import java.util.Scanner;
import chess.TestBot;

public class XBoardHandler {
    private Scanner scanner;
    private TestBot bot;

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
        while (true) {
            String command = scanner.nextLine();
            switch (command){
                case "new":
                //play as white
                break;
                
                case "go":
                //play as black
                break;

                default:
                //assume is a move
                //update internal gamestate
                //make next move
                break;
            }
        }
    }

    public void handleMove(String move){
        
    }

    public String makeMove(){
        return "";
    }
}