public class Field {

    public int x;
    public int y;
    Figure f;

    /**
     * For usage as a possible field to move to.
     * @param x
     * @param y
     */
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}
}