package test;

import java.awt.Event;

import javax.swing.JFrame;
import javax.swing.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

public class Game implements EventListener, KeyListener {

	private Snake player;
	private Food food;
	private Graphics graphics;
	public static final int width = 30;
	public static final int height = 30;
	public static final int dimension = 20;
	private JFrame window;

	public Game() {
		window = new JFrame();

		player = new Snake();
		food = new Food(player);
		graphics = new Graphics(this);

		window.add(graphics);

		window.setTitle("Snake");
		window.setSize(width * dimension + 2, height * dimension + 4);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void start() {
		graphics.state = "RUNNING";
	}

	public void update() {
		if (graphics.state == "RUNNING") {
			if (foodCollision()) {
				player.grow();
				food.random_spawn(player);
			} else if (wallCollision() || selfCollision()) {
				graphics.state = "END";
			} else {
				player.move();
			}
		}
	}

	private boolean wallCollision() { // detects collision of the snake into a wall.
		if (player.getX() < 0 || player.getX() >= width * dimension || player.getY() < 0
				|| player.getY() >= height * dimension) {
			return true;
		}
		return false;
	}

	private boolean foodCollision() {// detects collision of the snake into a foood object.
		if (player.getX() == food.getX() * dimension && player.getY() == food.getY() * dimension) {
			return true;
		}
		return false;
	}

	private boolean selfCollision() { // detects collision of the snake into its own body.
		for (int i = 1; i < player.getBody().size(); i++) {
			if (player.getX() == player.getBody().get(i).x && player.getY() == player.getBody().get(i).y) {
				return true;
			}
		}
		return false;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (graphics.state == "RUNNING") {
			if (keyCode == KeyEvent.VK_W) {
				player.up();
			} else if (keyCode == KeyEvent.VK_S) {
				player.down();
			} else if (keyCode == KeyEvent.VK_A) {
				player.left();
			} else {
				player.right();
			}
		} else {
			this.start();
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public Snake getPlayer() {
		return player;
	}

	public void setPlayer(Snake player) {
		this.player = player;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}
}
