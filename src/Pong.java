
/*
 * This code is protected under the Gnu General Public License (Copyleft), 2005 by
 * IBM and the Computer Science Teachers of America organization. It may be freely
 * modified and redistributed under educational fair use.
 */

import csta.ibm.pong.Game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/** Main Pong class*/
public class Pong extends Game {
	// Add any state variables or object references here
	private Ball ball, badBall; // if paddle hits player, they loose score
	private Paddle player1, player2;
	private int score1, score2;
	private JLabel scoreDisplay, winDisplay;
	private JOptionPane rules;

	/**
	 * initialize objects
	 */
	public void setup() {
		
		scoreDisplay = new JLabel();
		scoreDisplay.setForeground(Color.WHITE);
		scoreDisplay.setBounds(getFieldWidth()/2-10, 0, 50, 50);
		scoreDisplay.setText("0 : 0");
		add(scoreDisplay);
		
		winDisplay = new JLabel();
		winDisplay.setForeground(Color.WHITE);
		winDisplay.setBounds(getFieldWidth()/2-40, getFieldHeight()/2-30, 100, 100);
		add(winDisplay);
		
		rules = new JOptionPane();
		rules.showMessageDialog(null, "Instructions:\n control the paddle with z and x for player 1 and n and m for player 2 \n"
				+ "stop the ball from passing the paddle otherwise the other player gains a point\n"
				+ "avoid hitting the gray ball as it makes you loose points\n"
				+ "get 10 points to win the game");
		
		repaint();
		
		ball = new Ball();
		ball.setSize(25, 25);
		ball.setX(getFieldWidth()/2);
		ball.setY(getFieldHeight()/2);
		add(ball);
		
		badBall = new Ball();
		badBall.setSize(50, 50);
		badBall.setX(getFieldWidth()/2);
		badBall.setY(getFieldHeight()/2);
		badBall.setColor(Color.GRAY);
		add(badBall);
		
		player1 = new Paddle();
		player1.setSize(10, 50);
		player1.setX(25);
		player1.setY(getFieldHeight()/2-25);
		add(player1);
		
		player2 = new Paddle();
		player2.setSize(10, 50);
		player2.setX(getFieldWidth() - 25);
		player2.setY(getFieldHeight()/2-25);
		add(player2);
	}
	
	/**
	 * reset ball speed, position, and player position
	 */
	public void reset() {
		badBall.setX(getFieldWidth()/2);
		badBall.setY(getFieldHeight()/2);
		
		ball.setX(getFieldWidth()/2);
		ball.setY(getFieldHeight()/2);
		
		player1.setX(25);
		player1.setY(getFieldHeight()/2-25);
		
		player2.setX(getFieldWidth() - 25);
		player2.setY(getFieldHeight()/2-25);
		
		ball.resetSpeed();
		badBall.resetSpeed();
	}

	/**
	 * timer method, includes player movement and ball collision
	 */
	public void act() {
		// Player1 movement
		if (XKeyPressed() && player1.getY() > 0) {
			player1.moveUp();
		}
		else if (ZKeyPressed() && player1.getY() < getFieldHeight() - 50) {
			player1.moveDown();
		}
		
		// Player2 movement
		if (NKeyPressed() && player2.getY() > 0) {
			player2.moveUp();
		}
		else if (MKeyPressed() && player2.getY() < getFieldHeight() - 50) {
			player2.moveDown();
		}
		
		// Ball collision stuff
		if (ball.getX() <= 0) {
			score2++;
			scoreDisplay.setText(score1 + " : " + score2);
			reset();
		}
		if (ball.getX() >= getFieldWidth()) {
			score1++;
			scoreDisplay.setText(score1 + " : " + score2);
			reset();
		}
		if (ball.getY() <= 0) {
			ball.setY(0);
		}
		else if (ball.getY() >= getFieldHeight() - 50) {
			ball.setY(getFieldHeight() - 50);
		}
		if (ball.collides(player1) || ball.collides(player2)) {
//			System.out.println("hit");
			ball.horizontalBounce();
		}
		winCheck(score1, score2);
		
		// bad ball collision stuff
		if (badBall.getX() <= 0 || badBall.getX() >= getFieldWidth()) {
			badBall.horizontalBounce();
		}
		if (badBall.getY() <= 0) {
			badBall.setY(0);
		}
		else if (badBall.getY() >= getFieldHeight() - 50) {
			badBall.setY(getFieldHeight() - 50);
		}
		if (badBall.collides(player1)) { // if player hits the bad ball, they loose points and bad ball position is reset to center of screen
			badBall.setX(getFieldWidth()/2);
			badBall.setY(getFieldHeight()/2);
			if (score1 > 0) {	
				score1--;
				scoreDisplay.setText(score1 + " : " + score2);
//				System.out.println("hit");
			}
		}
		if (badBall.collides(player2) && score2 > 0) {
			badBall.setX(getFieldWidth()/2);
			badBall.setY(getFieldHeight()/2);
			if (score2 > 0) {
				score2--;
				scoreDisplay.setText(score1 + " : " + score2);
//				System.out.println("hit");
			}
		}
	}
	
	/**
	 * check for win conditions, initiate win screen if conditions are met
	 * @param score1 score of player 1
	 * @param score2 score of player 2
	 */
	public void winCheck(int score1, int score2) {
		if (score1 == 10) {
			winDisplay.setText("Player 1 Wins!");
		}
		else if (score2 == 10) {
			winDisplay.setText("Player 2 Wins!");
		}
		repaint();
		if (score1 == 10 || score2 == 10) {
			remove(ball);
			remove(badBall);
			reset();
			this.score1 = 0;
			this.score2 = 0;
			stopGame();
		}
	}

	/**
	 * This code has been provided for you, and should not be modified
	 */
	public static void main(String[] args) {
		Pong p = new Pong();
		
		p.setLocationRelativeTo(null);
		p.setVisible(true);
		p.initComponents();
	}
}