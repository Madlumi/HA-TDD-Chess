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
  public MoveTypes[][] moveSpace(Chessboard chessboard) {
    MoveTypes[][] ms = BaseSpace();
    //TODO


    return ms;
  }

  @Override
  public void moved() {

  }
}
