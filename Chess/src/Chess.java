import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;

public class Chess {

    public static Figure[][] Board = new Figure[8][8];
    static int boardSize = 720;
    static int squareSize = boardSize /8;
    //piece gap from the top of the square
    static int gap = squareSize/8;
    static int iconWidth = squareSize - 2*gap;
    static GamePanel panel = new GamePanel(boardSize);
    @SuppressWarnings("unused")
    static GameFrame frame = new GameFrame();
    static Side sideToMove = Side.White;
    static Figure selectedFigure;
    static boolean gameOver = false;
    static Side winningSide;
    static String iconFilePath = "C:/Users/bruno/git/My-Projects/Chess/Figures/";

    public static void main(String[] args){


        setup();
        printBoardToConsole();
        drawBoard();
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / squareSize;
                int y = e.getY() / squareSize;

                if (selectedFigure == null) {
                    selectedFigure = Board[x][y];
                    selectedFigure.setStatus(Status.highlighted);
                } else {
                    List<Move> possibleMoves = selectedFigure.getMoves();
                    for (Move move : possibleMoves) {
                        if (move.getX() == x && move.getY() == y) {
                            if (Board[x][y] != null) {
                                Board[x][y].take();
                            }
                        }
                    }
                }

            }
        });

    }

    public static void setup(){
        for(int x=0; x<8; x++) {
            for(int y=0; y<8; y++) {

                if(y>1 && y<6) {
                    continue;
                }

                if(y==1) {
                    Board[x][y] = new Pawn(x, y, Side.White);
                }
                else if(y==6) {
                    Board[x][y] = new Pawn(x, y, Side.Black);
                }
                else if(y==0 && (x==0 || x==7)) {
                    Board[x][y] = new Rook(x, y, Side.White);
                }
                else if(y==7 && (x==0 || x==7)) {
                    Board[x][y] = new Rook(x, y, Side.Black);
                }
                else if(y==0 && (x==1 || x==6)) {
                    Board[x][y] = new Knight(x, y, Side.White);
                }
                else if(y==7 && (x==1 || x==6)) {
                    Board[x][y] = new Knight(x, y, Side.Black);
                }
                else if(y==0 && (x==2 || x==5)) {
                    Board[x][y] = new Bishop(x, y, Side.White);
                }
                else if(y==7 && (x==2 || x==5)) {
                    Board[x][y] = new Bishop(x, y, Side.Black);
                }
                else if(y==0 && x==3) {
                    Board[x][y] = new Queen(x, y, Side.White);
                }
                else if(y==7 && x==3) {
                    Board[x][y] = new Queen(x, y, Side.Black);
                }
                else if(y == 0) {
                    Board[x][y] = new King(x, y, Side.White);
                }
                else {
                    Board[x][y] = new King(x, y, Side.Black);
                }
            }
        }
    }

    /**
     * Draws the board on the JPanel.
     * If an icon is missing, the character is used.
     */
    public static void drawBoard(){

        Graphics g = panel.getGraphics();
        Graphics2D g2D = (Graphics2D)g;
        g2D.setFont(new Font("Arial", Font.BOLD, 80));

        for(Figure[] row : Board){
            for(Figure f : row){
                if(f==null){continue;}
                if(f.side == Side.Black){
                    g2D.setColor(Color.black);
                }
                else{
                    g2D.setColor(Color.white);
                }
                if(f.icon != null){
                    g2D.drawImage(f.icon,f.posX * squareSize, boardSize - (f.posY +1) * squareSize, squareSize, squareSize, panel );
                    System.out.println("Drawing Image " + f.type + " " + f.side);
                    continue;
                }
                if(f.side == Side.White){g2D.setColor(Color.white);}
                else{g2D.setColor(Color.BLACK);}
                g2D.drawString(String.valueOf(f.character), f.posX * squareSize + squareSize/6, (boardSize - f.posY * squareSize) - gap);
            }
        }
    }

    /**
     * Prints the board to the console.
     */
    public static void printBoardToConsole() {
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

    /**
     * Checks if position x,y is in the grid.
     * @return true if it is a position within the grid.
     */
    public static boolean validPos(int x, int y) {
        return x<=8 && x>=0 && y<=8 && y>=0;
    }

    /**
     * Sets the game to finished and sets winner.
     * @param side is the winner.
     */
    public static void setGameOver(Side side){
        gameOver = true;
        winningSide = side;
        System.out.println(side + " wins!");
    }
}