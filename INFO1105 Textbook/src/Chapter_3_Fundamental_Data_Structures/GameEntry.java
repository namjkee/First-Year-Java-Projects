package Chapter_3_Fundamental_Data_Structures;

public class GameEntry {
	private String name;
	private int score;
	/** Constructs a game entry with given parameters */
	public GameEntry(String n, int s) {
		name = n;
		score = s;
	}
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	
	public String toString() {
		return "(" + name + ", " + score + ")";
	}
	

}
