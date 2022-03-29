package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.PieceType;

class Move{
  Coordinates[] c;
  Player player;
  PieceType piece;
  Move(PieceType piece, Player player, Coordinates[] c){
    this.player=player;
    this.piece=piece;
    this.c = c;
  }
};