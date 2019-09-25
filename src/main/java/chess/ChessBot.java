package chess;

import chess.model.GameState;

public interface ChessBot {
    String nextMove(GameState gamestate);
	
    String getToken();
}
