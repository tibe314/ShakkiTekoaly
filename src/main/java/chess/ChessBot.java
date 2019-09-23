/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.model.GameState;

public interface ChessBot {
	public String nextMove(GameState gamestate);
	
	public String getToken();
}
