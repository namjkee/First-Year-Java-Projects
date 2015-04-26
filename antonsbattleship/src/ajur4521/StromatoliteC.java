package ajur4521;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import util.Coordinate;
import util.Ship;
import util.ShipPlacement;
import util.ShipPlacementException;
import util.ShipPlacement.Direction;
import core.GameConfiguration;
import core.Player;


//TODO: work out if corners

public class StromatoliteC implements Player {
	boolean destroy;
	ArrayList<int[]> adjacency;
	
	GameConfiguration configuration;
	ArrayList<Ship> shipsRemaining;
	int largestRemaining;
	
	Coordinate[][] coords;
	
	ArrayList<ArrayList<Coordinate>> searchList; 
	ArrayList<Coordinate> initialPattern;
	ArrayList<Coordinate> searchPattern1;
	ArrayList<Coordinate> searchPattern2;
	ArrayList<Coordinate> destroyPattern;
	ArrayList<Coordinate> prefPattern;
	ArrayList<Coordinate> perimPattern;
	
	ArrayList<int[]> prefDirections;
	
	HashSet<Coordinate> searched;
	HashSet<Coordinate> destroyed;
	
	Coordinate lastShot;
	
	int height;
	int width;
	
	int nonFatalHits;
	int perimeterHits;
	
	Random random;
	
	static int initialBorderCheck = 10;
	
	public StromatoliteC() {
		destroy = false;
		height = 10;
		width = 10;
		nonFatalHits = 0;
		perimeterHits = 0;
		
		adjacency = new ArrayList<int[]>();
		adjacency.add(new int[]{0,1});
		adjacency.add(new int[]{0,-1});
		adjacency.add(new int[]{1,0});
		adjacency.add(new int[]{-1,0});
		
		prefDirections = new ArrayList<int[]>();
		
		coords = new Coordinate[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				coords[i][j] = new Coordinate(j, i);
			}
		}
		
		
		searchPattern1 = generateSearchPattern(3, 4, width, height);
		
		initialPattern = new ArrayList<Coordinate>();
		int iter = 0;
		for (Coordinate c : searchPattern1) {
			if (isOnPerimeter(c)) {
				initialPattern.add(c);
				iter++;
			}
			if (iter >= initialBorderCheck) {
				break;
			}
			
		}
		
		searchPattern2 = generateSearchPattern(1, 4, width, height);
		
		perimPattern = generatePerimeterPattern(2, width, height);
		
		destroyPattern = new ArrayList<Coordinate>();
		prefPattern = new ArrayList<Coordinate>();
		
		searchList = new ArrayList<ArrayList<Coordinate>>();
		searchList.add(initialPattern);
		searchList.add(searchPattern1);
		searchList.add(searchPattern2);
		searchList.add(perimPattern);
		searchList.add(destroyPattern);
		searchList.add(prefPattern);
		
		searched = new HashSet<Coordinate>();
		destroyed = new HashSet<Coordinate>();
		
		lastShot = new Coordinate(-1, -1);
		
		shipsRemaining = new ArrayList<Ship>();
		largestRemaining = 1;
		
		random = new Random();
	}
	
	public ArrayList<Coordinate> generatePerimeterPattern(int spacing, int width, int height) {
		ArrayList<Coordinate> perim = new ArrayList<Coordinate>();
		
		//NORTH
		for (int i = 1; i < width; i += spacing) {
			perim.add(coords[0][i]);
		}
		//EAST
		for (int i = perim.contains(coords[0][width-1]) ? 2 : 1; i < height; i += spacing) {
			perim.add(coords[i][width - 1]);
		}
		//WEST
		for (int i = 1; i < height; i += spacing) {
			perim.add(coords[i][0]);
		}
		//SOUTH
		for (int i = perim.contains(coords[0][width-1]) ? 2 : 1; i < height - 1; i += spacing) {
			perim.add(coords[height - 1][i]);
		}
		
		Collections.shuffle(perim);
		return perim;
	}
	
	public ArrayList<Coordinate> generateSearchPattern(int offset, int interval, int width, int height) {
		ArrayList<Coordinate> pattern = new ArrayList<Coordinate>();
		int startingPos = offset;
		int currentPos;
		
		for (int row = 0; row < height; row++) {
			currentPos = startingPos;
			while (currentPos < width) {
				pattern.add(coords[row][currentPos]);
				currentPos += interval;
			}
			
			startingPos--;
			if (startingPos < 0) startingPos += interval;
		}
		Collections.shuffle(pattern);
		return pattern;	
	}
	
	public void promoteShot(ArrayList<Coordinate> list, Coordinate c) {
		list.remove(c);
		list.add(0, c);
	}
	
	public void demoteShot(ArrayList<Coordinate> list, Coordinate c) {
		list.remove(c);
		list.add(c);
	}
	
	public void removeShotFromLists(Coordinate shot) {
		for (ArrayList<Coordinate> pattern : searchList) {
			if (pattern.contains(shot)){
				pattern.remove(shot);
			}
		}
	}
	
	public void addSurroundsToDestroyPattern(int x, int y) {
		ArrayList<Coordinate> surrounds = new ArrayList<Coordinate>();
		
		if (isValidCoordinate(x+1, y) && !searched.contains(coords[y][x+1])){
			surrounds.add(coords[y][x+1]);
		}
		if (isValidCoordinate(x, y+1) && !searched.contains(coords[y+1][x])){
			surrounds.add(coords[y+1][x]);
		}
		if (isValidCoordinate(x-1, y) && !searched.contains(coords[y][x-1])){
			surrounds.add(coords[y][x-1]);
		}
		if (isValidCoordinate(x, y-1) && !searched.contains(coords[y-1][x])){
			surrounds.add(coords[y-1][x]);
		}
		
		Collections.shuffle(surrounds);

		for (Coordinate c : surrounds) {
			if (!destroyPattern.contains(c)) {
				destroyPattern.add(c);
			}
		}
		
	}
	
	public void addPrefDirections(Coordinate shot) {
		int x = shot.getX();
		int y = shot.getY();
		
		for (Coordinate hit : destroyed) {
			if (isAdjacent(shot, hit)) {
				prefDirections.add(new int[]{x - hit.getX(), y - hit.getY()});
				prefDirections.add(new int[]{-(x - hit.getX()), -(y - hit.getY())});
			}
		}
	}
	
	public void updatePrefPattern(Coordinate shot) {
		for (int[] direction : prefDirections) {
			for (Coordinate toShoot : destroyPattern) {
				if (Arrays.equals(getDirection(shot, toShoot), direction)) {
					if (!prefPattern.contains(toShoot)) {
						prefPattern.add(toShoot);						
					}
				}
			}
		}
	}
	
	public boolean areOrthogonal(Coordinate coord1, Coordinate coord2) {
		if (coord1.getX() == coord2.getX() || coord1.getY() == coord2.getY()) {
			return true;
		}
		
		return false;
	}
	
	public boolean isOnPerimeter(Coordinate c) {
		if (c.getX() == 0 || c.getX() == width-1 || c.getY() == 0 || c.getY() == height-1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isAdjacent(Coordinate coord1, Coordinate coord2) {
		int[] difference = new int[]{coord1.getX() - coord2.getX(), coord1.getY() - coord2.getY()};
		
		for (int [] d : adjacency) {
			if (Arrays.equals(d, difference)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isNextToOccupiedCell(int x, int y, char[][] myBoard) {
		if (isValidCoordinate(x+1, y) && myBoard[y][x+1] != (char) 0){
			return true;
		}
		if (isValidCoordinate(x-1, y) && myBoard[y][x-1] != (char) 0){
			return true;
		}
		if (isValidCoordinate(x, y+1) && myBoard[y+1][x] != (char) 0){
			return true;
		}
		if (isValidCoordinate(x, y-1) && myBoard[y-1][x] != (char) 0){
			return true;
		}
		
		return false;
	}
	
	public boolean isACorner(int x, int y) {
		if (x == 0 && y == 0) {
			return true;
		}
		else if (x == 0 && y == height - 1) {
			return true;
		}
		else if (x == width - 1 && y == 0) {
			return true;
		}
		else if (x == width - 1 && y == height - 1) {
			return true;
		}
		
		return false;
	}
	
	public boolean isValidCoordinate(int x, int y) {
		if ( x < width && x >= 0 && y < height && y >= 0) {
			return true;
		} 
		else {
			return false;			
		}
	}
	
	public boolean isValidPlacement(Ship ship, ShipPlacement shipPlacement, char[][] myBoard) {
		Coordinate beginning = shipPlacement.getBeginning();
		int[] ending = {beginning.getX(), beginning.getY()};
		int[] direction = new int[2];
		
		switch (shipPlacement.getDirection()) {
		case NORTH:
			ending[1] += ship.getShipLength() - 1;
			direction[1] = 1;
			break;
		case SOUTH:
			ending[1] -= ship.getShipLength() - 1;
			direction[1] = -1;
			break;
		case EAST:
			ending[0] += ship.getShipLength() - 1;
			direction[0] = 1;
			break;
		case WEST:
			ending[0] -= ship.getShipLength() - 1;
			direction[0] = -1;
			break;
		}
		
		if (isValidCoordinate(beginning.getX(), beginning.getY()) && isValidCoordinate(ending[0], ending[1])){
			int beginx = beginning.getX();
			int beginy = beginning.getY();
			
			for (int i = 0; i < ship.getShipLength(); i++) {
				int x = beginx + (i * direction[0]);
				int y = beginy + (i * direction[1]);
				
				char cellState = myBoard[y][x]; 
				if (cellState != (char) 0) {
					return false;
				}
				if (isNextToOccupiedCell(x, y, myBoard)) {
					return false;
				}
			}
			
			//TODO: work out if corners are a good idea.
			if (isACorner(beginx, beginy) || isACorner(ending[0], ending[1])) {
				return false;
			}
		}
		else {
			return false;
		}
			
		return true;
	}
	
	private boolean isValidBerth(ShipPlacement shipPlacement, int length, char[][] board) {

		Coordinate beginning = shipPlacement.getBeginning();
		int[] ending = {beginning.getX(), beginning.getY()};
		int[] direction = new int[2];
		
		switch (shipPlacement.getDirection()) {
		case NORTH:
			ending[1] += length - 1;
			direction[1] = 1;
			break;
		case SOUTH:
			ending[1] -= length - 1;
			direction[1] = -1;
			break;
		case EAST:
			ending[0] += length - 1;
			direction[0] = 1;
			break;
		case WEST:
			ending[0] -= length - 1;
			direction[0] = -1;
			break;
		}
		
		if (isValidCoordinate(beginning.getX(), beginning.getY()) && isValidCoordinate(ending[0], ending[1])){
			int beginx = beginning.getX();
			int beginy = beginning.getY();
			
			for (int i = 0; i < length; i++) {
				int x = beginx + (i * direction[0]);
				int y = beginy + (i * direction[1]);
				
				char cellState = board[y][x]; 
				if (cellState == '.') {
					return false;
				}
			}
		}
		else {
			return false;
		}
			
		return true;
	}
	
	public int[] getDirection(Coordinate stationary, Coordinate moving) {
		int x = moving.getX() - stationary.getX();
		int y = moving.getY() - stationary.getY();
		
		if (x != 0) {
			x /= Math.abs(x);
		}
		if (y != 0) {
			y /= Math.abs(y);			
		}
		
		return new int[]{x, y};
	}
	
	public ShipPlacement oldShipPlacement(Ship ship, char[][] myBoard) {
		boolean isValid = false;
		ArrayList<Direction> directionList = new ArrayList<Direction>();
		directionList.add(Direction.NORTH);
		directionList.add(Direction.SOUTH);
		directionList.add(Direction.EAST);
		directionList.add(Direction.WEST);
		
		int x;
		int y;
		
		ShipPlacement shipPlacement = new ShipPlacement(new Coordinate(0, 0), directionList.get(1)); 
		
		while (!isValid) {
			x = random.nextInt(width);
			y = random.nextInt(height);
			shipPlacement = new ShipPlacement(new Coordinate(x, y), directionList.get(random.nextInt(4)));
			
			isValid = isValidPlacement(ship, shipPlacement, myBoard);
		}
		
		return shipPlacement;
	}
	
	@Override
	public ShipPlacement getShipPlacement(Ship ship, char[][] myBoard) {
		boolean isValid = false;
		
		ArrayList<Integer> NSSides = new ArrayList<Integer>();
		NSSides.add(0);
		NSSides.add(height - 1);
		ArrayList<Integer> EWSides = new ArrayList<Integer>();
		EWSides.add(0);
		EWSides.add(width - 1);
		
		Collections.shuffle(NSSides);
		Collections.shuffle(EWSides);
		
		ArrayList<Direction> NSList = new ArrayList<Direction>();
		ArrayList<Direction> EWList = new ArrayList<Direction>();
		EWList.add(Direction.NORTH);
		EWList.add(Direction.SOUTH);
		NSList.add(Direction.EAST);
		NSList.add(Direction.WEST);
		
		Collections.shuffle(NSList);
		Collections.shuffle(EWList);
		
		Direction d = Direction.NORTH;
		
		int orientation = random.nextInt(2);
		
		int x = 0;
		int y = 0;
		
		ShipPlacement shipPlacement = new ShipPlacement(new Coordinate(0, 0), Direction.SOUTH); 
		int loops = 0;
		
		while (!isValid) {
			loops++;
			
			if (orientation == 0) {
				x = EWSides.get(0);
				y = random.nextInt(height);
				d = EWList.get(0);
				
				Collections.shuffle(EWSides);
				Collections.shuffle(EWList);
			}
			else {
				x = random.nextInt(width);
				y = NSSides.get(0);
				d = NSList.get(0);
				
				Collections.shuffle(NSSides);
				Collections.shuffle(NSList);
			}
			
			orientation = random.nextInt(2);
			
			shipPlacement = new ShipPlacement(new Coordinate(x, y), d);
			
			if (loops > 100) {
				shipPlacement = oldShipPlacement(ship, myBoard);
			}
			
			isValid = isValidPlacement(ship, shipPlacement, myBoard);
		}
		
		return shipPlacement;
	}
	
	public Ship getShipWithHandle(char c) {
		for (Ship ship : configuration.getShips()) {
			if (ship.getShipHandle() == c) {
				return ship;
			}
		}
		return null;
	}
	
	private int getLargestLength(ArrayList<Ship> ships) {
		int currentLargest = 0;
		
		for (Ship s : ships) {
			if (s.getShipLength() > currentLargest) {
				currentLargest = s.getShipLength();
			}
		}
		
		return currentLargest;
		
	}
	
	private void printHabMatrix(int shipLength, char[][] myViewOfOpponentBoard) {
		System.out.println("------------- Habitability of Coprolite's board for l = " + shipLength);
		System.out.println("    0  1  2  3  4  5  6  7  8  9");
		int i = 0;
		int j = 0;
		for (char[] row : myViewOfOpponentBoard) {
			System.out.print(i + " :");
			j = 0;
			for (char c : row) {
				if (c == (char)0) {
					int hab = getHabitability(new Coordinate(j, i), shipLength, myViewOfOpponentBoard);
					if (hab < 10) System.out.print(' ');
					System.out.print(hab);
				}
				else {
					System.out.print(" " + c);
				}
				System.out.print(' ');
				j++;
			}
			System.out.println();
			i++;
		}
	}
	
	private int getHabitability(Coordinate coord, int shipLength, char[][] myViewOfOpponentBoard) {
		int x = coord.getX();
		int y = coord.getY();
		
		ShipPlacement northFace;
		ShipPlacement eastFace;
		
		int habitability = 0;
		
		for (int i = 0; i < shipLength; i++) {
			northFace = new ShipPlacement(new Coordinate(x, y-i), Direction.NORTH);
			eastFace = new ShipPlacement(new Coordinate(x-i, y), Direction.EAST);
			
			if (isValidBerth(northFace, shipLength, myViewOfOpponentBoard)) {
				habitability++;
			}
			if (isValidBerth(eastFace, shipLength, myViewOfOpponentBoard)) {
				habitability++;
			}
		}
		
		return habitability;
	}
	
	private Coordinate getGreatestHabitabilityCoord(ArrayList<Coordinate> list, char[][] board, int shipLength) {
		Coordinate bestCoord = list.get(0);
		int maxHab = getHabitability(bestCoord, shipLength, board);
		int currentHab = 0;
		for (Coordinate c : list) {
			currentHab = getHabitability(c, shipLength, board);
			if (currentHab > maxHab) {
				maxHab = currentHab;
				bestCoord = c;
			}
		}
		
		return bestCoord;
		
		
	}
	
	public Coordinate perimPhase(char[][] enemyBoard, ArrayList<Coordinate> pattern) {
		Coordinate shot;
		if (destroy) {
			shot = perimDestroy(enemyBoard);
		}
		else {
			shot = getGreatestHabitabilityCoord(pattern, enemyBoard, largestRemaining);
		}
		return shot;
	}
	
	public Coordinate searchPhase (char[][] enemyBoard, ArrayList<Coordinate> pattern) {
		Coordinate shot;
		if (destroy) {
			shot = destroyPhase(enemyBoard);
		}
		else {
			//shot = pattern.get(0);
			shot = getGreatestHabitabilityCoord(pattern, enemyBoard, largestRemaining);	
		}
		return shot;
	}
	
	public Coordinate randPhase (char[][] enemyBoard) {
		Coordinate shot;
		if (destroy) {
			shot = destroyPhase(enemyBoard);
		}
		else {
			shot = coords[0][0];
			
			while (searched.contains(shot)){
				shot = coords[random.nextInt(height)][random.nextInt(width)];
			}	
		}
		
		return shot;
	}
	
	public Coordinate perimDestroy(char[][] enemyBoard) {
		Coordinate shot;
		ArrayList<Coordinate> perims = new ArrayList<Coordinate>();
		
		if (prefPattern.size() != 0) {
			for (Coordinate c : prefPattern) {
				if (isOnPerimeter(c)) {
					perims.add(c);
				}
			}
			
			if (perims.size() != 0) {
				shot = getGreatestHabitabilityCoord(perims, enemyBoard, largestRemaining);
			}
			else {
				shot = getGreatestHabitabilityCoord(prefPattern, enemyBoard, largestRemaining);
			}
		}
		else {
			for (Coordinate c : destroyPattern) {
				if (isOnPerimeter(c)) {
					perims.add(c);
				}
			}
			
			if (perims.size() != 0) {
				shot = getGreatestHabitabilityCoord(perims, enemyBoard, largestRemaining);
			}
			else {
				shot = getGreatestHabitabilityCoord(destroyPattern, enemyBoard, largestRemaining);
			}
		}
		return shot;
	}
	
	public Coordinate destroyPhase(char[][] enemyBoard) {
		Coordinate shot;
		
		if (prefPattern.size() != 0) {
			shot = getGreatestHabitabilityCoord(prefPattern, enemyBoard, largestRemaining);
		}
		else {
			shot = getGreatestHabitabilityCoord(destroyPattern, enemyBoard, largestRemaining);
		}
		
		return shot;
	}
	
	@Override
	public Coordinate getNextShot(char[][] myBoard,
			char[][] enemyBoard) {
		
		//printHabMatrix(largestRemaining, enemyBoard);
		
		if (perimeterHits >= 9 && perimPattern.size() > 0) {
			lastShot = perimPhase(enemyBoard, perimPattern);
		}
		else if (initialPattern.size() > 0) {
			lastShot = perimPhase(enemyBoard, initialPattern);
		}
		else if (searchPattern1.size() > 0) {
			lastShot = searchPhase(enemyBoard, searchPattern1);
		}
		else if (searchPattern2.size() > 0) {
			lastShot = searchPhase(enemyBoard, searchPattern2);
		}
		else {
			lastShot = randPhase(enemyBoard);
		}
		
		searched.add(lastShot);
		removeShotFromLists(lastShot);
		
		return lastShot;
	}
	
	@Override
	public void notify(String message) {
		if (message.startsWith("HIT")) {
			
			nonFatalHits++;
			destroy = true;
			destroyed.add(lastShot);
						
			int x = lastShot.getX();
			int y = lastShot.getY();
			
			if (isOnPerimeter(lastShot)) {
				perimeterHits++;
			}
			
			addSurroundsToDestroyPattern(x, y);
			
			if (prefDirections.size() == 0) {
				addPrefDirections(lastShot);				
			}
			updatePrefPattern(lastShot);
		}
		if (message.startsWith("SUNK")) {
			char handle = message.charAt(message.length() - 1);
			Ship sunkShip = getShipWithHandle(handle);
			
			shipsRemaining.remove(sunkShip);
			largestRemaining = getLargestLength(shipsRemaining);
			
			nonFatalHits -= sunkShip.getShipLength();
			prefDirections.clear();
			
			if (destroy && nonFatalHits == 0) {
				prefPattern.clear();
				prefDirections.clear();
				destroyPattern.clear();
				destroy = false;
			}
		}
		
	}
	
	@Override
	public void newGame(String opponent, GameConfiguration config) {
		configuration = config;
		height = config.getGridHeight();
		width = config.getGridWidth();
		
		shipsRemaining = new ArrayList<Ship>(configuration.getShips());
		largestRemaining = getLargestLength(shipsRemaining);
		
		nonFatalHits = 0;
		perimeterHits = 0;
		lastShot = new Coordinate(-1, -1);
		
		destroy = false;
		
		searchPattern1 = generateSearchPattern(3, 4, width, height);
		
		initialPattern = new ArrayList<Coordinate>();
		int iter = 0;
		for (Coordinate c : searchPattern1) {
			if (isOnPerimeter(c)) {
				initialPattern.add(c);
				iter++;
			}
			if (iter >= initialBorderCheck) {
				break;
			}
			
		}
		
		searchPattern2 = generateSearchPattern(1, 4, width, height);
		
		perimPattern = generatePerimeterPattern(2, width, height);
		
		prefDirections = new ArrayList<int[]>();
		
		destroyPattern = new ArrayList<Coordinate>();
		prefPattern = new ArrayList<Coordinate>();
		
		searchList = new ArrayList<ArrayList<Coordinate>>();
		searchList.add(initialPattern);
		searchList.add(searchPattern1);
		searchList.add(searchPattern2);
		searchList.add(perimPattern);
		searchList.add(destroyPattern);
		searchList.add(prefPattern);
		
		searched = new HashSet<Coordinate>();
		destroyed = new HashSet<Coordinate>();
	}

}
