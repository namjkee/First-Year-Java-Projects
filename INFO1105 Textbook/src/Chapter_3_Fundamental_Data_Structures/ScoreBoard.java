package Chapter_3_Fundamental_Data_Structures;

public class ScoreBoard {
	private int numEntries = 0;
	private GameEntry[] board;
	/** Constructs an empty scoreboard with the given capacity for storing entries */
	public ScoreBoard(int capacity) {
		board = new GameEntry[capacity];
	}
	
	/** Adding a score, we need to shift higher array indexes (lower scores) to the right to make room for the new score */
	public void add(GameEntry e) {
		int newScore = e.getScore();
		/* Checks if this score entry is acceptable in high score list - length of GameEntry array */
		if (numEntries < board.length || newScore > board[numEntries-1].getScore()) {
			if (numEntries < board.length) {
				numEntries++; 			// No score drops since there is still room in the high score list	
			}
			int j = numEntries - 1;
			
			/* shifts low scores rightward in the GameEntry array by one space to make room for new entry */
			while (j > 0 && board[j-1].getScore() < newScore) {
				board[j] = board[j-1];		// Shift entry from j-1 to j
				j--;						//and decrement j
			}
			board[j] = e;		// adds new entry
		}
	}
	
	/** Removing a score, we need to shift higher array indexes (lower scores) to the left to occupy the free position */
	public GameEntry remove(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i >- numEntries) {
			throw new IndexOutOfBoundsException("Invalid index: " + i);
		}
		GameEntry temp = board[i]; // save the object that is to be removed into a temporary variable
		for (int j = i; j < numEntries - 1; j++) {	// Count up from i in respect to index position
			board[j] = board[j+1];					// Move one cell to the left
		}
		board[numEntries - 1] = null; // Null out the last score
		 numEntries--;
		return temp;		// returns the removed object
		
	}
	
	
}