package model;

public class Player {

    private String name;
    private Side color;
    private double score;

    /**
     * Create empty model.Player.
     */
    public Player(){}

    /**
     * Create model.Player.
     * @param name model.Player's name.
     * @param color model.Player's starting side.
     */
    public Player(String name, Side color) {
        this.name = name;
        this.color = color;
        score = 0.0;
    }

    /**
     * Creates model.Player including set score > 0.
     * @param name model.Player's name.
     * @param color model.Player's starting side.
     * @param score model.Player's starting score.
     */
    public Player(String name, Side color, double score) {
        this.name = name;
        this.color = color;
        this.score = score;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Side getColor() {return color;}
    public void setColor(Side color) {this.color = color;}
    public double getScore() {return score;}
    public void setScore(double score) {this.score = score;}
    public void addScore(double toAdd) {this.score += toAdd;}
}
