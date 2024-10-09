package pieces;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import model.*;

public class Bishop extends Piece {

    public Bishop(int x, int y, Side side, Piece[][] board) {
        super(x, y, side, board);
        this.type = Type.Bishop;
        this.character = 'b';
        loadIcon();

        for(int i = -7; i <= 7; i++ ) {
            predefinedMoves.add(new Field(i, i));
            predefinedMoves.add(new Field(-i, i));
        }
    }
}
