import model.*;
import pieces.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Chess {

    private Player player1;
    private Player player2;
    private List<Player> players;
    private Piece[][] board;
    private Player playerToMove;
    private Piece selectedPiece;
    private List<Field> possibleMoves = new ArrayList<>();
    private boolean gameOver = false;
    private int result; //0 = tie, 1/2 = Player1/2 wins

    // Constants can be static
    static final int boardSize = 720;
    static final int squareSize = boardSize / 8;

    private GamePanel panel;
    private GameFrame frame;

    public Chess() {
        players = new ArrayList<Player>();
        player1 = new Player("White model.Player", Side.White);
        players.add(player1);
        player2 = new Player("Black model.Player", Side.Black);
        players.add(player2);
        board = new Piece[8][8];
        playerToMove = player1;
        panel = new GamePanel(boardSize, board);
        frame = new GameFrame(panel);
        frame.setTitle("Chess: White to move!");
    }

    public static void main(String[] args) {
        Chess game = new Chess();
        game.setup();
        game.run();
    }

    public void run() {

        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / squareSize;
                int y = (boardSize - e.getY()) / squareSize; // make y start from bottom left
                System.out.println("Click on: " + new Field(x,y));

                //Skips if clicked on same pieces.
                if(selectedPiece != null && board[x][y] == selectedPiece){
                    System.out.println("Skipping click.");
                    return;
                }

                Piece targetPiece = board[x][y];

                //Selects a piece if it's same color as player
                if (targetPiece != null && targetPiece.getSide() == playerToMove.getColor()) {
                    selectedPiece = targetPiece;
                    targetPiece = null;
                    possibleMoves = selectedPiece.getMoves();
                    panel.repaint(panel.getGraphics(), possibleMoves);
                }

                // Move the selected piece if target is in possibleMoves.
                else if (selectedPiece != null) {
                    for (Field field : possibleMoves) {
                        if (field.getX() == x && field.getY() == y) {
                            selectedPiece.move(x, y);
                            selectedPiece = null;
                            targetPiece = null;
                            possibleMoves = null;
                            System.out.println("Moving to " + field);
                            panel.repaint();
                            playerToMove = switchPlayer();
                            frame.setTitle("Chess: " + playerToMove.getColor() + " to move!");
                            return;
                        }
                    }
                    selectedPiece = null;
                    targetPiece = null;
                    possibleMoves = null;
                    panel.repaint(panel.getGraphics(), possibleMoves);
                    System.out.println("Unselecting piece");
                }
            }
        });

    }

    /**
     * Sets up the default board layout and draws it on the panel.
     */
    public void setup() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                if (y > 1 && y < 6) {
                    continue;
                }

                if (y == 1) {
                    board[x][y] = new Pawn(x, y, Side.White, board);
                } else if (y == 6) {
                    board[x][y] = new Pawn(x, y, Side.Black, board);
                } else if (y == 0 && (x == 0 || x == 7)) {
                    board[x][y] = new Rook(x, y, Side.White, board);
                } else if (y == 7 && (x == 0 || x == 7)) {
                    board[x][y] = new Rook(x, y, Side.Black, board);
                } else if (y == 0 && (x == 1 || x == 6)) {
                    board[x][y] = new Knight(x, y, Side.White, board);
                } else if (y == 7 && (x == 1 || x == 6)) {
                    board[x][y] = new Knight(x, y, Side.Black, board);
                } else if (y == 0 && (x == 2 || x == 5)) {
                    board[x][y] = new Bishop(x, y, Side.White, board);
                } else if (y == 7 && (x == 2 || x == 5)) {
                    board[x][y] = new Bishop(x, y, Side.Black, board);
                } else if (y == 0 && x == 3) {
                    board[x][y] = new Queen(x, y, Side.White, board);
                } else if (y == 7 && x == 3) {
                    board[x][y] = new Queen(x, y, Side.Black, board);
                } else if (y == 0) {
                    board[x][y] = new King(x, y, Side.White, board);
                } else {
                    board[x][y] = new King(x, y, Side.Black, board);
                }
            }
        }
        panel.repaint();
    }

    /**
     * Prints the board to the console.
     */
    public void printBoardToConsole() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] == null) {
                    System.out.print(' ');
                    continue;
                }
                System.out.print(board[j][i].getCharacter() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if position x, y is in the grid.
     */
    public boolean validPos(int x, int y) {
        return x <= 8 && x >= 0 && y <= 8 && y >= 0;
    }

    /**
     * Sets the game to finished and adds score to winner(or both in tie).
     * @param winningPlayer model.Player that won.
     */
    public void playerWins(Player winningPlayer) {
        gameOver = true;
        if(winningPlayer == player1) {result = 1;}
        else {result = 2;}
        winningPlayer.addScore(1);
        System.out.println();
        System.out.println(winningPlayer.getName() + " wins!");
        System.out.println(player1.getName() + " " + player1.getScore() + " - " + player2.getScore() + " " + player2.getName());
    }

    /**
     * Sets game to tie.
     */
    public void gameTie() {
        gameOver = true;
        result = 0;
        player1.addScore(0.5);
        player2.addScore(0.5);
        System.out.println();
        System.out.println("It's a tie!");
        System.out.println(player1.getName() + " " + player1.getScore() + " - " + player2.getScore() + " " + player2.getName());
    }

    private Player switchPlayer(){
        return playerToMove == player1 ? player2 : player1;
    }
}
