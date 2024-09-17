import java.util.*;
import java.util.function.IntUnaryOperator;

public class Figure {
    int posX;
    int posY;
    Type type;
    Color color;
    Map<Integer, Integer> moves;
    Character character;
    public static Figure[][] Board = Chess.Board;

    public void move(int x, int y){
        posX = x;
        posY = y;
    }

    public Map<Integer, Integer> getMoves() {
        return this.moves;
    }

    public Color getColor() {return color;}

}

