public class Rook extends Figure{

    boolean moved = false;

    Rook(int x, int y, Color color) {
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.type = Type.Rook;
        this.character = 'R';
    }

    public void move(int x, int y) {
        super.move(x, y);
        moved = true;
    }
}
