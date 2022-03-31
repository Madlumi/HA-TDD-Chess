package ax.ha.tdd.chess.console;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Coordinates;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessboardWriterTest {

    @Test
    public void print_emptyChessboard_printsOkay() {
        final String expectedChessboard= 
                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +
                                            
                " *    *    *    *    *    *    *    * \n\n" +
                                            
                " *    *    *    *    *    *    *    * \n\n" +
                                            
                " *    *    *    *    *    *    *    * \n\n" +
                                            
                " *    *    *    *    *    *    *    * \n";
        Assertions.assertEquals(expectedChessboard, new ChessboardWriter().print(new Chessboard()));
    }

    @Test
    public void print_chessboardWithSinglePawn_printsOkay() {
        final String expectedChessboard=
                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *   B-P   *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n";
        Chessboard chessboard = new Chessboard();
        chessboard.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(2, 4)));
        Assertions.assertEquals(expectedChessboard, new ChessboardWriter().print(chessboard));
    }

    @Test
    public void print_fullChessboard_printsOkay() {
        final String expectedChessboard=
               "B-R  B-K  B-B  B-Q  B-K  B-B  B-K  B-R\n\n" +
                                            
               "B-P  B-P  B-P  B-P  B-P  B-P  B-P  B-P\n\n" +
                                            
               " *    *    *    *    *    *    *    * \n\n" +
                                            
               " *    *    *    *    *    *    *    * \n\n" +
                                            
               " *    *    *    *    *    *    *    * \n\n" +
                                            
               " *    *    *    *    *    *    *    * \n\n" +
                                            
               "W-P  W-P  W-P  W-P  W-P  W-P  W-P  W-P\n\n" +
                                            
               "W-R  W-K  W-B  W-Q  W-K  W-B  W-K  W-R\n";
        final Chessboard chessboard = Chessboard.startingBoard();

        Assertions.assertEquals(expectedChessboard, new ChessboardWriter().print(chessboard));
    }
}
