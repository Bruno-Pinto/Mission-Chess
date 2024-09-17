import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Chess {

    public static Figure[][] Board = new Figure[8][8];

    public static void main(String[] args) {

        setup();
        printBoard();



    }

    public static void setup(){
        for(int x=0; x<8; x++) {
            for(int y=0; y<8; y++) {

                if(y>1 && y<6) {
                    continue;
                }

                if(y==1) {
                    Board[x][y] = new Pawn(x, y, Color.White);
                }
                else if(y==6) {
                    Board[x][y] = new Pawn(x, y, Color.Black);
                }
                else if(y==0 && (x==0 || x==7)) {
                    Board[x][y] = new Rook(x, y, Color.White);
                }
                else if(y==7 && (x==0 || x==7)) {
                    Board[x][y] = new Rook(x, y, Color.Black);
                }
                else if(y==0 && (x==1 || x==6)) {
                    Board[x][y] = new Knight(x, y, Color.White);
                }
                else if(y==7 && (x==1 || x==6)) {
                    Board[x][y] = new Knight(x, y, Color.Black);
                }
                else if(y==0 && (x==2 || x==5)) {
                    Board[x][y] = new Bishop(x, y, Color.White);
                }
                else if(y==7 && (x==2 || x==5)) {
                    Board[x][y] = new Bishop(x, y, Color.Black);
                }
                else if(y==0 && x==3) {
                    Board[x][y] = new Queen(x, y, Color.White);
                }
                else if(y==7 && x==3) {
                    Board[x][y] = new Queen(x, y, Color.Black);
                }
                else if(y==0 && x==4) {
                    Board[x][y] = new King(x, y, Color.White);
                }
                else if(y==7 && x==4) {
                    Board[x][y] = new King(x, y, Color.Black);
                }
            }
        }
    }

    public static void printBoard() {
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {

                if(Board[j][i] == null) {
                    System.out.print(' ');
                    continue;
                }
                System.out.print(Board[j][i].character + " ");
            }
            System.out.println();
        }
    }

    public static boolean validPos(int x, int y) {
        return x<=8 && x>=0 && y<=8 && y>=0;
    }
}