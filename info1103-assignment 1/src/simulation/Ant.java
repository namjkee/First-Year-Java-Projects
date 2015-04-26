package simulation;

import core.Direction;

/**
 * 
 * Ant class
 * 
 * Container class to hold the information about the ant.
 * 
 * @author Clifford Phan
 *
 */
public class Ant {
	
	Simulation simulation;
	Grid antGrid;
	private int iPos;
	private int jPos;
	private Direction direction;
	
	public Ant(int iPos, int jPos, Direction direction){
		this.iPos = iPos;
		this.jPos = jPos;
		this.direction = direction;
	}
	
	public int getIPos() {
		return iPos;
	}
	
	protected void setIPos(int iPos) {
		this.iPos = iPos;
	}
	
	public int getJPos() {
		return jPos;
	}
	protected void setJPos(int jPos) {
		this.jPos = jPos;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	protected void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}