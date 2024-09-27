import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Rook extends Figure{

    Rook(int x, int y, Side side) {
        this.posX = x;
        this.posY = y;
        this.side = side;
        this.type = Type.Rook;
        this.character = 'R';
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
}
