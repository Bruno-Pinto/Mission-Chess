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
        game.setupBoard();
        game.run();
    }

    public void run() {

        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / squareSize;
                int y = (boardSize - e.getY()) / squareSize; // Adjusting y to start from bottom-left
                System.out.println("Click on: " + new Field(x, y));

                handleMouseClick(x, y);
            }
        });

    }

    /**
     * Sets up the default board layout and draws it on the panel.
     */
    public void setupBoard() {
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
     * Handles the logic when a mouse click occurs on the game board.
     * Delegates to either selecting a piece or attempting to move a selected piece.
     *
     * @param x the x-coordinate of the clicked square (calculated from mouse position)
     * @param y the y-coordinate of the clicked square (calculated from mouse position)
     */
    private void handleMouseClick(int x, int y) {
        // If the player clicks on the selected piece again, skip the action
        if (selectedPiece != null && board[x][y] == selectedPiece) {
            System.out.println("Skipping click.");
            return;
        }

        Piece targetPiece = board[x][y];

        if (selectedPiece == null || targetPiece != null && targetPiece.getSide() == playerToMove.getColor()) {
            // Select a piece if the clicked piece belongs to the current player
            selectPiece(targetPiece, x, y);
        } else {
            // Try to move the selected piece if a piece is already selected
            movePiece(x, y);
        }
    }

    /**
     * Attempts to select a piece based on the player's current turn.
     * If the clicked piece belongs to the current player, it will be selected, and its possible moves are calculated.
     * If the clicked piece is invalid or belongs to the opponent, the selection is reset.
     *
     * @param targetPiece the piece on the clicked square, or null if the square is empty
     * @param x the x-coordinate of the clicked square
     * @param y the y-coordinate of the clicked square
     */
    private void selectPiece(Piece targetPiece, int x, int y) {
        // If the clicked piece belongs to the current player, select it
        if (targetPiece != null && targetPiece.getSide() == playerToMove.getColor()) {
            selectedPiece = targetPiece;
            possibleMoves = selectedPiece.getMoves();
            System.out.println("Selected: " + selectedPiece.getSide() + selectedPiece.getType() + " at " + new Field(x, y));
            panel.repaint(panel.getGraphics(), possibleMoves);
        } else {
            // If an invalid piece is clicked, just reset
            System.out.println("No piece selected or invalid piece.");
            selectedPiece = null;
            possibleMoves = null;
            panel.repaint(panel.getGraphics(), possibleMoves);
        }
    }

    /**
     * Attempts to move the currently selected piece to the clicked square.
     * If the clicked square is a valid move for the selected piece, the piece is moved, and the turn is switched.
     * If the move is invalid, the selected piece is unselected and the state is reset.
     *
     * @param x the x-coordinate of the clicked square
     * @param y the y-coordinate of the clicked square
     */
    private void movePiece(int x, int y) {
        // Move the selected piece if the target position is in the possible moves
        for (Field field : possibleMoves) {
            if (field.getX() == x && field.getY() == y) {
                selectedPiece.move(x, y);
                System.out.println("Moved to " + field);
                panel.repaint();
                playerToMove = switchPlayer(); // Switch player
                frame.setTitle("Chess: " + playerToMove.getColor() + " to move!");

                // Reset after the move
                selectedPiece = null;
                possibleMoves = null;
                return;
            }
        }

        // If the move is invalid, unselect the piece and reset
        System.out.println("Invalid move, unselecting piece.");
        selectedPiece = null;
        possibleMoves = null;
        panel.repaint(panel.getGraphics(), possibleMoves);
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

    /**
     * Switches player that is moving.
     * @return Player that has to move.
     */
    private Player switchPlayer(){
        return playerToMove == player1 ? player2 : player1;
    }

    public boolean checkCapturable(Field field, Side side) {
        List<Field> targetFields = new ArrayList<>();
        for(int i=-7; i<8; i+=7){
            for(int j=7; j>-8; j-=7){
                if(i==0 && j==0){continue;}
                targetFields.add(new Field(field.getX()+i, field.getY()+j));
            }
        }
        for(Field targetField : targetFields) {
            Field occupiedField = pathHasPiece(field.getX(), field.getY(), targetField.getX(), targetField.getY());
            if(occupiedField == null){continue;}

            Piece p = board[occupiedField.getX()][occupiedField.getY()];
            boolean straight = occupiedField.getX() == field.getX() || occupiedField.getY() == field.getY();
            if(p.getSide() == side){continue;}

            for(Field f : p.getMoves()){
                if(f.getX() == field.getX() && f.getY() == field.getY()){
                    return true;
                }
            }
        }

        targetFields.clear();
        for(int i=-2; i<=2; i+=1){
            for(int j=2; j>=-2; j-=1){
                if(i==j || i==-j || i==0 || j==0){continue;}
                targetFields.add(new Field(field.getX()+i, field.getY()+j));
            }
        }
        targetFields.add(new Field(field.getX(), field.getY()));
        return false;
    }


    /**
     * Checks if there is a piece on the path between startXY and targetXY
     * @return null if no, else Field of piece.
     */
    private Field pathHasPiece(int startX, int startY, int targetX, int targetY) {
        int deltaX = Integer.compare(targetX, startX); // 1 if moving right, -1 if moving left, 0 if no movement
        int deltaY = Integer.compare(targetY, startY); // 1 if moving up, -1 if moving down, 0 if no movement

        int x = startX;
        int y = startY;

        // Move along the path and check for obstacles
        while ((x != targetX || y != targetY) && validPos(x,y)) {
            if (board[x][y] != null) {
                return new Field(x,y); // A piece is in the way
            }
            x += deltaX;
            y += deltaY;
        }
        return null; // Path is clear
    }
}
