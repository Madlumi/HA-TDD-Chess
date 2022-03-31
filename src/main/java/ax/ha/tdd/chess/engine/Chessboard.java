package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chessboard implements Iterable<ChessPiece[]> {
    // This could just as easily be replaced with a List or Set,
    // since the ChessPieces right now keep track of their own location.
    // Feel free to change this however you like
    // [y][x]
    private final ChessPiece[][] board = new ChessPiece[8][8];

    public List<Move> moveList = new ArrayList<>();
    public static Chessboard startingBoard() {
        final Chessboard chessboard = new Chessboard();

        chessboard.withMirroredPiece(PieceType.PAWN, List.of(0,1,2,3,4,5,6,7), 1)
                .withMirroredPiece(PieceType.ROOK, List.of(0,7), 0)
                .withMirroredPiece(PieceType.KNIGHT, List.of(1,6), 0)
                .withMirroredPiece(PieceType.BISHOP, List.of(2,5), 0)
                .withMirroredPiece(PieceType.QUEEN, List.of(3), 0)
                .withMirroredPiece(PieceType.KING, List.of(4), 0);
        return chessboard;
    }

    public ChessPiece getPiece(final Coordinates coordinates) {
        return board[coordinates.getY()][coordinates.getX()];
    }

    public void addPiece(final ChessPiece chessPiece) {
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = chessPiece;
    }


    public  MoveTypes[][] ThreatSpace(Player player){
        MoveTypes[][] ms = ChessPiece.BaseSpace();

        for (int y = 0; y < 8; y++){
            for (int x = 0; x < 8; x++){
                if(getPiece(new Coordinates(x,y))!=null && getPiece(new Coordinates(x,y)).getPlayer()!=player){
                    MoveTypes[][] ms2 =
                            getPiece(new Coordinates(x,y)).moveSpace(this);

                    for (int yy = 0; yy < 8; yy++) {
                        for (int xx = 0; xx < 8; xx++) {
                            if(ms2[xx][yy]!=MoveTypes.ILLE){
                                ms[xx][yy]=ms2[xx][yy];
                            }
                        }

                    }
                }
            }
        }

        return ms;
    }


    public boolean MovePiece(final ChessPiece chessPiece, final Coordinates c) {
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = null;
        MoveTypes mt = chessPiece.moveSpace(this)[c.getX()][c.getY()];
        switch (mt){
            case MOVE:
                board[c.getY()][c.getX()] = chessPiece;
                break;
            case TAKE:
                board[c.getY()][c.getX()] = chessPiece;
                break;
            case ENPA:
                board[c.getY()][c.getX()] = chessPiece;
                switch (chessPiece.getPlayer()){
                    case WHITE:
                        board[c.getY()+1][c.getX()] = null;
                        break;
                    case BLACK:
                        board[c.getY()-1][c.getX()] = null;
                        break;
                }
                break;
            case CAST:
                ChessPiece rook= board[c.getY()][c.getX()];
                Coordinates c2 = chessPiece.getLocation();
                board[c.getY()][c.getX()] = chessPiece;
                board[c2.getY()][c2.getX()] = rook;
            case ILLE:
                return false;
        }
        chessPiece.setLocation(c);
        chessPiece.moved();
        return true;
    }

    private Chessboard withMirroredPiece(final PieceType pieceType,
                                         final List<Integer> xCoordinates, final int yCoordinate) {
        xCoordinates.forEach(xCoordinate -> {
            switch (pieceType){
                case PAWN:
                    addPiece(new Pawn(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                    addPiece(new Pawn(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
                    break;
                case BISHOP:
                    addPiece(new Bishop(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                    addPiece(new Bishop(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
                    break;
                case KNIGHT:
                    addPiece(new Horsie(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                    addPiece(new Horsie(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
                    break;
                case KING:
                    addPiece(new King(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                    addPiece(new King(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
                    break;
                case QUEEN:
                    addPiece(new Queen(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                    addPiece(new Queen(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
                    break;
                case ROOK:
                    addPiece(new Rook(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinate)));
                    addPiece(new Rook(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinate)));
                    break;
            }

        });
        return this;
    }

    @Override
    public Iterator<ChessPiece[]> iterator() {
        return List.of(board).iterator();
    }
}
