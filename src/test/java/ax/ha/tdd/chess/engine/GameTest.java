package ax.ha.tdd.chess.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {


  @Test
  public void valideInputAndMoveTest() {
      Game g = new Game();
      MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
      //g.getBoard().MovePiece(g.getBoard().
      //        getPiece(new Coordinates(3,1)), new Coordinates(3,3) );
      g.move("42-44");
      MoveTypes[][] ActualMS= g.getBoard().getPiece(new Coordinates(3, 3)).moveSpace(g.getBoard());

      for (int y = 0; y < 8; y++){
        for (int x = 0; x < 8; x++){
          ExpctedMs[x][y]=MoveTypes.ILLE;
        }
      }

      ExpctedMs[3][4]=MoveTypes.MOVE;
      String[] s=MoveTest.grid2String(ExpctedMs, ActualMS);

      Assertions.assertEquals(s[0],s[1]);
    }
  @Test
  public void valideInputAndLastTest() {
    Game g = new Game();
    MoveTypes[][] ExpctedMs = new MoveTypes[8][8];
    //g.getBoard().MovePiece(g.getBoard().
    //        getPiece(new Coordinates(3,1)), new Coordinates(3,3) );
    Assertions.assertEquals("Game hasn't begun",g.getLastMoveResult());
    g.move("42-44");
    Assertions.assertEquals("Last move: d7 moved to d5",g.getLastMoveResult());
    g.move("44-45");
    Assertions.assertEquals("Last move: d5 moved to d4",g.getLastMoveResult());
    g.move("45-45");
    Assertions.assertEquals("Illegal move! Last move: d5 moved to d4",g.getLastMoveResult());
    g.move("aa-45");
    Assertions.assertEquals("Illegal move! Last move: d5 moved to d4",g.getLastMoveResult());
  }


  @Test
  public void valideInputTest() {
    String inString = "e4-d5\n";
    Coordinates[] c = {new Coordinates(4,4), new Coordinates(3,3) };
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString)[0],c[0]);
    Assertions.assertEquals(g.validateInput(inString)[1],c[1]);
  }


  @Test
  public void valideInputTest2() {
    String inString = "55-44\n";
    Coordinates[] c = {new Coordinates(4,4), new Coordinates(3,3) };
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString)[0],c[0]);
    Assertions.assertEquals(g.validateInput(inString)[1],c[1]);
  }
  @Test
  public void valideInputTest3() {
    String inString = "81-85\n";
    Coordinates[] c = {new Coordinates(7,0), new Coordinates(7,4) };
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString)[0],c[0]);
    Assertions.assertEquals(g.validateInput(inString)[1],c[1]);
  }
  @Test
  public void valideInputTest4() {
    String inString = "11-12\n";
    Coordinates[] c = {new Coordinates(0,0), new Coordinates(0,1) };
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString)[0],c[0]);
    Assertions.assertEquals(g.validateInput(inString)[1],c[1]);
  }


  @Test
  public void valideInvalidInputTest() {
    //you cannot move to the same squere
    String inString = "e5-e5\n";
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString),null);
  }


  @Test
  public void valideInvalidInputTest1() {
    String inString = "e5-q4\n";
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString),null);
  }
  @Test
  public void valideInvalidInputTest2() {
    String inString = "\n";
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString),null);
  }
  @Test
  public void valideInvalidInputTest3() {
    String inString = "01-22\n";
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString),null);
  }
  @Test
  public void valideInvalidInputTest4() {
    String inString = "91-22\n";
    Game g = new Game();
    Assertions.assertEquals(g.validateInput(inString),null);
  }


}
