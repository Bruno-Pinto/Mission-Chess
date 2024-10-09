package model;

public class Field {

    private int x;
    private int y;
    private char charX;

    /**
     * For usage as a possible field to move to.
     */
    public Field(int x, int y) {
        this.x = x;
        this.y = y;

        switch (x){
            case 0: charX = 'A'; break;
            case 1: charX = 'B'; break;
            case 2: charX = 'C'; break;
            case 3: charX = 'D'; break;
            case 4: charX = 'E'; break;
            case 5: charX = 'F'; break;
            case 6: charX = 'G'; break;
            case 7: charX = 'H'; break;
        }
    }

    public int getX(){return x;}
    public int getY(){return y;}

    /**
     * Function to get a printable version of model.Field cords.
     * @return Returns a String value of the cords.
     */
    public String toString(){
//        return x++ + "," + y++;
        return charX + "" + (y+1);
    }
}