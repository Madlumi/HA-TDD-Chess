package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.*;
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
  public void TestStartPawnWhiteSide() {
    final Chessboard chessboard = Chessboard.startingBoard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(0, 6)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
      }
    }
    ExpctedMs[0][5]=MoveTypes.MOVE;
    ExpctedMs[0][4]=MoveTypes.MOVE;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }
  @Test
  public void TestStartPawnWhiteFarSide() {
    final Chessboard chessboard = Chessboard.startingBoard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(7, 6)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
      }
    }
    ExpctedMs[7][5]=MoveTypes.MOVE;
    ExpctedMs[7][4]=MoveTypes.MOVE;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }

  @Test
  public void TestStartPawnWhiteTallSide() {
    final Chessboard chessboard = new Chessboard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    chessboard.addPiece(new Pawn(PieceType.PAWN, Player.WHITE, new Coordinates(7,0)));
    chessboard.getPiece(new Coordinates(7,0)).setState(1);
    MoveTypes[][] ActualMS=
            chessboard.getPiece(new Coordinates(7, 0)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
      }
    }

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
    g.move("45-56");
    System.out.println(g.getLastMoveResult());
    Assertions.assertEquals(g.board.getPiece(new Coordinates(3 , 4 )),null);
    Assertions.assertEquals(g.board.getPiece(new Coordinates(4 , 4 )),null);
    Assertions.assertEquals(g.board.getPiece(new Coordinates(4 , 5 )).getPieceType(),PieceType.PAWN);
    Assertions.assertEquals(g.board.getPiece(new Coordinates(4 , 5 )).getPlayer(),Player.BLACK);

  }


  @Test
  public void TestTower() {
    final Chessboard chessboard = new Chessboard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(4,4)));

    chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(2,4)));
    chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(6,4)));
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(4, 4)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
        if((x==4||y==4)&&x<6&&x>2){
          ExpctedMs[x][y]=MoveTypes.MOVE;
        }
      }
    }
    ExpctedMs[4][4]=MoveTypes.ILLE;
    ExpctedMs[6][4]=MoveTypes.TAKE;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }
  @Test
  public void TestTower2() {
    final Chessboard chessboard = new Chessboard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(0, 0)));

    MoveTypes[][] ActualMS = chessboard.getPiece(new Coordinates(0, 0)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        ExpctedMs[x][y] = MoveTypes.ILLE;
        if ((x == 0 || y == 0)) {
          ExpctedMs[x][y] = MoveTypes.MOVE;
        }
      }
    }
    ExpctedMs[0][0] = MoveTypes.ILLE;

    String[] s = grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0], s[1]);
  }

  @Test
  public void TestTower3() {
    final Chessboard chessboard = new Chessboard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(7,7)));

    MoveTypes[][] ActualMS = chessboard.getPiece(new Coordinates(7, 7)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        ExpctedMs[x][y] = MoveTypes.ILLE;
        if ((x == 7|| y == 7)) {
          ExpctedMs[x][y] = MoveTypes.MOVE;
        }
      }
    }
    ExpctedMs[7][7] = MoveTypes.ILLE;

    String[] s = grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0], s[1]);
  }

  @Test
  public void TestBish() {
    final Chessboard chessboard = new Chessboard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    chessboard.addPiece(new Bishop(PieceType.BISHOP, Player.WHITE, new Coordinates(4,4)));

    //chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(2,4)));
    //chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(6,4)));
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(4, 4)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
        if(Math.abs(x-4)==Math.abs(y-4)){
          ExpctedMs[x][y]=MoveTypes.MOVE;
        }
      }
    }
    ExpctedMs[4][4]=MoveTypes.ILLE;

    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);



  }
  @Test
  public void TestQeeen() {
    final Chessboard chessboard = new Chessboard();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    chessboard.addPiece(new Queen(PieceType.QUEEN, Player.WHITE, new Coordinates(4,4)));

    //chessboard.addPiece(new Rook(PieceType.ROOK, Player.WHITE, new Coordinates(2,4)));
    //chessboard.addPiece(new Rook(PieceType.ROOK, Player.BLACK, new Coordinates(6,4)));
    MoveTypes[][] ActualMS= chessboard.getPiece(new Coordinates(4, 4)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=MoveTypes.ILLE;
        if(Math.abs(x-4)==Math.abs(y-4)||(x==4||y==4)){
          ExpctedMs[x][y]=MoveTypes.MOVE;
        }
      }
    }
    ExpctedMs[4][4]=MoveTypes.ILLE;

    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);



  }
}





