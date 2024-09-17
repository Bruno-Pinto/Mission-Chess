import java.util.Map;

public class Pawn extends Figure {

    boolean moved = false;

    Pawn(int x, int y, Color color) {
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.type = Type.Pawn;
        this.character = 'p';
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        moved = true;
    }

    @Override
    public Map<Integer, Integer> getMoves() {
        if (color==Color.White) {
            moves.put(posX, posY+1);

            if(Chess.validPos(posX+1, posY+1) && Board[posX+1][posY+1] != null && Board[posX+1][posY+1].getColor() != color) {
                moves.put(posX+1,posY+1);
            }
            if(Chess.validPos(posX-1, posY+1) && Board[posX-1][posY+1] != null && Board[posX-1][posY+1].getColor() != color) {
                moves.put(posX-1,posY+1);
            }
            if(!moved) {
                moves.put(posX, posY+2);
            }
        }
        else {
            moves.put(posX, posY-1);

            if(Chess.validPos(posX+1, posY-1) && Board[posX+1][posY-1] != null && Board[posX+1][posY-1].getColor() != color) {
                moves.put(posX+1,posY-1);
            }
            if(Chess.validPos(posX-1, posY-1) && Board[posX-1][posY-1] != null && Board[posX-1][posY-1].getColor() != color) {
                moves.put(posX-1,posY-1);
            }
            if(!moved) {
                moves.put(posX, posY-2);
            }
        }

        return moves;
    }
}
