package pieces;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import model.*;

public class Queen extends Piece {

    public Queen(int x, int y, Side side, Piece[][] board) {
        super(x, y, side, board);
        this.type = Type.Queen;
        this.character = 'Q';
        loadIcon();

        for(int i = -7; i <= 7; i++ ) {
            predefinedMoves.add(new Field(i, i));
            predefinedMoves.add(new Field(-i, i));
            predefinedMoves.add(new Field(0, i));
            predefinedMoves.add(new Field(i, 0));
        }
    }
}
