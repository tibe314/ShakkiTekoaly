package chess.engine;
import com.github.bhlangonijr.chesslib.move.*;

import java.util.Random;

import com.github.bhlangonijr.chesslib.*;

public class Engine {
    private Random random; 
    private Board b;
    public Engine(){
        this.random = new Random();
        this.b = new Board();
    }
    public Board getBoard(){
        return this.b;
    }
    public Move getMove() throws Exception{
        MoveList moves = MoveGenerator.generateLegalMoves(b);
        System.out.println(moves);
        Move move = moves.get(random.nextInt(moves.size()));
        return move;
    }

    public static void main(String[] args){
        Engine engine = new Engine();
        Board b = engine.getBoard();   
        for (int i=0; i<20; i++){
            try {
                Move move = engine.getMove();
                b.doMove(move);   
               
            } catch (Exception e) {
                System.out.println("Error while generating moves: " + e.getMessage() + " at index " + i );
            }
         
            
        }   
        System.out.println(b.toString());
    
    }

    
}