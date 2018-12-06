package snakeGame;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	
	private int xCoor, yCoor, height, width;

	public Snake(int xCoor, int yCoor, int height, int width) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.height = height;
		this.width = width;
	}
	
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xCoor * width, yCoor * height, width, height);
		g.setColor(Color.PINK);
		g.fillRect(xCoor * width + 2, yCoor * height + 2, width - 4, height - 4);
	}
}
