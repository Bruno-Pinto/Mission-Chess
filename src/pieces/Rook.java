package pieces;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import model.*;

public class Rook extends Piece {

    public Rook(int x, int y, Side side, Piece[][] board) {
        super(x, y, side, board);
        this.type = Type.Rook;
        this.character = 'R';
        loadIcon();

        for(int i = -7; i <= 7; i++ ) {
            predefinedMoves.add(new Field(0, i));
            predefinedMoves.add(new Field(i, 0));
        }
    }

    public void move(int x, int y) {
        super.move(x, y);
        moved = true;
    }
}
