
import java.util.Random;

public class Life {
	
	static int rows = 40;
	static int cols = 60;
	static int w = 15;
	static int gap = 1;
	static int uSize = w + gap;
	static int panelWidth = cols*(uSize)-gap;
	static int panelHeight = rows*(uSize)-gap;
	static GamePanel panel = new GamePanel(panelWidth, panelHeight);
	static GameFrame frame = new GameFrame();
	static boolean ready = false;
	static int[][] grid1 = new int[rows][cols];
	static int[][] grid2 = new int[rows][cols];
	
	//setup the game
	public static void main(String[] args) throws InterruptedException {
		
		int flip = 0;
		grid1 = randomFill(grid1, rows, cols);
		
		print(grid1, rows, cols);
		//System.out.println(panelWidth + " " + panelHeight);
		
		
		Thread.sleep(100);
		
		if(flip==0) {
			startGame(grid1, rows, cols);
			flip = 1;
		}
		else {
			startGame(grid2, rows, cols);	
			flip = 0;
		}
	}
	//runs the game/calculates what the next step will be
	static void run(int[][] gridA, int[][] gridB) {
		int sum = 0;
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if(i==0 || i==rows-1 || j==0 || j==cols-1) {
					sum = neighboursBorder(gridA, i, j);
				}
				else {
					sum = neighbours(gridA, i ,j);
				}
				
				
			}
		}
	}
	
	//counts neighbours of inside fields
	static int neighbours(int[][]grid, int i, int j) {
		int sum=0;
		sum += grid[i-1][j-1];
		sum += grid[i-1][j];
		sum += grid[i-1][j+1];
		sum += grid[i][j-1];
		sum += grid[i][j+1];
		sum += grid[i+1][j-1];
		sum += grid[i+1][j];
		sum += grid[i+1][j+1];
		return sum;
	}
	
	//counts neighbours of border fields
		static int neighboursBorder(int[][]grid, int i, int j) {
			int sum=0;
			if(i>0) {
				sum += grid[i-1][j];
				if(j>0) {
					sum += grid[i-1][j-1];		
				}
				if(j<cols-1) {
					sum += grid[i-1][j+1];
				}
			}
			if(i<rows-1) {
				sum += grid[i+1][j];
				if(j>0) {
					sum += grid[i-+1][j-1];	
				}
				if(j<cols-1) {
					sum += grid[i+1][j+1];
				}
			}
			
			return sum;
		}
		
	//starts the game and paints the first frame
	static void startGame(int[][] grid, int rows, int cols) {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				if(grid[i][j] == 1) {
					panel.paintRect(panel, (uSize)*j, (uSize)*i, w);

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
		ready = true;
		return grid;
	}
}
