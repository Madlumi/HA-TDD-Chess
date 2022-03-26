package ax.ha.tdd.chess.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveTest {

  public String[] grid2String(int[][] a, int[][] e){
    //convert to string for readability
    String[] s = new String[2];
    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        s[0]+=e[x][y];
        s[1]+=a[x][y];
      }
      s[0]+="\n";
      s[1]+="\n";
    }
    return s;
  }

  @Test
  public void TestStartPawnBlack() {
    final Chessboard chessboard = Chessboard.startingBoard();
    int[][] ExpctedMs = new int[8][8];
    int[][] ActualMS= chessboard.getPiece(new Coordinates(3, 1)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=0;
      }
    }
    ExpctedMs[3][2]=1;
    ExpctedMs[3][3]=1;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }

  @Test
  public void TestStartPawnWhite() {
    final Chessboard chessboard = Chessboard.startingBoard();
    int[][] ExpctedMs = new int[8][8];
    int[][] ActualMS= chessboard.getPiece(new Coordinates(3, 6)).moveSpace(chessboard);

    for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        ExpctedMs[x][y]=0;
      }
    }
    ExpctedMs[3][5]=1;
    ExpctedMs[3][4]=1;
    String[] s=grid2String(ExpctedMs, ActualMS);

    Assertions.assertEquals(s[0],s[1]);
  }
}
