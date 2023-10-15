
import java.util.Random;

public class Life {
	
	static int rows = 10;
	static int cols = 40;
	static int w = 10;
	static int panelWidth = cols*(w+1)-1;
	static int panelHeight = rows*(w+1)-1;
	static GameFrame frame = new GameFrame();
	static GamePanel panel = new GamePanel(panelWidth, panelHeight);
	
	//setup the game
	public static void main(String[] args) throws InterruptedException {
		frame.add(panel);
		frame.pack();
		
		int[][] grid1 = new int[rows][cols];
		int[][] grid2 = new int[rows][cols];
		int flip = 0;
		grid1 = randomFill(grid1, rows, cols);
		
		print(grid1, rows, cols);
		//System.out.println(panelWidth + " " + panelHeight);
		
		start(grid1, rows, cols, w);	
	}
		

	
	static void start(int[][] grid, int rows, int cols, int w) {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if(grid[i][j] == 1) {
					panel.paintRect(panel, 11*j, 11*i, w);

				}				
			}
		}
	}
	
	
	//print the grid in console
	static void print(int[][] grid, int rows, int cols) {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
	
	//fill the grid with random 0 or 1
	static int[][] randomFill(int[][] grid, int rows, int cols){
		Random random = new Random();
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				grid[i][j] = random.nextInt(2);
				
			}
		}
		return grid;
	}
}
