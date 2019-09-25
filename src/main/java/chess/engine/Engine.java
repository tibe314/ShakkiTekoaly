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
        
        if (moves.size() > 0) {
            return moves.get(random.nextInt(moves.size()));
        } else {
            return null;
        }
    }
    public void setMove(String starting, String ending, String promote) {
        String promotionPiece = "";
        if (promote.length() > 0) {
            Piece piece = b.getPiece(Square.valueOf(starting));
            String side = piece.getPieceSide().value();
            switch (promote) {
                case "R" : promotionPiece = side + "_ROOK"; break;
                case "B" : promotionPiece = side + "_BISHOP"; break;
                case "N" : promotionPiece = side + "_KNIGHT"; break;
                case "Q" : promotionPiece = side + "_QUEEN"; break;
                default: throw new Error("Something went wrong parsing the promoted piece");   
            }
        }
        Move latestMove = promote.isEmpty() ? new Move(
                        Square.fromValue(starting),
                        Square.valueOf(ending)) : new Move(Square.fromValue(starting),
                        Square.valueOf(ending), Piece.fromValue(promotionPiece));
        this.b.doMove(latestMove);
    }
}