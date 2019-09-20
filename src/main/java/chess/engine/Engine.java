// THIS CLASS IS ONLY FOR INTERNAL GAMESTATE TRACKING AND TESTING
// THIS CLASS SHOULD NOT BE USED FOR WRITING A CHESS BOT FOR 
// DATA STRUCTURES AND ALGORITHMS PROJECTS

package chess.engine;
import com.github.bhlangonijr.chesslib.move.*;

import java.util.Random;

import com.github.bhlangonijr.chesslib.*;

/**
 * Abstraction of a chess engine
 * 
 * <b>Data Structures and Algorithms student: DO NOT USE THIS CLASS</b>
 */
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
    
    /**
     * Fetches a random move based on current board state
     * @return A chesslib move 
     * @throws MoveGeneratorException if unable to generate legal moves
     */
    public Move getMove() throws MoveGeneratorException {
        MoveList moves = MoveGenerator.generateLegalMoves(b);
        System.out.println(moves);
        
        if (moves.size() > 0) {
            return moves.get(random.nextInt(moves.size()));
        } else {
            return null;
        }
    }
}