import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static int size;

    GamePanel(int size) {
        GamePanel.size = size;
        this.setPreferredSize(new Dimension(size, size));
    }

    @Override
    public void run() {

    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D)g;
        g2D.setColor(Color.getHSBColor(0.08f, 0.7f, 0.3f));

        int squareSize = size/8;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if((i+j) % 2 == 1){
                    g2D.fillRect(i*squareSize, j*squareSize, squareSize, squareSize);
                }
            }
        }
    }

    public void drawBoard(Graphics g, int boardSize, int squareSize, int gap){

        Graphics2D g2D = (Graphics2D)g;
        g2D.setFont(new Font("Arial", Font.BOLD, 80));

        for(Figure[] row : Chess.Board){
            for(Figure f : row){
                if(f==null){continue;}
                if(f.side == Side.Black){
                    g2D.setColor(Color.black);
                }
                else{
                    g2D.setColor(Color.white);
                }
                if(f.icon != null){
                    g2D.drawImage(f.icon,f.posX * Chess.squareSize, boardSize - (f.posY +1) * squareSize, squareSize, squareSize, this );
                    System.out.println("Drawing Image " + f.type + " " + f.side);
                    continue;
                }
                if(f.side == Side.White){g2D.setColor(Color.white);}
                else{g2D.setColor(Color.BLACK);}
                g2D.drawString(String.valueOf(f.character), f.posX * squareSize + squareSize/6, (boardSize - f.posY * squareSize) - gap);
            }
        }
    }
}
