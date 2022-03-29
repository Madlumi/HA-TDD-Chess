package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Pawn;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveTest {

  public static String[] grid2String(MoveTypes[][] a, MoveTypes[][] e){
    //convert to string for readability
    String[] s = new String[2];
    s[0]="";
    s[1]="";
    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        s[0]+=e[x][y]+"|";
        s[1]+=a[x][y]+"|";
      }
      s[0]+="\n";
      s[1]+="\n";
    }
    return s;
  }

  @Test
  public void TestStartPawnBlack() {
    final Chessboard chessboard = Chessboard.startingBoard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(3, 1)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
      }
    }
    ExpctedMs[3][2]=MoveTypes.MOVE;
    ExpctedMs[3][3]=MoveTypes.MOVE;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }
  @Test
  public void TestMovePawnBlack() {
    final Chessboard chessboard = Chessboard.startingBoard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    chessboard.MovePiece(chessboard.getPiece(new Coordinates(3,1)), new Coordinates(3,3) );

    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(3, 3)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
      }
    }

    ExpctedMs[3][4]=MoveTypes.MOVE;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }

  @Test
  public void TestStartPawnWhite() {
    final Chessboard chessboard = Chessboard.startingBoard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(3, 6)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
      }
    }
    ExpctedMs[3][5]=MoveTypes.MOVE;
    ExpctedMs[3][4]=MoveTypes.MOVE;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }

  @Test
  public void TestStartPawnMoveAllowed() {
    final Chessboard chessboard = Chessboard.startingBoard();
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(3, 1)).moveSpace(chessboard);
    chessboard.getPiece(new Coordinates(3, 1)).canMove(chessboard,new Coordinates(3,2));

    int[][] ExpctedMs = new int[8][8];
    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=0;
      }
    }
    ExpctedMs[3][2]=1;
    ExpctedMs[3][3]=1;
    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++) {
        if(ExpctedMs[x][y]==0){
          Assertions.assertEquals(false, chessboard.getPiece(new Coordinates(3, 1)).canMove(chessboard,new Coordinates(x,y)));
        }else{
          Assertions.assertEquals(true, chessboard.getPiece(new Coordinates(3, 1)).canMove(chessboard,new Coordinates(x,y)));
        }

      }
    }


  }

  @Test
  public void TestPawnBlack() {
    final Chessboard chessboard = Chessboard.startingBoard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(4,2)));
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(3, 1)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
      }
    }
    ExpctedMs[3][2]=MoveTypes.MOVE;
    ExpctedMs[3][3]=MoveTypes.MOVE;
    ExpctedMs[4][2]=MoveTypes.TAKE;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }


  @Test
  public void TestPawnBlackEnPes() {
    Game g = new Game();
    g.board= new Chessboard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    g.board.addPiece(new Pawn(PieceType.PAWN, Player.BLACK, new Coordinates(3,4)));
    g.board.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(4,6)));

    //chessboard.MovePiece(chessboard.getPiece(new Coordinates(4,6)),new Coordinates(4,4));
    g.move("57-55");
    //moveEnpes
    MoveTypes[][] ActualMS= g.board.getPiece(new Coordinates(3, 4)).moveSpace(g.board);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
      }
    }
    ExpctedMs[3][5]=MoveTypes.MOVE;
    ExpctedMs[3][6]=MoveTypes.MOVE;
    ExpctedMs[4][5]=MoveTypes.ENPA;

    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }
}
