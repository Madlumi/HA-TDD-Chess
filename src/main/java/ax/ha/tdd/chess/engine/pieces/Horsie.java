package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.MoveTypes;
import ax.ha.tdd.chess.engine.Player;

public class Horsie extends ChessPiece{
  public Horsie(PieceType pieceType, Player player, Coordinates location) {
    super(pieceType, player, location);
  }

  @Override
  public String getSymbol() {
    return getPieceType().getSymbol();
  }

  @Override
  public MoveTypes[][] moveSpace(Chessboard b) {
    MoveTypes[][] ms = BaseSpace();

    for(int y = -2;y<3;y++){
      for(int x = -2;x<3;x++){
        if(Math.abs(x*y)==2){
          ms=bishopHelper(b,ms,x,y,true);
        }
    }}
    return ms;
  }

  @Override
  public void moved() {

  }
}
