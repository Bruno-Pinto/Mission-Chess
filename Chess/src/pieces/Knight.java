package pieces;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import model.*;

public class Knight extends Piece {

    public Knight(int x, int y, Side side, Piece[][] board) {
        super(x, y, side, board);
        this.type = Type.Knight;
        this.character = 'k';
        loadIcon();

        predefinedMoves.add(new Field(1,2));
        predefinedMoves.add(new Field(2,1));
        predefinedMoves.add(new Field(-1,2));
        predefinedMoves.add(new Field(-2,1));
        predefinedMoves.add(new Field(1,-2));
        predefinedMoves.add(new Field(2,-1));
        predefinedMoves.add(new Field(-1,-2));
        predefinedMoves.add(new Field(-2,-1));
    }
}
