package simulation;

import core.Direction;

/**
 * 
 * Simulation class
 * 
 * @author Clifford Phan
 *
 */
public class Simulation {
	
	Ant ant;
	Grid grid;

	/**
	 * Initialise instance variables in this constructor
	 * 
	 * @param height - the height of the grid
	 * @param width - the width of the grid
	 * @param antStartI - the original I coordinate of the ant
	 * @param antStartJ - the original J coordinate of the ant
	 * @param originalDirection - the original direction the ant is facing
	 */
	public Simulation(int height, int width, int antStartI, int antStartJ,
			Direction originalDirection, int maxTimeSteps) {
		ant = new Ant(antStartI, antStartJ, originalDirection);
		grid = new Grid(height, width);
	}

	/**
	 * Execute a time step for the simulation.
	 * 
	 * The ant must:
	 * 		* move forward 1 space
	 * 			- if this movement cause it to move off the grid, 
	 * 				the simulation is completed.
	 * 		* rotate depending on the state of the cell the ant is occupying
	 * 			- if the cell is white, rotate left
	 * 			- otherwise, rotate right
	 * 		* change the state of the cell the ant is currently occupying
	 * 			- if the cell is white, it becomes black
	 * 			- otherwise, it becomes white
	 * 
	 * NOTE: Using the i,j coordinate system that are in the Ant class means that
	 *       0 <= i < height and 0 <= j < width
	 *
	 * NOTE: this method should do nothing if the simulation is completed.
	 */
	public void executeStep() {
		
		int iPos = ant.getIPos();
		int jPos = ant.getJPos();
		Direction d = ant.getDirection();
		/* Step 1 - Moving forward */
		switch(d) {
		case NORTH:
			iPos = iPos - 1;
			ant.setIPos(iPos);
			break;
		case EAST:
			jPos = jPos + 1;
			ant.setJPos(jPos);
			break;
		case SOUTH:
			iPos = iPos + 1;
			ant.setIPos(iPos);
			break;
		case WEST:
			jPos = jPos - 1;
			ant.setJPos(jPos);
			break;
		}
		
		if (isCompleted() == true) {
			return;
		}
		
		/* Step 2 - Rotating */
		if (grid.grid[iPos][jPos] == false) {
			switch (d) {
			case NORTH:
				ant.setDirection(Direction.WEST);
				break;
			case SOUTH:
				ant.setDirection(Direction.EAST);
				break;
			case WEST:
				ant.setDirection(Direction.SOUTH);
				break;
			case EAST:
				ant.setDirection(Direction.NORTH);
				break;
			}
		} 
		else {
			switch (d) {
			case NORTH:
				ant.setDirection(Direction.EAST);
				break;
			case SOUTH:
				ant.setDirection(Direction.WEST);
				break;
			case WEST:
				ant.setDirection(Direction.NORTH);
				break;
			case EAST:
				ant.setDirection(Direction.SOUTH);
				break;
			}
		}
		
		/* Step 3 - Changing cell state */
		if (grid.grid[iPos][jPos] == false) {
			grid.setBlack(iPos, jPos);
		} else
			grid.setWhite(iPos, jPos);
		
		System.out.println("ANT AT GRID[" + iPos + "][" + jPos +"]");
	}

	/**
	 * Method to check if the simulation is completed.
	 * 
	 * The simulation is completed if and only if:
	 * 		* it has reached the maximum time steps allowed
	 * 		* the ant has moved off the grid
	 * 
	 * @return true - the simulation is completed
	 * @return false - the simulation is not completed
	 */
	public boolean isCompleted() {
		int iPos = ant.getIPos();
		int jPos = ant.getJPos();
		int height = grid.getHeight();
		int width = grid.getWidth();
		
		if (iPos > height || jPos > width || iPos < 0 || jPos < 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method to return a copy of the current grid.
	 * 
	 * You should always return a copy of an object if you do not
	 * want your base object to be changed by any code calling this method.
	 * 
	 * @return a copy of the grid.
	 */
	public Grid getCopyOfGrid() {
		return grid;
	}

	/**
	 * Method to return a copy of the current ant.
	 * 
	 * You should always return a copy of an object if you do not
	 * want your base object to be changed by any code calling this method.
	 * 
	 * @return a copy of the ant.
	 */
	public Ant getCopyOfAnt() {
		return ant;
	}
}
