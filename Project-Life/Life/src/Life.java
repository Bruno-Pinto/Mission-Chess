
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Life {
	
	//setup the game
	public static void main(String[] args) {
		
		int h = 2;
		int w = 2;
		int[][] grid1 = new int[h][w];
		int[][] grid2 = new int[h][w];
		int flip = 0;
		grid1 = randomFill(grid1, w, h);
		
		// print(grid1, h, w);

		MyFrame myFrame = new MyFrame();
		
		
	}
	//print the grid in console
	static void print(int[][] grid, int h, int w) {
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
	//fill the grid with random 0 or 1
	static int[][] randomFill(int[][] grid, int w, int h){
		Random random = new Random();
		for (int i=0; i<h; i++) {
			for (int j=0; j<w; j++) {
				grid[i][j] = random.nextInt(2);
				
			}
		}
		return grid;
	}
}
