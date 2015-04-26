package ajur4521;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import util.Coordinate;
import util.Ship;
import util.ShipPlacement;
import util.ShipPlacement.Direction;
import core.GameConfiguration;
import core.Player;

public class Naive implements Player {
	enum Phase{SEARCH1, SEARCH2, DESTROY};
	Phase phase;
	
	Coordinate[][] coords;
	
	ArrayList<Coordinate> searchPattern1;
	ArrayList<Coordinate> searchPattern2;
	ArrayList<Coordinate> destroyPattern; 
	HashSet<Coordinate> destroyed;
	
	Coordinate lastShot;
	
	int height;
	int width;
	
	Random random;
	
	public Naive() {
		phase = Phase.SEARCH1;
		height = 10;
		width = 10;
		
		coords = new Coordinate[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				coords[i][j] = new Coordinate(j, i);
			}
		}
		
		searchPattern1 = generateSearchPattern(3, 4, width, height);
		searchPattern2 = generateSearchPattern(1, 4, width, height);
		
		destroyPattern = new ArrayList<Coordinate>();
		destroyed = new HashSet<Coordinate>();
		
		
		random = new Random();
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
	
	public boolean isValidCoordinate(int x, int y) {
		if ( x < width && x >= 0 && y < height && y >= 0) {
			return true;
		} 
		else {
			return false;			
		}
	}
	
	@Override
	public Coordinate getNextShot(char[][] myBoard,
			char[][] myViewOfOpponentBoard) {
		
		if (phase == Phase.DESTROY && destroyPattern.size() == 0) {
			if (searchPattern1.size() != 0) {
				phase = Phase.SEARCH1;
			}
			else {
				phase = Phase.SEARCH2;
			}
		}
		
		if (phase == Phase.SEARCH1) {
			lastShot = searchPattern1.get(0);
			searchPattern1.remove(0);
			destroyed.add(lastShot);
			
			if (searchPattern1.size() == 0) {
				phase = Phase.SEARCH2;
			}
			
			return lastShot; 
			
		}
		else if (phase == Phase.SEARCH2) {
			lastShot = searchPattern2.get(0);
			searchPattern2.remove(0);
			destroyed.add(lastShot);
			
			return lastShot;
		}
		else if (phase == Phase.DESTROY) {
			lastShot = destroyPattern.get(0);
			destroyPattern.remove(0);
			destroyed.add(lastShot);
			
			if (searchPattern1.contains(lastShot)) {
				searchPattern1.remove(lastShot);
			}
			if (searchPattern2.contains(lastShot)) {
				searchPattern2.remove(lastShot);
			}
			
			
			if (destroyPattern.size() == 0) {
				if (searchPattern1.size() != 0) {
					phase = Phase.SEARCH1;
				}
				else {
					phase = Phase.SEARCH2;
				}
			}
			
			return lastShot;
		}
		else {
			
			Coordinate randomCoord = coords[0][0];
			
			while (destroyed.contains(randomCoord)){
				randomCoord = coords[random.nextInt(height)][random.nextInt(width)];
			}
			
			return randomCoord;
		}
	}
	
	public boolean isValidPlacement(Ship ship, ShipPlacement shipPlacement, char[][] myBoard) {
		Coordinate beginning = shipPlacement.getBeginning();
		int[] ending = {beginning.getX(), beginning.getY()};
		int[] direction = new int[2];
		
		switch (shipPlacement.getDirection()) {
		case NORTH:
			ending[1] += ship.getShipLength();
			direction[1] = 1;
			break;
		case SOUTH:
			ending[1] -= ship.getShipLength();
			direction[1] = -1;
			break;
		case EAST:
			ending[0] += ship.getShipLength();
			direction[0] = 1;
			break;
		case WEST:
			ending[0] -= ship.getShipLength();
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
			}
		}
		else {
			return false;
		}
			
		return true;
	}
	
	@Override
	public ShipPlacement getShipPlacement(Ship ship, char[][] myBoard) {
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
			
			x = random.nextInt(10);
			y = random.nextInt(10);
			shipPlacement = new ShipPlacement(new Coordinate(x, y), directionList.get(random.nextInt(4)));
			
			isValid = isValidPlacement(ship, shipPlacement, myBoard);
		}
		
		return shipPlacement;
	}

	@Override
	public void notify(String message) {
		//System.out.println("Player 1: " + message);
		if (message.startsWith("HIT")) {
			phase = Phase.DESTROY;
			
			int x = lastShot.getX();
			int y = lastShot.getY();
			
			if (isValidCoordinate(x+1, y) && !destroyed.contains(coords[y][x+1])){
				destroyPattern.add(coords[y][x+1]);
			}
			if (isValidCoordinate(x-1, y) && !destroyed.contains(coords[y][x-1])){
				destroyPattern.add(coords[y][x-1]);
			}
			if (isValidCoordinate(x, y+1) && !destroyed.contains(coords[y+1][x])){
				destroyPattern.add(coords[y+1][x]);
			}
			if (isValidCoordinate(x, y-1) && !destroyed.contains(coords[y-1][x])){
				destroyPattern.add(coords[y-1][x]);
			}
		}
		
	}

	@Override
	public void newGame(String opponent, GameConfiguration config) {
		height = config.getGridHeight();
		width = config.getGridWidth();
		
		phase = Phase.SEARCH1;
		
		coords = new Coordinate[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				coords[i][j] = new Coordinate(j, i);
			}
		}
		
		searchPattern1 = generateSearchPattern(3, 4, width, height);
		searchPattern2 = generateSearchPattern(1, 4, width, height);
		
		destroyPattern = new ArrayList<Coordinate>();
		destroyed = new HashSet<Coordinate>();
		
	}

}
