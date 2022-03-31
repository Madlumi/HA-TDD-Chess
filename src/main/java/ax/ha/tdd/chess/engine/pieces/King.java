package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.*;

public class King extends ChessPiece{
  public King(PieceType pieceType, Player player, Coordinates location) {
    super(pieceType, player, location);
  }

  @Override
  public String getSymbol() {
    return getPieceType().getSymbol();
  }

  @Override
  public MoveTypes[][] moveSpace(Chessboard b) {
    MoveTypes[][] ms = BaseSpace();

    ms=bishopHelper(b,ms,-1,-1,true);
    ms=bishopHelper(b,ms,1,-1,true);
    ms=bishopHelper(b,ms,-1,1,true);
    ms=bishopHelper(b,ms,1,1,true);
    ms=bishopHelper(b,ms,-1,0,true);
    ms=bishopHelper(b,ms,1,0,true);
    ms=bishopHelper(b,ms,0,1,true);
    ms=bishopHelper(b,ms,0,-1,true);


    //premature return for non current player king
    if(b.moveList.size() == 0 || b.moveList.get(b.moveList.size()-1).player == getPlayer() ){
      return ms;
    }
    //make sure is not moving to check
    MoveTypes[][] ts = b.ThreatSpace(getPlayer());
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        if(ts[x][y]!=MoveTypes.ILLE){
          ms[x][y]=MoveTypes.ILLE;
          if(x==location.getX() && y == location.getY()){
            moved();
          }
        }
      }
    }
    if(getState()==0){
      MoveTypes[][] ms2 = BaseSpace();

      ms2=bishopHelper(b,ms2,-1,0,false);
      ms2=bishopHelper(b,ms2,1,0,false);

      for(int i = 0; i !=location.getX(); i++){
        if(ts[i][location.getY()]!=MoveTypes.ILLE){
          ms2[1][location.getY()] = MoveTypes.ILLE;
          break;
        }
      }
      for(int i = 7; i != location.getX(); i--){
        if(ts[i][location.getY()]!=MoveTypes.ILLE){
          ms2[6][location.getY()] = MoveTypes.ILLE;
          break;
        }
      }
      if(ms2[1
              ][location.getY()] == MoveTypes.MOVE
              && b.getPiece(new Coordinates(0, location.getY())).getPieceType()==PieceType.ROOK
              && b.getPiece(new Coordinates(0, location.getY())).getState()==0
              ){

        ms[0][location.getY()]=MoveTypes.CAST;
      } if(ms2[6][location.getY()] == MoveTypes.MOVE
              && b.getPiece(new Coordinates(7, location.getY())).getPieceType()==PieceType.ROOK
              && b.getPiece(new Coordinates(7, location.getY())).getState()==0
              ){
        ms[7][location.getY()]=MoveTypes.CAST;
      }
    }



    return ms;
  }

  @Override
  public void moved() {
    if (getState()==0) state++;
  }
}
