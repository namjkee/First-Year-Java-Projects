package core;

import java.security.InvalidParameterException;
import java.util.Collection;

import util.Boards;
import util.Coordinate;
import util.Ship;
import util.ShipPlacement;
import util.ShipPlacement.Direction;
import util.ShipPlacementException;
import core.GameConfiguration;

/**
 * Battleships class.
 * 
 * Fill in the methods.
 * They are ordered in the order you should code them in.
 * 
 * NOTES:
 * 	* Board states:
 * 		* empty spot is denoted by a 0 ( e.g. char a = 0; ). 
 * 			This is the default value for a char.
 * 		* ship segments that are not hit are denoted by a single lower case 
 * 			character that equals their ship handle.
 * 		* ship segments that are hit are denoted by a single UPPER case 
 * 			character that equals their ship handle.
 * 		* misses are denoted by a '.'
 * 
 * @author Clifford Phan
 *
 */
public class BattleShips implements Player {
	
	private GameConfiguration config;
	private Player[] Player;
	private Boards boards;
	
	private int height;
	private int width;
	private int maxMoves;
	
	/**
	 * #0 We advise you to implement this first
	 * 
	 * Constructor
	 * 
	 * Initialize all instance variables here.
	 * 
	 * @param config - the configuration for this player
	 * @param player1 - one player
	 * @param player2 - other player
	 */
	public BattleShips(GameConfiguration config, Player player1, Player player2) {
		
		Player[0] = player1;
		Player[1] = player2;
		
		this.config = config;
		height = config.getGridHeight();
		width = config.getGridWidth();
		
		
		
	}
	
	/**
	 * #1 (implement this next)
	 * 
	 * Function to check if the coordinate is valid. 
	 * 
	 * A coordinate is valid if and only if:
	 * 	* x value is within the board bounds (greater or equal to 0 and less 
	 * 		than the grid width)
	 * 	* y value is within the board bounds (greater or equal to 0 and less 
	 * 		than the grid height)
	 * 
	 * HINT: call this method to ensure the coordinate is valid before using it 
	 * in any calculations!
	 * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return true - coordinate is valid
	 * @return false - coordinate is invalid
	 */
	protected boolean isValidCoordinate(int x, int y) {
		if(x >= 0 && x < this.width && y >= 0 && y < this.height) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * #2
	 * 
	 * Function to check if the shot is valid.
	 * 
	 * A shot is valid if and only if:
	 * 	* it is within bounds
	 * 
	 * @param shot - the shot being taken
	 * @return true - shot is valid
	 * @return false - the shot is invalid
	 */
	protected boolean isValidShot(Coordinate shot) {
		if(shot == null) {
			return false;
		} else {
			if (isValidCoordinate(shot.getX(), shot.getY()) == true) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	/**
	 * #3
	 * 
	 * Function to get the value of the cell on a specific player's board
	 * 
	 * @param player - the player whose board you are referring to 
	 * 	(0 for player 1, 1 for player 2)
	 * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * 
	 * @return the character in the cell (-1 if the coordinates are invalid)
	 */
	
	/**
	 * Function to check if an index refers to a player.
	 * 
	 * Only 0 and 1 are valid values.
	 * 
	 * @param player - an integer representing a player
	 * @return true - integer represents a player
	 * @return false - integer does not represent a player
	 */
	protected boolean isValidPlayer(int player) {
		switch(player) {
		case 0:
		case 1:
			return true;
		default:
			throw new InvalidParameterException("Player must be 0 or 1");
		}
	}
	
	protected char getCellState(int player, int x, int y) {
		if (isValidCoordinate(x, y) == true) {
			return boards.getCellState(x, y, player);
		}
		else {
			return (char) -1;
		}
	}
		
	/**
	 * #4
	 * 
	 * Function to resolve a shot.
	 * 
	 * It must:
	 * 	* change the correct board value to the correct value.
	 * 	* send the correct message to the correct player.
	 * 
	 * Correct value changes:
	 * 	* If the character is a lower case character - make it upper case 
	 * 		(shot)
	 * 	* If the character is 0 - make it '.' (miss)
	 * 	* If the character is upper case or '.' - do nothing (miss)
	 * 
	 * Correct messages:
	 * 	* If the character is a lower case character - "HIT (x,y)" where x is 
	 * 		the x coordinate and y is the y coordinate
	 * 	* If the whole ship has been sunk - "SUNK 'A'" where 'A' is the ship 
	 * 		handle (don't include the ' marks)
	 * 	* If the character is 0 - "MISS (x,y)" where x is the x coordinate and 
	 * 		y is the y coordinate
	 * 	* If the character is upper case or '.' - do nothing (miss)
	 * 
	 * @param shot - the current shot
	 * @param player - the player SHOOTING
	 */
	protected void resolveShot(Coordinate shot, int player) {
		if (shot == null || !isValidPlayer(player)) {
			return;
		}
		
		int x = shot.getX();
		int y = shot.getY();
		
		if (isValidShot(shot)) {
			char cellState = boards.getCellState(x, y, player ^ 1);
			
			
		}
		
		
	}
	
	/**
	 * #5
	 * 
	 * Function to resolve a ship placement.
	 * 
	 * It must first check if a ship placement is valid, then place it if it 
	 * is valid.
	 * 
	 * The placement of a ship is valid if and only if:
	 * 	* the shipPlacement is not null
	 * 	* no square that the ship is going to occupy is outside the grid
	 * 	* no square that the ship is going to occupy is already occupied by 
	 * 		another ship
	 * 
	 * If a square of the ship is going to occupy a position that is already 
	 * occupied by another ship, the already placed ship will sink and the new 
	 * ship will not be placed.
	 * 
	 * If a ship is placed on top of multiple ships, they ALL sink.
	 * 
	 * @param shipPlacement - the ship placement (starting coordinate and 
	 * 			direction)
	 * @param ship - the ship being placed
	 * @param player - the player placing the ship
	 * @throws ShipPlacementException - thrown when there is a placement problem
	 * @see ShipPlacementException
	 */
	protected void placeShip(Ship ship, ShipPlacement shipPlacement, int player) 
			throws ShipPlacementException {
		
	}
	
	/**
	 * #6
	 * 
	 * Function to get the enemy view of a player's board
	 * 
	 * You generate the view based on these rules:
	 * 	* if the value is upper case, show 'H'
	 * 	* if the value is lower case, show 0
	 * 	* otherwise - the true value
	 * 
	 * @param player - the player whose board you are going to view
	 * @return the view (contains only 'H', '.' and 0
	 */
	protected char[][] getEnemyView(int player) {
		
		return null;
	}
	
	/**
	 * #7
	 * 
	 * Function to start and run the game.
	 * 
	 * @return the player that has won ( null if it was a draw )
	 */
	public Player run() {
		return null;
	
	}

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