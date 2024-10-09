import model.Field;
import model.Side;
import pieces.Piece;

import java.awt.*;
import java.awt.Color;
import java.util.List;
import javax.swing.*;

public class GamePanel extends JPanel{

    static int boardSize;
    static int squareSize;
    static int gap;
    private Piece[][] board;
    static Color translucentBlack = new Color(0,0,0, 70);
    private List<Field> highlightedFields;

    GamePanel(int size, Piece[][] board) {
        GamePanel.boardSize = size;
        squareSize = size/8;
        this.setPreferredSize(new Dimension(size, size));
        this.board = board;
        gap = squareSize/8;
    }

    public void paint(Graphics g){
        Graphics2D G2D = (Graphics2D)g;
        invertY(G2D);
        drawBoard(G2D);
        drawPieces(G2D);
    }

    /**
     * Resets previously highlighted fields and paints new highlights.
     * @param possibleMoves New fields to be highlighted.
     */
    public void repaint(Graphics g, List<Field> possibleMoves) {
        Graphics2D G2D = (Graphics2D)g;
        invertY(G2D);
        if(highlightedFields != null) {
            drawSquares(G2D, highlightedFields);
        }
        if(possibleMoves != null) {
            highlightFields(possibleMoves, G2D);
        }
    }

    /**
     * Inverts the Y axis so (0,0) is at the bottom left.
     */
    public void invertY(Graphics2D G2D){
        G2D.translate(0, getHeight());  // Move the origin to the bottom-left corner
        G2D.scale(1, -1);               // Flip the y-axis (invert)
    }

    /**
     * Draws the black and white squares.
     */
    public void drawBoard(Graphics2D G2D) {
        for(int x=0; x<8; x++) {
            for (int y = 0; y < 8; y++) {
                //"Black" square
                if ((x + y) % 2 == 0) {
                    G2D.setColor(Color.getHSBColor(0.08f, 0.7f, 0.3f));
                    G2D.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
                }
                //White square
                else {
                    G2D.setColor(Color.white);
                    G2D.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
                }
            }
        }
    }

    /**
     * Redraws single squares including pieces on square.
     * Use case: delete highlights.
     * @param squares Positions to be redrawn.
     */
    public void drawSquares(Graphics2D G2D, List<Field> squares) {
        for(Field field: squares) {
            int x = field.getX();
            int y = field.getY();
            Piece f = board[x][y];

            //Black Square
            if ((x + y) % 2 == 0) {
                G2D.setColor(Color.getHSBColor(0.08f, 0.7f, 0.3f));
                G2D.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
            }
            //White square
            else {
                G2D.setColor(Color.white);
                G2D.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
            }

            if(f != null) {
                invertY(G2D);
                G2D.drawImage(f.getIcon(),x * Chess.squareSize, boardSize - (y +1) * squareSize, squareSize, squareSize, this );
                invertY(G2D);
            }
        }
    }

    /**
     * Draws the pieces on the board.
     */
    public void drawPieces(Graphics2D G2D){
        invertY(G2D);
        G2D.setFont(new Font("Arial", Font.BOLD, 80));
        for(Piece[] row : board){
            for(Piece f : row){
                if(f==null){continue;}
                if(f.getSide() == Side.Black){
                    G2D.setColor(Color.black);
                }
                else{
                    G2D.setColor(Color.white);
                }
                if(f.getIcon() != null){
                    G2D.drawImage(f.getIcon(),f.getPosX() * Chess.squareSize, boardSize - (f.getPosY() +1) * squareSize, squareSize, squareSize, this );
//                    System.out.println("Drawing Image " + f.type + " " + f.side);
                    continue;
                }
                if(f.getSide() == Side.White){G2D.setColor(Color.white);}
                else{G2D.setColor(Color.BLACK);}
                G2D.drawString(String.valueOf(f.getCharacter()), f.getPosX() * squareSize + squareSize/6, (boardSize - f.getPosY() * squareSize) - gap);
            }
        }
        invertY(G2D);
    }

    /**
     * Highlights fields to move to.
     * @param possibleMoves Fields to be highlighted.
     */
    public void highlightFields(List<Field> possibleMoves, Graphics2D G2D) {

        G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Field field : possibleMoves) {
            int x = field.getX();
            int y = field.getY();
            int circleSize = squareSize/3;
            int circleGap = (squareSize-circleSize)/2;
            G2D.setColor(translucentBlack);

            if(board[x][y] == null){
                G2D.fillOval(x * squareSize + circleGap,y * squareSize + circleGap, circleSize, circleSize);
            }
            else{
                G2D.setStroke(new BasicStroke(10));
                G2D.drawOval(x * squareSize + 5,y * squareSize + 5, squareSize - 10, squareSize - 10);
                G2D.setStroke(new BasicStroke(1));
            }
        }
        highlightedFields = possibleMoves;
    }
}
