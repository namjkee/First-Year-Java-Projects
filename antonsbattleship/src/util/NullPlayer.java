package util;

import util.Coordinate;
import util.Ship;
import util.ShipPlacement;
import core.GameConfiguration;
import core.Player;

public class NullPlayer implements Player {

	@Override
	public Coordinate getNextShot(char[][] myBoard,
			char[][] myViewOfOpponentBoard) {
		return null;
	}

	@Override
	public ShipPlacement getShipPlacement(Ship ship, char[][] myBoard) {
		return null;
	}

	@Override
	public void notify(String message) {
	}

	@Override
	public void newGame(String opponent, GameConfiguration config) {
	}

}
