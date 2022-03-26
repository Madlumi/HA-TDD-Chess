package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
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

    public abstract String getSymbol();

    public PieceType getPieceType() { return pieceType; }

    public Player getPlayer() {
        return player;
    }

    public Coordinates getLocation() {
        return location;
    }


    /**
     * Suggestion of design:
     * Checks if the chessPiece can move to a certain destination.
     * I preferred to keep this logic tightly coupled to the pieces instead of the board,
     * since that makes the separation of logic easier.
     *
     * @param chessboard chessboard
     * @param destination destination
     * @return true if piece can move to the destination
     */
    public boolean canMove(final Chessboard chessboard, final Coordinates destination){
        int[][] moveSpace =moveSpace(chessboard);
        if(moveSpace[destination.getX()][destination.getY()] > 0){ return true;}
        else{return false;}
    }

    public abstract int[][] moveSpace(final Chessboard chessboard);

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
}
