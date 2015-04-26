public class ScoreBoard {
	//instance variables
	private SortedLinkedList game;
	private double minDistance = 0;
	
	// constructors
	public ScoreBoard() {
		game = new SortedLinkedList();
	}

	public ScoreBoard(int capacity) {
		game = new SortedLinkedList(capacity);
	}

	public ScoreBoard(double minimum) {
		game = new SortedLinkedList();
		this.minDistance = minimum;
	}

	public ScoreBoard(int capacity, double minimum) {
		game = new SortedLinkedList(capacity);
		this.minDistance = minimum;
	}
	
	// access list - for main testing
	public SortedLinkedList getList() {
		return game;
	}

	// user query methods
	public int size() {
		return game.size();
	}
	
	public String leader() {
		LinkedListNode first = game.getFirst();
		if (first == null) return null;					// prevent nullPointer
		String leader = first.getElement().getName();	
		return leader;									// returns the first flyer's name
	}

	public double queryDistance(String name) {
		if (game.isEmpty()) return -1;
		
		/* Traverse through the list to find the name */
		LinkedListNode current = game.getFirst();
		
		for (int i = 0; i < game.size(); i++) {
			if (current.getElement().getName() == name) {
				double distance = current.getElement().getDistance();
				return distance;									// found name and returns his/her distance
			}
			current = current.getNext();
			if (current == game.getTail()) {						// traversed through entire list and found nothing
				return -1;
			}
		}
		return -1;		// returns this if capacity is 0
	}

	// update method
	public void add(String name, double distance) {
		if (distance >= minDistance) {					// add if minimum distance achieved
			Flyer entry = new Flyer(name, distance);
			game.add(entry);
		}
		else {											// doesn't add if below minimum distance
			return;
		}
	}

}
