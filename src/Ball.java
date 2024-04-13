
/*
 * This code is protected under the Gnu General Public License (Copyleft), 2005 by
 * IBM and the Computer Science Teachers of America organization. It may be freely
 * modified and redistributed under educational fair use.
 */

import csta.ibm.pong.GameObject;
import java.util.Random;

public class Ball extends GameObject {
	// Add any state variables here
	private double speedX, speedY;
	private static Random r;
	private int time = 0, dirX = 1, dirY = 1, lastYHit = 0, lastXHit = 0;
	
	/**
	 * initialize objects and variables
	 */
	public Ball() {
		r = new Random();
		speedX = 1;
		speedY = 1;
		resetSpeed();
	}
	/**
	 * update ball position and update ball movement speed
	 */
	public void act() {
		time += 1;
		setX((int) (getX() + speedX));
		setY((int) (getY() + speedY));
		
//		if (speedX < speedCap && speedX > -speedCap) {
			updateSpeedX(time);
			updateSpeedY(time);
//		}
//		System.out.printf("%f\n", speedX);
	}

	// Add any additional methods here
	/**
	 * move ball back to stop ball from clipping and switch x direction
	 */
	public void horizontalBounce() {
//		speedX *= -1;
		if (lastXHit + 10 > time) return;
		dirX *= -1;
		setX((int) (getX() + speedX));
		lastXHit = time;
	}
	/**
	 * reset the speed of the ball and randomly set direction
	 */
	public void resetSpeed() {
		if (r.nextInt(2) == 0) {
			speedX *= -1;
			dirX *= -1;
		}
		if (r.nextInt(2) == 0) {
			speedY *= -1;
			dirY *= -1;
		} 
	}
	/**
	 * update x component of speed through a sin function
	 * @param val is a value speedX is dependent on
	 */
	public void updateSpeedX(double val) {
//		speedX *= val;
		speedX = Math.abs(5.0 * Math.sin(val / 1000.0)) * dirX;
	}
	
	/**
	 * update y component of speed through a sin function
	 * @param val is a value speedY is dependent on
	 */
	public void updateSpeedY(double val) {
//		speedY *= val;
		speedY = Math.abs(r.nextDouble() * 10) * dirY +  + 10 * Math.sin(val / 10.0);
	}
}
