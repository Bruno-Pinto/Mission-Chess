package pieces;

import model.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Piece {
    int posX;
    int posY;
    boolean moved = false;
    Type type;
    Side side;
    char character;
    Image icon;

    // Reference to the game board, no longer static
    protected Piece[][] board;

    // Predefined moves for this piece (e.g., relative moves for a knight or rook)
    List<Field> predefinedMoves = new ArrayList<>();

    public Piece(int x, int y, Side side, Piece[][] board) {
        this.posX = x;
        this.posY = y;
        this.side = side;
        this.board = board;
    }

    public void move(int x, int y) {
        if (board[x][y] != null) {
            board[x][y].take();
        }
        board[posX][posY] = null;
        posX = x;
        posY = y;
        board[x][y] = this;
    }

    /**
     * Returns possible moves based on predefined moves, filtering out invalid ones.
     * @return Valid moves within the board.
     */
    public List<Field> getMoves() {
        List<Field> possibleMoves = new ArrayList<>();

        // Loop through predefined moves
        for (Field f : predefinedMoves) {
            int targetX = posX + f.getX();
            int targetY = posY + f.getY();

            // Skip invalid board positions
            if (!validPos(targetX, targetY)) {
                continue;
            }

            // Skip positions occupied by the same side
            if (board[targetX][targetY] != null && board[targetX][targetY].getSide() == side) {
                continue;
            }

            // Check if pieces are in the way (but not for knights, which can jump)
            if (type != Type.Knight) {
                if (!isPathClear(posX, posY, targetX, targetY)) {
                    continue; // Skip move if the path is blocked
                }
            }

            // If no issues, add the move to the possible moves
            possibleMoves.add(new Field(targetX, targetY));
        }

        return possibleMoves;
    }


    /**
     * Helper function to check if the path between (startX, startY) and (targetX, targetY) is clear
     */
    private boolean isPathClear(int startX, int startY, int targetX, int targetY) {
        int deltaX = Integer.compare(targetX, startX); // 1 if moving right, -1 if moving left, 0 if no movement
        int deltaY = Integer.compare(targetY, startY); // 1 if moving up, -1 if moving down, 0 if no movement

        int x = startX + deltaX;
        int y = startY + deltaY;

        // Move along the path and check for obstacles
        while (x != targetX || y != targetY) {
            if (board[x][y] != null) {
                return false; // A piece is in the way
            }
            x += deltaX;
            y += deltaY;
        }

        return true; // Path is clear
    }


    protected void loadIcon() {
        assert type != null;
        String iconName = type.toString() + side.toString() + ".png"; // e.g., WhitePawn.png, BlackRook.png
        try {
            // Load the icon from the resources folder
            this.icon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + iconName)));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Icon not found: " + iconName, e);
        }
    }

    /**
     * Checks if position x, y is valid on the board.
     */
    protected boolean validPos(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public Side getSide() {
        return side;
    }
    public Type getType() {
        return type;
    }
    public int getPosX() {return posX;}
    public int getPosY() {return posY;}
    public char getCharacter() {return character;}
    public Image getIcon() {return icon;}

    public void take() {
        board[posX][posY] = null;
    }

}
