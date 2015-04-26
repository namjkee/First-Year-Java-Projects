package cpha3003;

import java.util.ArrayList;

import util.Coordinate;
import util.Ship;
import util.ShipPlacement;
import core.GameConfiguration;
import core.Player;
import util.ShipPlacement.Direction;

public class Player1 implements Player {
/*	
	private static final Direction NORTH = null;

	private static final Direction SOUTH = null;

	private static final Direction EAST = null;

	private static final Direction WEST = null;

	GameConfiguration config;
	ShipPlacement placement;
	
	int x = (int) (Math.random() * config.getGridWidth());
	int y = (int) Math.random() * config.getGridHeight();
	
	Coordinate beginning = new Coordinate(x,y);
	
	ArrayList<Integer> random = new ArrayList<Integer>();
	
	Direction d = placement.getDirection();
	
			Direction n = NORTH;
			Direction s = SOUTH;
			Direction e = EAST;
			Direction w = WEST;

			/* First step, place ships */
		//	ShipPlacement shipPlace = new ShipPlacement(beginning, n);
	
			/* Second step, begin game and start firing */

	@Override
	public Coordinate getNextShot(char[][] myBoard,
			char[][] myViewOfOpponentBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShipPlacement getShipPlacement(Ship ship, char[][] myBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notify(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newGame(String opponent, GameConfiguration config) {
		// TODO Auto-generated method stub
		
	}

}
