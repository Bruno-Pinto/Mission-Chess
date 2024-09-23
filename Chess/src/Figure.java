import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Figure {
    int posX;
    int posY;
    Type type;
    Side side;
    List<Move> moves;
    Character character;
    Image icon;
    public static Figure[][] Board = Chess.Board;
    Status status = Status.visible;

    public void move(int x, int y){
        posX = x;
        posY = y;
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public Side getColor() {return side;}

    public void take(){
        status = Status.invisible;
        Board[posX][posY] = null;
    }

    public void setStatus(Status status){this.status = status;}
}

