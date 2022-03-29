package ax.ha.tdd.chess.engine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {


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
