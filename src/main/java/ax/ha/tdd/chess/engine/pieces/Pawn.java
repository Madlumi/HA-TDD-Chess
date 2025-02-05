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
    //should refactor to utizize the bishophelp function, but i'd have to refactor that to use int rather than bool, can't be bothered

    //specialized code for pawns since theyre the only direction bound piece
    int dir;
    if (player.getSymbol()=="B"){dir=1;}else{dir=-1;}

    //init the array
    MoveTypes[][] moveSpace = BaseSpace();

    if(location.getY()+dir<0 || location.getY()+dir>7){
      return moveSpace;
    }

    //check front slot
    if (chessboard.getPiece(new Coordinates(location.getX(), location.getY()+dir)) == null){
      moveSpace[location.getX()][location.getY()+dir]=MoveTypes.MOVE;
      //check if first move, then allow for one more space
      if(state==0){if (chessboard.getPiece(new Coordinates(location.getX(), location.getY()+dir*2)) == null){
          moveSpace[location.getX()][location.getY()+dir*2]=MoveTypes.MOVE;
        }
      }
    }
    //check attack, could do a bunch of loops but not worth the effoert here
    if ((location.getX()<7)&&chessboard.getPiece(new Coordinates(location.getX()+1, location.getY()+dir)) != null &&
            chessboard.getPiece(new Coordinates(location.getX()+1, location.getY()+dir)).getPlayer().getSymbol()!=player.getSymbol()){
      moveSpace[location.getX()+1][location.getY()+dir]=MoveTypes.TAKE;
    }
    if ((location.getX()>0)&&chessboard.getPiece(new Coordinates(location.getX()-1, location.getY()+dir)) != null &&
            chessboard.getPiece(new Coordinates(location.getX()-1, location.getY()+dir)).getPlayer().getSymbol()!=player.getSymbol()){
      moveSpace[location.getX()+1][location.getY()+dir]=MoveTypes.TAKE;
    }

    //check en pessante
    if(chessboard.moveList.size()>0&&//make sure that it's not first move
      chessboard.moveList.get(chessboard.moveList.size()-1).piece == PieceType.PAWN && //the last move needs to be the pawn
      Math.abs(chessboard.moveList.get(chessboard.moveList.size()-1).c[1].getY()-
      chessboard.moveList.get(chessboard.moveList.size()-1).c[0].getY()) == 2 && //make sure the move is 2, thus facilitating en pessante
      chessboard.moveList.get(chessboard.moveList.size()-1).c[1].getY()==location.getY()){ //make sure that the Y is correct
            //we dont need to check that spot is empty due to how the double step works
            //check if next to the enpassable
            if((location.getX()>0)&&chessboard.moveList.get(chessboard.moveList.size()-1).c[1].getX()- location.getX() == -1){
              moveSpace[location.getX()-1][location.getY()+dir]=MoveTypes.ENPA;
            }else if((location.getX()<7)&&chessboard.moveList.get(chessboard.moveList.size()-1).c[1].getX()- location.getX() == 1){
              moveSpace[location.getX()+1][location.getY()+dir]=MoveTypes.ENPA;
            }

    }

      //

    return moveSpace;
  }




  @Override
  public void moved() {
    if (getState()==0) state++;
  }
}
