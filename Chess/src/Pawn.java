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

    @Override
    public List<Field> getMoves() {
        List<Field> possibleMoves = super.getMoves();
        if (side == Side.White) {
            possibleMoves.add(new Field(posX, posY+1));

            if(Chess.validPos(posX+1, posY+1) && Board[posX+1][posY+1] != null && Board[posX+1][posY+1].getColor() != side) {
                possibleMoves.add(new Field(posX+1, posY+1));
            }
            if(Chess.validPos(posX-1, posY+1) && Board[posX-1][posY+1] != null && Board[posX-1][posY+1].getColor() != side) {
                possibleMoves.add(new Field(posX-1,posY+1));
            }
            if(!moved) {
                possibleMoves.add(new Field(posX, posY+2));
            }
        }
        else {
            possibleMoves.add(new Field(posX, posY-1));

            if(Chess.validPos(posX+1, posY-1) && Board[posX+1][posY-1] != null && Board[posX+1][posY-1].getColor() != side) {
                possibleMoves.add(new Field(posX+1,posY-1));
            }
            if(Chess.validPos(posX-1, posY-1) && Board[posX-1][posY-1] != null && Board[posX-1][posY-1].getColor() != side) {
                possibleMoves.add(new Field(posX-1,posY-1));
            }
            if(!moved) {
                possibleMoves.add(new Field(posX, posY-2));
            }
        }

        return possibleMoves;
    }

}
