package simulation;

/**
 * 
 * Grid class
 *
 * Container class to hold the grid information.
 * This class should be a wrapper over the two dimensional array of the board.
 * 
 * @author Clifford Phan
 *
 */

public class Grid {
	Ant ant;
	boolean[][] grid;
	private int height;
	private int width;

	/**
	 * Constructor for the grid.
	 * 
	 * Initial state is that the every cell in the grid is white
	 * 
	 * @param height - height of the grid
	 * @param width - width of the grid
	 */
	public Grid(int height, int width) {
		this.height = height;
		this.width = width;
		grid = new boolean[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = false;
			}
		}
		
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean isWhite(int i, int j) {
		if (grid[i][j] == false) {
			return true;
		} else
			return false;
	}

	protected void setWhite(int i, int j) {
		grid[i][j] = false;
	}

	protected void setBlack(int i, int j) {
		grid[i][j] = true;
	}
}