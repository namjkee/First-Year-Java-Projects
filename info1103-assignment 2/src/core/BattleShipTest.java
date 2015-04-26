package core;

import java.util.ArrayList;

import cpha3003.Player1;
import cpha3003.Player2;
import util.Coordinate;
import util.Ship;
import util.ShipPlacement;
import util.ShipPlacement.Direction;
import util.ShipPlacementException;

public class BattleShipTest {

	public static void main(String[] args) {
		// Create the ships to test with (these are standard lengths)
		// Use whatever ships you want!
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Ship('a', 5));
		ships.add(new Ship('b', 4));
		ships.add(new Ship('s', 3));
		ships.add(new Ship('d', 3));
		ships.add(new Ship('p', 2));

		// Test on a 10x10 board. Try other board sizes!
		GameConfiguration config = new GameConfiguration(10, 10, ships);
		
		// Create an instance of your player
		Player p1 = new Player1(); 
		// Create another instance of your player
		Player p2 = new Player2(); 

		// Create and run the game
		BattleShips game = new BattleShips(config, p1, p2);
		
		Coordinate coordinate = new Coordinate(3,2);
		Ship b = ships.get(1);
		Direction d = Direction.EAST;
		
		ShipPlacement place = new ShipPlacement(coordinate,d);
		try {
			game.placeShip(b, place, 0);
		} catch (ShipPlacementException e) {
			e.printStackTrace();
		}
		
		/*Ship a */
		Coordinate shipA = new Coordinate(6,2);
		Ship a = ships.get(0);
		Direction e = Direction.WEST;
		
		ShipPlacement placeE = new ShipPlacement(shipA, e);
			try {
				game.placeShip(a, placeE, 0);
			} catch (ShipPlacementException e1) {
				e1.printStackTrace();
			}
		
		game.getEnemyView(1);
			
		Player winner = game.run();
		System.out.println(winner);
	}

}
