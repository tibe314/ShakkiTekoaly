package chess.engine;
import com.github.bhlangonijr.chesslib.move.*;

import java.util.Random;

import com.github.bhlangonijr.chesslib.*;

public class Engine {
    private Random random; 
    private Board b;
    public Engine() {
        this.random = new Random();
        this.b = new Board();
    }
    public Board getBoard() {
        return this.b;
    }
    public Move getMove() throws Exception {
        MoveList moves = MoveGenerator.generateLegalMoves(b);
        System.out.println(moves);
        
        if (moves.size() > 0) {
            return moves.get(random.nextInt(moves.size()));
        } else {
            return null;
        }
    }
}