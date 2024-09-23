public class Move {

    public int x;
    public int y;
    Figure f;

    public Move(int x, int y, Figure f) {
        this.x = x;
        this.y = y;
        this.f = f;
    }

    public int getX(){return x;}
    public int getY(){return y;}
}