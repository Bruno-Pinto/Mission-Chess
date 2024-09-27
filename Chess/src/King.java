import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class King extends Figure{

    King(int x, int y, Side side) {
        this.posX = x;
        this.posY = y;
        this.side = side;
        this.type = Type.King;
        this.character = 'K';
        try {
            this.icon = ImageIO.read(new File(Chess.iconFilePath.concat(type.toString() + side + ".png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Moves.add(new Field(1,0));
        Moves.add(new Field(1,1));
        Moves.add(new Field(1,-1));
        Moves.add(new Field(0,1));
        Moves.add(new Field(0,-1));
        Moves.add(new Field(-1,0));
        Moves.add(new Field(-1,1));
        Moves.add(new Field(-1,-1));

    }

    public void move(int x, int y) {
        if(x>1) {castleShort();}
        if(x<-1) {castleLong();        }
        super.move(x, y);
        moved = true;
    }

    public void castleShort(){
        Chess.Board[posX+3][posY].move(5, posY);
    }

    public void castleLong(){
        Chess.Board[posX-4][posY].move(3, posY);
    }

    @Override
    public void take() {
        Chess.setGameOver(side.getOpponent());
    }

    @Override
    public List<Field> getMoves() {
        List<Field> possibleMoves = super.getMoves();
        if(moved){return possibleMoves;}
        //check if can castle short
        if(Board[posX+3][posY] != null && Board[posX+3][posY].type == Type.Rook){
            if(!Board[posX+3][posY].moved){
                possibleMoves.add(new Field(0,2));
            }
        }
        //check if can castle long
        if(Board[posX-4][posY] != null && Board[posX-4][posY].type == Type.Rook){
            if(!Board[posX-4][posY].moved){
                possibleMoves.add(new Field(0,-2));
            }
        }
        return possibleMoves;
    }
}
