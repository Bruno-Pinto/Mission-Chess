public class King extends Figure{

    boolean moved = false;

    King(int x, int y, Color color) {
        this.posX = x;
        this.posY = y;
        this.color = color;
        this.type = Type.King;
        this.character = 'K';
    }

    public void move(int x, int y) {
        super.move(x, y);
        moved = true;
    }
}
