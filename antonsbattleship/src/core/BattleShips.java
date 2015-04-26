package core;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import util.Boards;
import util.Coordinate;
import util.NullPlayer;
import util.Ship;
import util.ShipPlacement;
import util.ShipPlacementException;

/**
 * Battleships class.
 * 
 * Plays Battleship between two players.
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
 * @author Anton Jurisevic
 * @sid 311180051
 * @date 2014.6.7
 *
 */
public class BattleShips {
	private GameConfiguration config;
	private Player[] players;
	private Boards boards;
	
	private int height;
	private int width;
	private int maxMoves;
	
	public static boolean DEBUG = true;
	public static int DELAY = 500;
	
	
	/**
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
		
		//if configuration is invalid, play a standard game of Battleship.
		if (config == null || config.getShips() == null) {			
			ArrayList<Ship> ships = new ArrayList<Ship>();
			ships.add(new Ship('c', 5));
			ships.add(new Ship('b', 4));
			ships.add(new Ship('s', 3));
			ships.add(new Ship('d', 3));
			ships.add(new Ship('p', 2));
			
			config = new GameConfiguration(10, 10, ships);
		}
		
		//if either player is null, set them to a null player which does nothing.
		if (player1 == null) {
			player1 = new NullPlayer();
		}
		if (player2 == null) {
			player2 = new NullPlayer();
		}		
		
		this.config = config;
		height = config.getGridHeight();
		width = config.getGridWidth();
		
		// Can't run if the dimensions don't make sense.
		if (height < 1 || width < 1) {
			throw new InvalidParameterException("Height and width must be positive numbers");
		}
		
		//A player which never repeats itself should be able to find all ships by hitting every square. Longer games than this are draws.
		maxMoves = height*width;
		
		//Putting the players in a list for more consistent access with zero-indexing.
		this.players = new Player[]{player1, player2};
		
		//Create the boards.
		this.boards = new Boards(height, width);			
		
	}
	
	
	/**
	 * Function to check if the coordinate is valid. 
	 * 
	 * A coordinate is valid if and only if:
	 * 	* x value is within the board bounds (greater or equal to 0 and less 
	 * 		than the grid width)
	 * 	* y value is within the board bounds (greater or equal to 0 and less 
	 * 		than the grid height)
	 * 
	 * 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 * @return true - coordinate is valid
	 * @return false - coordinate is invalid
	 */
	protected boolean isValidCoordinate(int x, int y) {
		if ( x < this.width && x >= 0 && y < this.height && y >= 0) {
			return true;
		} 
		else {
			return false;			
		}
	}
	
	/**
	 * Function to check if the shot is valid.
	 * 
	 * A shot is valid if and only if:
	 * 	* it is within bounds
	 *  * coordinate is not null
	 * 
	 * @param shot - the shot being taken
	 * @return true - shot is valid
	 * @return false - the shot is invalid
	 */
	protected boolean isValidShot(Coordinate shot) {
		if (shot == null) {
			return false;
		} else {
			if (isValidCoordinate(shot.getX(), shot.getY())) {
				return true;
			}
			else {
				return false;			
			}
		}
	}
	
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
		switch (player) {
		case 0:
		case 1:
			return true;
		default:
			throw new InvalidParameterException("Player must be 0 or 1");
		}
	}
	
	/**
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
	protected char getCellState(int player, int x, int y) {
		if (isValidCoordinate(x, y) && isValidPlayer(player)) {
			return boards.getCellState(x, y, player);
		}
		else {
			return (char) -1;
		}
	}
		
	/**
	 * Function to resolve a shot.
	 * 
	 *  * Takes a shot at the enemy board:
	 * 	  * Changes the value at the coordinate specified on the opposite player's board.
	 * 	* Sends a message to the enemy player depending on what is at the given coordinate.
	 * 
	 * Value changes:
	 * 	* If the character is a lower case character - it becomes upper case (hit)
	 * 	* If the character is 0 - it becomes '.' (miss)
	 * 	* If the character is upper case or '.' - do nothing
	 * 
	 * Messages:
	 * 	* If the character is a lower case character - "HIT (x,y)" where x is 
	 * 		the x coordinate and y is the y coordinate
	 * 	* If the whole ship has been sunk - "SUNK 'A'" where 'A' is the ship 
	 * 		handle, without quotation marks around the handle
	 * 	* If the character is 0 - "MISS (x,y)" where x is the x coordinate and 
	 * 		y is the y coordinate
	 * 	* If the character is upper case or '.' - do nothing
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
			char cellState = boards.getCellState(x, y, player^1);
			
			if (cellState >= 'a' && cellState <= 'z') {
				boards.setCellState(x, y, player^1, Character.toUpperCase(cellState));
				players[player].notify("HIT (" + x + "," + y + ")");
				
				//If, after a hit has been made, that ship's handle does not
				//  appear anywhere on the board in lower case, then that ship
				//  has been sunk.
				if (!boards.contains(cellState, player^1)){
					players[player].notify("SUNK " + cellState);
				}
			}
			else if (cellState == 0) {
				boards.setCellState(x, y, player^1, '.');
				players[player].notify("MISS (" + x + "," + y + ")");
			}
		}
		else {
			// If out of bounds, miss.
			players[player].notify("MISS (" + x + "," + y + ")");
			
		}

		
		
	}
	
	/**
	 * Function to resolve a ship placement.
	 * 
	 * It first checks if a ship's placement is valid, then places it if it 
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
	 * If a ship is placed in whole or in part out of bounds, the player is
	 *   notified by the message "SHIP x RAN AGROUND", where x is the ship's handle
	 * 
	 * If a ship is placed over one or more other ships, the player is notified 
	 *   by the message "SHIP x RAN OVER a,b,c....z"
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

		if (ship == null) {
			return;
		}
		else if (shipPlacement == null || !isValidPlayer(player)) {
			throw new ShipPlacementException("SHIP " + ship.getShipHandle() + " RAN AGROUND");
		}
		
		if (shipPlacement.getBeginning() != null && shipPlacement.getDirection() != null) {
			
			Coordinate beginning = shipPlacement.getBeginning();
			int[] ending = {beginning.getX(), beginning.getY()};
			int[] direction = new int[2];
			
			// Set ending to be the coordinate of the end of the ship according to the
			//  direction it is facing, and its length.
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
			default:
				//If we receive any other direction, the ship is not placed.
				throw new ShipPlacementException("SHIP " + ship.getShipHandle() + " RAN AGROUND");
			}
			
			
			if (isValidCoordinate(beginning.getX(), beginning.getY()) && isValidCoordinate(ending[0], ending[1])) {
				// The ship's entire extent is within the board, so we can examine
				//  the cells it occupies.
				ArrayList<Character> collisions = new ArrayList<Character>();
				char handle = ship.getShipHandle();
				int beginx = beginning.getX();
				int beginy = beginning.getY();
				
				// Iterate through each segment of the ship.
				for (int i = 0; i < ship.getShipLength(); i++) {
					int x = beginx + (i * direction[0]);
					int y = beginy + (i * direction[1]);
					
					// If the cell is occupied by another ship, add its handle to 
					//  the list of collisions if it is not already in the list.
					char cellState = boards.getCellState(x, y, player); 
					if (cellState != (char) 0 && !collisions.contains(cellState) ) {
						collisions.add(cellState);
					}
					
					// Set each cell to the handle of the current ship.
					boards.setCellState(x, y, player, handle);
				}
				
				// If there were any collisions, remove the current ship
				//  and all ships which were collided with.
				if (collisions.size() != 0) {
					boards.removeAll(handle, player);
					
					String notification = "SHIP " + handle + " RAN OVER SHIP ";
					
					// Sink the collided ships and add their handles to the notification.
					for (char scuttled : collisions) {
						boards.removeAll(scuttled, player);
						notification += scuttled + ",";
					}
					
					// Remove the final extraneous comma.
					notification = notification.substring(0, notification.length() - 1);
					
					throw new ShipPlacementException(notification);
					
				}
			}
			else {
				// If either the beginning or the ending coordinate are off the grid, the ship runs aground.
				throw new ShipPlacementException("SHIP " + ship.getShipHandle() + " RAN AGROUND");
			}
		
		}
		else {
			// If either of the components inside shipPlacement are null, the ship runs aground.
			throw new ShipPlacementException("SHIP " + ship.getShipHandle() + " RAN AGROUND");
		}
	
	}
	
	/**
	 * Function to get the enemy view of a player's board
	 * 
	 * The view is generated based on these rules:
	 * 	* if the value is upper case, show 'H'
	 * 	* if the value is lower case, show 0
	 * 	* otherwise - the true value
	 * 
	 * @param player - the player whose board you are going to view
	 * @return the view (contains only 'H', '.' and 0)
	 */
	protected char[][] getEnemyView(int player) {
		if (isValidPlayer(player)) {
			return boards.getEnemyView(player);			
		}
		return null;
	}
	
	/**
	 * Function to get the number of ships remaining for a given player.
	 * 
	 * @param player - the player whose ships afloat count to get.
	 * @return int - the number of ships afloat for the player.
	 */
	protected int getNumShipsRemaining(int player) {
		if (!isValidPlayer(player)) {
			return 0;
		}
		
		int remaining = 0;
		
		// For each ship, if its lower case handle appears somewhere on the board,
		//  then it is still floating.
		for (Ship ship : config.getShips()) {
			if (boards.contains(ship.getShipHandle(), player)) {
				remaining++;
			}
		}
		
		return remaining;
	}
	
	/**
	 * Prints out the current state of the game, with some extra diagnostic information.
	 * Delays a little each time it's called in order to allow a game in progress to be
	 *   examined in real time.
	 * 
	 * @param turnNumber - the current turn number.
	 * @param player1Shot - the shot the first player just took.
	 * @param player2Shot - the shot the second player just took.
	 * @param player1ShipsRemaining - number of unsunk ships owned by player 1.
	 * @param player2ShipsRemaining - number of unsunk ships owned by player 2.
	 */
	public void printDebugView(int turnNumber, Coordinate player1Shot, Coordinate player2Shot, int player1ShipsRemaining, int player2ShipsRemaining) {
		try {
			Thread.sleep(DELAY);
			System.out.println("Turn " + turnNumber);
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		System.out.println("=============== player " +  players[0].getClass().getSimpleName() + "'s own board");
		Boards.printBoard(boards.getPlayerBoard(0));
		System.out.println("------------- " + players[0].getClass().getSimpleName() + "'s view of player " + players[1].getClass().getSimpleName());
		Boards.printBoard(getEnemyView(1));
		System.out.println(players[0].getClass().getSimpleName() + " shot: " + player1Shot);
		System.out.println("Player 1 ships remaining: " + player1ShipsRemaining);
		
		System.out.println("=============== player " +  players[1].getClass().getSimpleName() + "'s own board");
		Boards.printBoard(boards.getPlayerBoard(1));
		System.out.println("------------- " + players[1].getClass().getSimpleName() + "'s view of player " + players[0].getClass().getSimpleName());
		Boards.printBoard(getEnemyView(0));
		System.out.println(players[1].getClass().getSimpleName() + " shot: " + player2Shot);
		System.out.println("Player 2 ships remaining: " + player2ShipsRemaining + "\n");
	}
	
	/**
	 * Function to start and run the game.
	 * 
	 * Steps:
	 * 
	 *  * Instantiate the boards
	 *  * Start a new game for each player
	 *  * Place each player's ships on their board. (Placement phase)
	 *  * Loop continually, getting each player's shot and resolving it on the other's board
	 *  *  until the game is over (Shooting phase)
	 *  
	 *  * A player wins if he has ships left and the opponent does not;
	 *     The other player loses.
	 *  * A draw happens if:
	 *    * Both players simultaneously sink the other's last ship;
	 *    * The maximum number of turns is reached. (equal to the number of squares on the board)
	 * 
	 * @return the player that has won ( null if it was a draw )
	 */
	public Player run() {
		// Create the players' boards.
		boards = new Boards(height, width);
		
		// Start a new game for each player, informing them of their opponent's name and the configuration.
		players[0].newGame(players[1].getClass().getCanonicalName(), config);
		players[1].newGame(players[0].getClass().getCanonicalName(), config);
		
		// Placement Phase
		for (Ship ship : config.getShips()) {
			//place player 1 ship
			try {
				placeShip(ship, players[0].getShipPlacement(ship, boards.getPlayerBoard(0)), 0);
			}
			catch (ShipPlacementException e) {
				players[0].notify(e.getMessage());
			}
			
			//place player 2 ship
			try {
				placeShip(ship, players[1].getShipPlacement(ship, boards.getPlayerBoard(1)), 1);
			}
			catch (ShipPlacementException e) {
				players[1].notify(e.getMessage());
			}
		}
		
		Player winner = null;
		boolean gameOver = false;
		int turnNumber = 0;
		
		Coordinate player1Shot;
		Coordinate player2Shot;
		int player1ShipsRemaining;
		int player2ShipsRemaining;
		
		// Shooting Phase
		while (!gameOver) {

			turnNumber++;
			
			player1Shot = players[0].getNextShot(boards.getPlayerBoard(0), getEnemyView(1));
			player2Shot = players[1].getNextShot(boards.getPlayerBoard(1), getEnemyView(0));
			
			resolveShot(player1Shot, 0);
			player2ShipsRemaining = getNumShipsRemaining(1);
			resolveShot(player2Shot, 1);
			player1ShipsRemaining = getNumShipsRemaining(0);
			
			// If DEBUG is true, the current game state will print out to the console, with a pause of DELAY milliseconds between turns 
			if (DEBUG) {
				printDebugView(turnNumber, player1Shot, player2Shot, player1ShipsRemaining, player2ShipsRemaining);
			}
			
			// Terminal Conditions
			if (player1ShipsRemaining == 0 && player2ShipsRemaining == 0) {
				gameOver = true;
				players[0].notify("DRAW");
				players[1].notify("DRAW");
			}
			else if (player1ShipsRemaining == 0) {
				gameOver = true;
				players[0].notify("LOSE");
				players[1].notify("WIN");
				winner = players[1];
			}
			else if (player2ShipsRemaining == 0) {
				gameOver = true;
				players[0].notify("WIN");
				players[1].notify("LOSE");
				winner = players[0];
			}
			else if (turnNumber >= maxMoves) {
				gameOver = true;
				players[0].notify("DRAW");
				players[1].notify("DRAW");
			}
		}
		
		return winner;
	}
	
}