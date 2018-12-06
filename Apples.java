package snakeGame;

import java.awt.Color;
import java.awt.Graphics;

public class Apples {
	
	private int xCoor, yCoor, width, height;

	public Apples(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.width = tileSize;
		this.height = tileSize;
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(xCoor * width, yCoor * height, width, height);
		g.setColor(Color.RED);
		g.fillOval(xCoor * width + 2, yCoor * height + 2, width - 4, height - 4);
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
