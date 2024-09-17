public class Bishop extends Figure{

    Bishop(int x, int y, Color color) {
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.type = Type.Bishop;
        this.character = 'b';
    }
}
