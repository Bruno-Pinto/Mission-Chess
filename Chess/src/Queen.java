import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Queen extends Figure{

    Queen(int x, int y, Side side) {
        this.posX = x;
        this.posY = y;
        this.side = side;
        this.type = Type.Queen;
        this.character = 'Q';
        try {
            this.icon = ImageIO.read(new File(Chess.iconFilePath.concat(type.toString() + side + ".png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
