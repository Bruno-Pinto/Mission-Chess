public class Knight extends Figure{

    Knight(int x, int y, Color color) {
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.type = Type.Knight;
        this.character = 'k';
    }
}
