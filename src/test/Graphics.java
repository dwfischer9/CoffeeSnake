package test;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer t = new Timer(100, this);
	public String state;

	private Snake s;
	private Food f;
	private Game game;

	private int w = Game.width; // shortening these variable names
	private int h = Game.height;
	private int d = Game.dimension;

	public Graphics(Game g) {
		t.start();
		state = "START";

		game = g;
		s = g.getPlayer();
		f = g.getFood();

		// adds a keyListener to the game

		this.addKeyListener(g);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, w * d + 5, h * d + 5);

		if (state == ("START")) { // displays a start screen and prompts the user to press any key to begin
			g2d.setColor(Color.white);
			g2d.drawString("Press Any Key", w / 2 * d - 40, h / 2 * d - 20);
		} else if (state == "RUNNING") {

			g2d.setColor(Color.red); //draws the first food item
			g2d.fillRect(f.getX() * d, f.getY() * d, d, d);

			g2d.setColor(Color.green); // draws each rectangle composing the body of the snake
			for (Rectangle r : s.getBody()) {
				g2d.fill(r);
			}
		} else {
			g2d.setColor(Color.white); // displays a game over screen, and displays the user's score.
			g2d.drawString("Your Score: " + (s.getBody().size() - 3), w / 2 * d - 40, h / 2 * d - 20);
		}
	}

	public void actionPerformed(ActionEvent e) {
		repaint();

		game.update();
	}
}