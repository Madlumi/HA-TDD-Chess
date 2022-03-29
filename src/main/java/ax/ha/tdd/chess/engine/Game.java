package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;

public class Game {

    Chessboard board = Chessboard.startingBoard();

    //Feel free to delete this stuff. Just for initial testing.
    boolean isNewGame = true;
    Player cPlayer=Player.WHITE;
    boolean moveFail=false;



    public Player getPlayerToMove() {
        return cPlayer;
    }

    public Chessboard getBoard() {
        return board;
    }

    public String getLastMoveResult() {
        String s = "";
        if(moveFail){s+="Illegal move! ";}

        if (isNewGame) {
            s+= "Game hasn't begun";
            return s;
        }

        s+="Last move: ";
        s+=board.moveList.get(board.moveList.size()-1).c[0].toAlgebraic();
        s+=" moved to ";
        s+=board.moveList.get(board.moveList.size()-1).c[1].toAlgebraic();
        return s;
    }

    public void move(String move) {
        moveFail=true;
        //validates move
        Coordinates[] m = validateInput(move);
        if(m==null){
            return;
        }
        ChessPiece cp = board.getPiece(m[0]);
        if(cp == null){
            return;
        }
        if(cp.getPlayer()!=cPlayer){
            return;
        }
        if(!cp.canMove(board, m[1])){
            return;
        }
        //executes move
        board.MovePiece(cp,m[1]);
        board.moveList.add(new Move(cp.getPieceType(),cPlayer,m));
        //switch player
        if(cPlayer==Player.WHITE){
            cPlayer=Player.BLACK;
        }else{
            cPlayer=Player.WHITE;
        }
        isNewGame = false;
        moveFail=false;
    }

    //validate input is valid
    public Coordinates[] validateInput(String move){
        move=move.trim();
        Coordinates[] c = new Coordinates[2];
        //assumes stings must be 5 chatacters
        if(move.length()==5 && move.charAt(2)=='-' ){
            boolean isNum=true;
            String a= move.substring(0,2);
            String b= move.substring(3,5);

            //check if alphanumeric
            if (a.matches(".*[a-h].*")&&a.matches(".*[1-8].*")&&
                    b.matches(".*[a-h].*")&&b.matches(".*[1-8].*")){
                c[0]=new Coordinates(a);
                c[1]=new Coordinates(b);
                if(c[1].equals(c[0])){ return null;}
                return c;
            //else check if numeric
            }else if (!a.matches(".*[^1-8].*") && !b.matches(".*[^1-8].*")) {
                c[0]=new Coordinates(
                        Integer.parseInt(a.substring(0,1))-1,Integer.parseInt(a.substring(1,2))-1);
                c[1]=new Coordinates(
                        Integer.parseInt(b.substring(0,1))-1,Integer.parseInt(b.substring(1,2))-1);
                if(c[1].equals(c[0])){ return null;}
                return c;
            }
            //else input must be invalid, thus let it run to return null
        }

       return null;


    }
}
