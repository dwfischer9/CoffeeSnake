package test;

import java.awt.Rectangle;

public class Food {
	private int x; // represents the x tile
	private int y; // represents the y tile

	public Food(Snake player) {
		this.random_spawn(player);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void random_spawn(Snake player) {
		boolean onSnake = true;
		while (onSnake) {
			onSnake = false;
			x = (int) (Math.random() * Game.width - 1); // generate new coordinates until they do not 
			y = (int) (Math.random() * Game.height - 1); // lie on top of the snake
			for (Rectangle r : player.getBody()) {
				if (r.x == x && r.y == y) {
					onSnake = true;
				}
			}
		}
	}
}
