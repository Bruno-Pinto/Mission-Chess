import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Figure {
    int posX;
    int posY;
    boolean moved = false;
    Type type;
    Side side;
    List<Field> Moves = new ArrayList<Field>();
    Character character;
    Image icon;
    public static Figure[][] Board = Chess.Board;
    Status status = Status.visible;

    public void move(int x, int y){

        if(Board[x][y] != null){
            Board[x][y].take();
        }
        posX = x;
        posY = y;
        Board[x][y] = this;
    }

    /**
     * Checks if the moves are possible from posX,posY.
     * Doesn't check for collision in Board.
     * @return All possible moves within board.
     */
    public List<Field> getMoves() {
        List<Field> possibleMoves = Moves;
        possibleMoves.removeIf(f -> !Chess.validPos(posX + f.getX(), posY + f.getY()));
        possibleMoves.removeIf(f -> Chess.Board[posX + f.getX()][posY + f.getY()] != null && Chess.Board[posX + f.getX()][posY + f.getY()].getColor() != side);
        return possibleMoves;
    }

    public Side getColor() {return side;}

    public void take(){
        status = Status.invisible;
        Board[posX][posY] = null;
    }

    public void setStatus(Status status){this.status = status;}
}

