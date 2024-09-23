import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class King extends Figure{

    boolean moved = false;

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
    }

    public void move(int x, int y) {
        super.move(x, y);
        moved = true;
    }

    @Override
    public void take() {
        Chess.setGameOver(side.getOpponent());
    }
}
