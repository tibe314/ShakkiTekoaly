package chess.engine;
import com.github.bhlangonijr.chesslib.move.*;
import com.github.bhlangonijr.chesslib.*;

public class Engine {
    public static void main(String[] args){
        Board b = new Board();
        b.doMove(new Move(Square.E2, Square.E4));
        System.out.println(b.toString());
        try {
            MoveList moves = MoveGenerator.generateLegalMoves(b);
            System.out.println(moves);
        } catch (Exception e) {
            System.out.println("MOI");
        } 
    }
}