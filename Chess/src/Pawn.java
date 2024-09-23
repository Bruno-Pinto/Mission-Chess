import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Pawn extends Figure {

    boolean moved = false;

    Pawn(int x, int y, Side side) {
        this.posX = x;
        this.posY = y;
        this.side = side;
        this.type = Type.Pawn;
        this.character = 'p';
        try {
            this.icon = ImageIO.read(new File(Chess.iconFilePath.concat(type.toString() + side + ".png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void move(int x, int y) {
        super.move(x, y);
        moved = true;
    }
    /*
    @Override
    public List<Move> getMoves() {
        if (side == Side.White) {
            moves.add(new Move(posX, posY+1,this));

            if(Chess.validPos(posX+1, posY+1) && Board[posX+1][posY+1] != null && Board[posX+1][posY+1].getColor() != side) {
                moves.add(new Move(posX+1, posY+1, this));
            }
            if(Chess.validPos(posX-1, posY+1) && Board[posX-1][posY+1] != null && Board[posX-1][posY+1].getColor() != side) {
                moves.put(posX-1,posY+1);
            }
            if(!moved) {
                moves.put(posX, posY+2);
            }
        }
        else {
            moves.put(posX, posY-1);

            if(Chess.validPos(posX+1, posY-1) && Board[posX+1][posY-1] != null && Board[posX+1][posY-1].getColor() != side) {
                moves.put(posX+1,posY-1);
            }
            if(Chess.validPos(posX-1, posY-1) && Board[posX-1][posY-1] != null && Board[posX-1][posY-1].getColor() != side) {
                moves.put(posX-1,posY-1);
            }
            if(!moved) {
                moves.put(posX, posY-2);
            }
        }

        return moves;
    }
     */
}
