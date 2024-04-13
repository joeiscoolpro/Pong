
/*
 * This code is protected under the Gnu General Public License (Copyleft), 2005 by
 * IBM and the Computer Science Teachers of America organization. It may be freely
 * modified and redistributed under educational fair use.
 */

import csta.ibm.pong.GameObject;

public class Paddle extends GameObject {
	// Add any state variables here

	/**
	 * Fill in this method with code that describes the behavior of a paddle
	 * from one moment to the next
	 */
	public void act() {
	}

	/**
	 * moves paddle upwards
	 */
	public void moveUp() {
		setY(getY() - 10);
	}
	
	/**
	 * moves paddle downwards
	 */
	public void moveDown() {
		setY(getY() + 10);
	}
}
