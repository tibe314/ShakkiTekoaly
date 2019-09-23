package chess;

import chess.model.GameState;

public interface ChessBot {
	public String nextMove(GameState gamestate);
	
	public String getToken();
}
