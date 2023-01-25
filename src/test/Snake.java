package test;

import java.util.ArrayList;
import java.awt.Rectangle;

public class Snake {
	private ArrayList<Rectangle> body;
	private int w = Game.width; // shortening these variable names
	private int h = Game.height;
	private int d = Game.dimension;

	private String move; // NOTHING, UP, DOWN, LEFT, RIGHT

	public Snake() {
		body = new ArrayList<>(); // initializing the snake
		Rectangle temp = new Rectangle(d, d);// rectangles are stored in an ArrayList, and the snake always starts with
												// three body segments.

		temp.setLocation(w / 2 * d, h / 2 * d); // adding the first segment
		body.add(temp);

		temp = new Rectangle(d, d); // adding the second body segment
		temp.setLocation((w / 2 - 1) * d, (h / 2) * d);
		body.add(temp);

		temp = new Rectangle(d, d); // adding the third body segment
		temp.setLocation((w / 2 - 2) * d, (h / 2) * d);
		body.add(temp);

		move = "RIGHT";
	}

	public void move() {
		if (move != "NOTHING") {
			Rectangle first = body.get(0);// get the 'head of the snake'
			Rectangle temp = new Rectangle(d, d);

			if (move == "UP")
				temp.setLocation(first.x, first.y - d); // move the head of the snake one coordinate up.

			else if (move == "DOWN")
				temp.setLocation(first.x, first.y + d); // move the head of the snake one coordinate down.

			else if (move == "LEFT")
				temp.setLocation(first.x - d, first.y); // move the head of the snake one coordinate left.

			else
				temp.setLocation(first.x + d, first.y); // move the head of the snake one coordinate right.

			body.add(0, temp); // add the new head to the snake
			body.remove(body.size() - 1);// delete the last element in the snake
		}
	}

	public void grow() {
		Rectangle head = body.get(0);
		Rectangle temp = new Rectangle(d, d);
		if (move == "UP")
			temp.setLocation(head.x, head.y - d);
		else if (move == "DOWN")
			temp.setLocation(head.x, head.y + d);
		else if (move == "LEFT")
			temp.setLocation(head.x - d, head.y);
		else
			temp.setLocation(head.x + d, head.y);
		body.add(0, temp); // adds the new body segment to the head of the snake
	}

	public ArrayList<Rectangle> getBody() {
		return body;
	}

	public void up() {// cannot move in the opposite to current direction as it will cause collision
						// and end the game
		if (move != "DOWN")
			move = "UP";
	}

	public void down() {
		if (move != "UP") // cannot move in the opposite to current direction as it will cause collision
							// and end the game
			move = "DOWN";
	}

	public void left() {// cannot move in the opposite to current direction as it will cause collision
						// and end the game
		if (move != "RIGHT")
			move = "LEFT";
	}

	public void right() {// cannot move in the opposite to current direction as it will cause collision
							// and end the game
		if (move != "LEFT")
			move = "RIGHT";
	}

	// ----------GETTERS & SETTERS----------//
	public int getX() { // returns the x position of the head of the snake
		return body.get(0).x;
	}

	public int getY() { // returns the y position of the head of the snake
		return body.get(0).y;
	}

	public void setBody(ArrayList<Rectangle> body) {
		this.body = body;
	}
}