package util;

/**
 * Boards helper class.
 * 
 * Stores both player boards in a 3D array.
 * 
 * * Board states:
 * 		* empty spot is denoted by a 0 ( e.g. char a = 0; ). 
 * 			This is the default value for a char.
 * 		* ship segments that are not hit are denoted by a single lower case 
 * 			character that equals their ship handle.
 * 		* ship segments that are hit are denoted by a single UPPER case 
 * 			character that equals their ship handle.
 * 		* misses are denoted by a '.'
 * 
 * @author Anton Jurisevic
 * @sid 311180051
 * @date 2014.6.7
 *
 */
public class Boards {
	private int height;
	private int width;
	
	// Player 1's board is stored at boards[0],
	//  Player 2's board is stored at boards[1].
	private char[][][] boards;
	
	private String[] playerNames;
	
	/**
	 * Boards constructor.
	 * 
	 * @param height - the height of the boards to build.
	 * @param width - the width of the boards to build.
	 */
	public Boards(int height, int width) {
		this.height = height;
		this.width = width;
		this.boards = new char[2][height][width];
		
		playerNames = new String[2];
		this.playerNames[0] = "0";
		this.playerNames[1] = "1";
	}
	
	/**
	 * Returns the height of the boards.
	 * 
	 * @return int - the height.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns the width of the boards.
	 * 
	 * @return int - the width.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the character in the specified cell.
	 * 
	 * @param x - x coordinate of the cell to get.
	 * @param y - y coordinate of the cell to get.
	 * @param player - the player whose board from which to get the cell.
	 * @return char - the character in the cell. 
	 */
	public char getCellState(int x, int y, int player) {
		return boards[player][y][x];
	}
	
	/**
	 * Sets the specified cell to be the specified character.
	 * 
	 * @param x - x coordinate of the cell to set.
	 * @param y - y coordinate of the cell to set.
	 * @param player - the player whose board upon which to set the cell.
	 * @param c - the character to set the specified cell to.
	 */
	public void setCellState(int x, int y, int player, char c) {
		boards[player][y][x] = c;
	}
	
	/**
	 * Checks if the board contains a given character.
	 * 
	 * Iterates through every cell, as soon as it finds an appearance of the
	 *  specified character, returns true.
	 * Otherwise, returns false.
	 * 
	 * @param c - character to find.
	 * @param player - whose board to check.
	 * @return true - board contains the given character.
	 * @return false - board does not contain the given character.
	 */
	public boolean contains(char c, int player) {
		for (char[] row : boards[player]) {
			for (char cell : row) {
				if (cell == c) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Removes all instances of a given character from a board.
	 * 
	 * Iterates through the board and sets each cell which matches the given
	 *   character to zero.
	 *   
	 * @param c - character to remove from the board.
	 * @param player - player whose board from which to remove all instances of the given character.
	 */
	public void removeAll(char c, int player) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++){
				if (boards[player][i][j] == c) {
					boards[player][i][j] = (char) 0;
				}
			}
		}
	}
	
	/**
	 * Returns the enemy's obscured view of the given player's board.
	 * 
	 * An enemy is only able to see hits and misses. All unhit cells are displayed as zeroes.
	 * Ship segments which have been hit are displayed as 'H'. Misses are displayed as '.'.
	 * 
	 * @param player - the player whose board of which to return an obscured version
	 * @return char[][] - the obscured version of the player's board.
	 */
	public char[][] getEnemyView(int player) {
		char[][] view = new char[height][width];
		
		//Iterate through the player's board and set the corresponding cell in the
		//  output board to the correct value.
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				char cell = boards[player][i][j];
				if (cell >= 'A' && cell <= 'Z'){
					view[i][j] = 'H';
				}
				else if (cell == '.'){
					view[i][j] = '.';
				}
			}
		}
		
		return view;
	}
	
	/**
	 * Gets the board of the specified player.
	 * 
	 * @param player - whose board to get.
	 * @return char[][] - the specified board.
	 */
	public char[][] getPlayerBoard(int player) {
		return boards[player];
	}
	
	/**
	 * Prints out the board passed to it.
	 * 
	 * @param board - a 2D character array representing a Battleships board.
	 */
	public static void printBoard (char[][] board) {
		int hitCells = 0;
		System.out.println("   0 1 2 3 4 5 6 7 8 9");
		int i = 0;
		for (char[] row : board) {
			System.out.print(i + " :");
			i++;
			for (char c : row) {
				System.out.print(c);						
				System.out.print(' ');
				if (c != (char) 0) hitCells++;
			}
			System.out.println();
		}
		System.out.println("Occupied cells: " + hitCells);
	}
	
	
}
