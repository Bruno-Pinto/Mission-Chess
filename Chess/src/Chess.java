import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Chess {

    public Figure[][] Board = new Figure[8][8];

    public static void main(String[] args) {



    }

    public void setup(){
        for(int x=0; x<8; x++) {
            for(int y=0; y<8; y++) {

                if(y>2 && y<7) {
                    continue;
                }

                if(y==2) {
                    Board[x][y] = new Pawn(x, y, Color.White);
                }
                if(y==7) {
                    Board[x][y] = new Pawn(x, y, Color.Black);
                }

            }
        }
    }

    public boolean validPos(int x, int y) {
        return x<=8 && x>=0 && y<=8 && y>=0;
    }
}