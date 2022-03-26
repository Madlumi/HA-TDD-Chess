package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.MoveTypes;
import ax.ha.tdd.chess.engine.Player;

public class Pawn extends ChessPiece {

  public Pawn(PieceType pieceType, Player player, Coordinates location) {
    super(pieceType, player, location);
  }

  @Override
  public String getSymbol() {
    return "P";
  }

  @Override
  public MoveTypes[][] moveSpace(Chessboard chessboard) {
    //specialized code for pawns since theyre the only direction bound piece
    int dir;
    if (player.getSymbol()=="B"){dir=1;}else{dir=-1;}

    //init the array
    MoveTypes[][] moveSpace = new MoveTypes[8][8];
    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        moveSpace[x][y]=MoveTypes.ILLE;
      }
    }
    //check front slot
    if (chessboard.getPiece(new Coordinates(location.getX(), location.getY()+dir)) == null){
      moveSpace[location.getX()][location.getY()+dir]=MoveTypes.MOVE;
      //check if first move, then allow for one more space
      if(state==2){if (chessboard.getPiece(new Coordinates(location.getX(), location.getY()+dir*2)) == null){
          moveSpace[location.getX()][location.getY()+dir*2]=MoveTypes.MOVE;
        }
      }
    }
    //check attack, could do a bunch of loops but not worth the effoert here
    if (chessboard.getPiece(new Coordinates(location.getX()+1, location.getY()+dir)) != null &&
            chessboard.getPiece(new Coordinates(location.getX()+1, location.getY()+dir)).getPlayer().getSymbol()!=player.getSymbol()){
      moveSpace[location.getX()+1][location.getY()+dir]=MoveTypes.TAKE;
    }
    if (chessboard.getPiece(new Coordinates(location.getX()-1, location.getY()+dir)) != null &&
            chessboard.getPiece(new Coordinates(location.getX()-1, location.getY()+dir)).getPlayer().getSymbol()!=player.getSymbol()){
      moveSpace[location.getX()+1][location.getY()+dir]=MoveTypes.TAKE;
    }
    //check en pessante
    if (chessboard.getPiece(new Coordinates(location.getX()+1, location.getY())) != null &&
            chessboard.getPiece(new Coordinates(location.getX()+1, location.getY())).getPlayer().getSymbol()!=player.getSymbol()&&
            chessboard.getPiece(new Coordinates(location.getX()+1, location.getY())).getPieceType().getSymbol() == "P" &&
            chessboard.getPiece(new Coordinates(location.getX()+1, location.getY())).getState()==1
    ){
      moveSpace[location.getX()+1][location.getY()+dir]=MoveTypes.ENPA;
    }
    if (chessboard.getPiece(new Coordinates(location.getX()-1, location.getY())) != null &&
            chessboard.getPiece(new Coordinates(location.getX()-1, location.getY())).getPlayer().getSymbol()!=player.getSymbol()&&
            chessboard.getPiece(new Coordinates(location.getX()-1, location.getY())).getPieceType().getSymbol() == "P" &&
            chessboard.getPiece(new Coordinates(location.getX()-1, location.getY())).getState()==1
    ){

      moveSpace[location.getX()-1][location.getY()+dir]=MoveTypes.ENPA;
    }
    return moveSpace;
  }

  @Override
  public void moved() {
    if (getState()==2) state--;
  }
}
