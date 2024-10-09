package pieces;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import model.*;

public class King extends Piece {

    public King(int x, int y, Side side, Piece[][] board) {
        super(x, y, side, board);
        this.type = Type.King;
        this.character = 'K';

        predefinedMoves.add(new Field(1,0));
        predefinedMoves.add(new Field(1,1));
        predefinedMoves.add(new Field(1,-1));
        predefinedMoves.add(new Field(0,1));
        predefinedMoves.add(new Field(0,-1));
        predefinedMoves.add(new Field(-1,0));
        predefinedMoves.add(new Field(-1,1));
        predefinedMoves.add(new Field(-1,-1));

        loadIcon();
    }

    public void move(int x, int y) {
        if(x-posX > 1) {castleShort();}
        if(x-posX <- 1) {castleLong();}
        super.move(x, y);
        moved = true;
    }

    public void castleShort(){
        board[posX+3][posY].move(5, posY);
    }

    public void castleLong(){
        board[posX-4][posY].move(3, posY);
    }

    @Override
    public void take() {
        System.out.println("Game over!");
        if(side == Side.White) {
            System.out.println("Black wins.");
        }
        else {
            System.out.println("White wins.");
        }
    }

    @Override
    public List<Field> getMoves() {
        List<Field> possibleMoves = super.getMoves();
        if(moved){return possibleMoves;}
        //check if can castle short
        if(board[posX+3][posY] != null && board[posX+3][posY].type == Type.Rook
                && board[posX+1][posY] == null && board[posX+2][posY] == null){
            if(!board[posX+3][posY].moved){
                possibleMoves.add(new Field(posX + 2,posY));
            }
        }
        //check if can castle long
        if(board[posX-4][posY] != null && board[posX-4][posY].type == Type.Rook
                && board[posX-1][posY] == null && board[posX-2][posY] == null && board[posX-3][posY] == null){
            if(!board[posX-4][posY].moved){
                possibleMoves.add(new Field(posX - 2,posY));
            }
        }
        return possibleMoves;
    }
}
