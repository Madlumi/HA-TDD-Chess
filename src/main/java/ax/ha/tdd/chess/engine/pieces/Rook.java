package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.MoveTypes;
import ax.ha.tdd.chess.engine.Player;

public class Rook  extends ChessPiece {
  public Rook(PieceType pieceType, Player player, Coordinates location) {
    super(pieceType, player, location);
  }

  @Override
  public String getSymbol() {
    return PieceType.ROOK.getSymbol();
  }

  @Override
  public MoveTypes[][] moveSpace(Chessboard b) {
    MoveTypes[][] moveSpace = BaseSpace();
    moveSpace = TowerSpace(b,moveSpace);
    return moveSpace;
  }

  @Override
  public void moved() {}
}
