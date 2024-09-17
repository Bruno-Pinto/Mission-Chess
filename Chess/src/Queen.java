public class Queen extends Figure{

    Queen(int x, int y, Color color) {
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.type = Type.Queen;
        this.character = 'Q';
    }
}
