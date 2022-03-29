package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.PieceType;

public class Move{
  public Coordinates[] c;
  public Player player;
  public PieceType piece;
  public Move(PieceType piece, Player player, Coordinates[] c){
    this.player=player;
    this.piece=piece;
    this.c = c;
  }
};