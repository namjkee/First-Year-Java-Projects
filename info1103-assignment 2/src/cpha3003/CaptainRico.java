package cpha3003;

import java.util.ArrayList;
import java.util.Random;

import util.Coordinate;
import util.Ship;
import util.ShipPlacement;
import core.GameConfiguration;
import core.Player;
import util.ShipPlacement.Direction;

public class CaptainRico implements Player {

	GameConfiguration config;
	ShipPlacement placement;

	@Override
	public Coordinate getNextShot(char[][] myBoard,
			char[][] myViewOfOpponentBoard) {
		
		/* Choosing shot to fire */
		int x = (int) (Math.random() * config.getGridWidth());
		int y = (int) (Math.random() * config.getGridHeight());
		
		Coordinate shot = new Coordinate(x, y);
		x = shot.getX();
		y = shot.getY();
		
		/* If true on enemy view, then reallocate shot coordinate */
		while(myViewOfOpponentBoard[y][x] == '.' || myViewOfOpponentBoard[y][x] == 'H') {
			
			if(myViewOfOpponentBoard[y][x] == '.') {
				x = (int) (Math.random() * config.getGridWidth());
				x = shot.getX();
				
				y = (int) (Math.random() * config.getGridHeight());
				y = shot.getY();
			}
			else if(myViewOfOpponentBoard[y][x] == 'H') {
				ArrayList<Integer> adjacentShot = new ArrayList<Integer>();
				adjacentShot.add(x + 1);
				adjacentShot.add(x - 1);
				adjacentShot.add(y + 1);
				adjacentShot.add(y - 1);
				
				Random r = new Random();
				
				int adjacent = adjacentShot.get(r.nextInt(adjacentShot.size()));
				if(adjacent == x + 1) {
					adjacent = shot.getX();
					// y doesn't change
				}
				else if(adjacent == x - 1) {
					adjacent = shot.getX();
					// y doesn't change
				}
				else if(adjacent == y + 1) {
					adjacent = shot.getY();
					// x doesn't change
				}
				else if(adjacent == y - 1) {
					adjacent = shot.getY();
					// x doesn't change
				}
				
			}
			
		}
		
		/* If condition false, then empty spot is chosen as shot coordinate */
		
		return shot;
	}

	@Override
	public ShipPlacement getShipPlacement(Ship ship, char[][] myBoard) {
		
		/* Choosing Ship Coordinates */
		int x = (int) (Math.random() * config.getGridWidth());
		int y = (int) (Math.random() * config.getGridHeight());
		
		Coordinate beginning = new Coordinate(x,y);
		/* If another ship on coordinate, it repeats a random coordinate */
		while(beginning.getX() != (char) 0) {
			x = (int) (Math.random() * config.getGridWidth());
		}
		while(beginning.getY() != (char) 0) {
			y = (int) (Math.random() * config.getGridHeight());
		}
		
		/* Choosing Direction */
		ArrayList<Direction> Direction = new ArrayList<Direction>();
		Direction.add(ShipPlacement.Direction.NORTH);
		Direction.add(ShipPlacement.Direction.EAST);
		Direction.add(ShipPlacement.Direction.SOUTH);
		Direction.add(ShipPlacement.Direction.WEST);
		
		Direction directionShip = null;
		int d = (int)(Math.random() * Direction.size());
		switch(d) {
		case 0:
			directionShip = ShipPlacement.Direction.NORTH;
			break;
		case 1:
			directionShip = ShipPlacement.Direction.NORTH;
			break;
		case 2:
			directionShip = ShipPlacement.Direction.SOUTH;
			break;
		case 3: 
			directionShip = ShipPlacement.Direction.WEST;
			break;
		}
		
		ShipPlacement shipPlace = new ShipPlacement(beginning, directionShip);
		
		return shipPlace;
	}

	@Override
	public void notify(String message) {
		/* HIT */
		if(message.substring(0, 2) == "HIT") {
			message = "HIT";
		}
		
		
	}

	@Override
	public void newGame(String opponent, GameConfiguration config) {
		
		
	}

}
