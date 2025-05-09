package pieces;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import model.*;

public class Pawn extends Piece {

    boolean moved = false;

    public Pawn(int x, int y, Side side, Piece[][] board) {
        super(x, y, side, board);
        this.type = Type.Pawn;
        this.character = 'p';
        loadIcon();
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

            if(validPos(posX, posY+1) && board[posX][posY+1] == null) {
                possibleMoves.add(new Field(posX, posY+1));
            }
            if(validPos(posX+1, posY+1) && board[posX+1][posY+1] != null && board[posX+1][posY+1].getSide() != side) {
                possibleMoves.add(new Field(posX+1, posY+1));
            }
            if(validPos(posX-1, posY+1) && board[posX-1][posY+1] != null && board[posX-1][posY+1].getSide() != side) {
                possibleMoves.add(new Field(posX-1,posY+1));
            }
            if(!moved && board[posX][posY+2] == null && board[posX][posY+1] == null) {
                possibleMoves.add(new Field(posX, posY+2));
            }
        }
        else {
            if(validPos(posX, posY-1) && board[posX][posY-1] == null) {
                possibleMoves.add(new Field(posX, posY-1));
            }
            if(validPos(posX+1, posY-1) && board[posX+1][posY-1] != null && board[posX+1][posY-1].getSide() != side) {
                possibleMoves.add(new Field(posX+1,posY-1));
            }
            if(validPos(posX-1, posY-1) && board[posX-1][posY-1] != null && board[posX-1][posY-1].getSide() != side) {
                possibleMoves.add(new Field(posX-1,posY-1));
            }
            if(!moved && board[posX][posY-2] == null && board[posX][posY-1] == null) {
                possibleMoves.add(new Field(posX, posY-2));
            }
        }

        return possibleMoves;
    }

}
