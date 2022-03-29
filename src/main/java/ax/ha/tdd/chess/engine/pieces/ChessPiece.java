package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.MoveTypes;
import ax.ha.tdd.chess.engine.Player;

import java.util.Objects;

public abstract class ChessPiece {

    protected final Player player;
    protected final PieceType pieceType;

    protected Coordinates location;

    public ChessPiece(PieceType pieceType, final Player player,
                      final Coordinates location) {
        this.pieceType = pieceType;
        this.player = player;
        this.location = location;
    }
    protected int state=0;

    public int getState(){
        return state;
    }
    public abstract String getSymbol();

    public PieceType getPieceType() { return pieceType; }

    public Player getPlayer() {
        return player;
    }

    public Coordinates getLocation() {
        return location;
    }
    public void setLocation(Coordinates c) {
        location = c;
    }
    public boolean canMove(final Chessboard chessboard, final Coordinates destination){
        MoveTypes[][] moveSpace =moveSpace(chessboard);
        if(moveSpace[destination.getX()][destination.getY()] != MoveTypes.ILLE){ return true;}
        else{return false;}
    }

    public abstract MoveTypes[][] moveSpace(final Chessboard chessboard);

    public abstract void moved();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return player == that.player && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, pieceType);
    }

    @Override
    public String toString() {
        return getPlayer().name() + " " + getClass().getSimpleName();
    }

    public void setState(int i) {
        state = i;
    }

    public MoveTypes[][] TowerSpace(Chessboard b, MoveTypes[][] ms) {
        int ydir= -1;
        int xdir= -1;
        ms=bishopHelper(b,ms,-1,0);
        ms=bishopHelper(b,ms,1,0);
        ms=bishopHelper(b,ms,0,1);
        ms=bishopHelper(b,ms,0,-1);
        return ms;
    }

    public MoveTypes[][] BishopSpace(Chessboard b, MoveTypes[][] ms) {
        int ydir= -1;
        int xdir= -1;
        ms=bishopHelper(b,ms,-1,-1);
        ms=bishopHelper(b,ms,1,-1);
        ms=bishopHelper(b,ms,-1,1);
        ms=bishopHelper(b,ms,1,1);
        return ms;
    }

    private MoveTypes[][] bishopHelper(Chessboard b, MoveTypes[][] moveSpace,int xdir,int ydir){
        int x = location.getX()+xdir;
        int y = location.getX()-ydir;
        while(x>=0&&y>=0 && x<8&&y<8){
            if(b.getPiece(new Coordinates(x, y))==null){
                moveSpace[x][ y]=MoveTypes.MOVE;
            }else if(b.getPiece(new Coordinates(x, y)).getPlayer()!=getPlayer()){
                moveSpace[x][ y]=MoveTypes.TAKE;
                break;
            }else{
                break;
            }
            x+=xdir;
            y-=ydir;
        }
        return moveSpace;
    }

}
