package snakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int HEIGHT = 600, WIDTH = 600;
	
	private Thread thread;
	private boolean running = false;
	private Snake snake;
	private ArrayList<Snake> fullSnake;
	
	private int tileSize = 25;
	private int xCoor = 25, yCoor = 25;
	private int size = 5;
	
	private boolean right = true, left = false, up = false, down = false;
	private int ticks = 0;
	
	private Apples apple;
	private Apples apple2;
	private Apples apple3;
	private ArrayList<Apples> apples;
	private Random r;
	private int score = 0;
	
	private int speed = 500000;
	
	private boolean started = false; 
	
	private boolean gameOver = false;
	
	private Rectangle exit;
	private Rectangle faster;
	private Rectangle slower;
	
	public Board() {
		
		setFocusable(true);
		addKeyListener(new Key());
		addMouseListener(new Key());
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		fullSnake = new ArrayList<Snake>();
		
		r = new Random();
		
		apples = new ArrayList<Apples>();
		this.setBackground(Color.BLACK);
		
	}
	
	public void tick() {
		
		if (apples.size() == 0) {
			int x = r.nextInt(WIDTH/tileSize - 1);
			int y = r.nextInt(HEIGHT/tileSize - 1);
			apple = new Apples(x, y, tileSize);
			apples.add(apple);
			
			int x1 = r.nextInt(WIDTH/tileSize - 1);
			int y1 = r.nextInt(HEIGHT/tileSize - 1);
			apple2 = new Apples(x1, y1, tileSize);
			apples.add(apple2);
			
			int x2 = r.nextInt(WIDTH/tileSize - 1);
			int y2 = r.nextInt(HEIGHT/tileSize - 1);
			apple3 = new Apples(x2, y2, tileSize);
			apples.add(apple3);
		}
		// to eat apples and expand
		for (int i = 0; i < apples.size(); i++) {
			if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
				size++;
				score += 10;
				apples.remove(i);
			}
		}
		// if it hits itself
		for (int i = 0; i < fullSnake.size(); i++) {
			if (xCoor == fullSnake.get(i).getxCoor() && yCoor == fullSnake.get(i).getyCoor()) {
				if (i != fullSnake.size() - 1) {
					running = false;
					gameOver = true;
				}
			}

		}
		ticks++;
		
		if (ticks > speed) {
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			
			if (xCoor < 0)
				xCoor = (WIDTH/tileSize) - 1;
			if (xCoor > (WIDTH/tileSize) - 1)
				xCoor = 0;
			if (yCoor < 0)
				yCoor = (HEIGHT/tileSize) - 1;
			if (yCoor > (HEIGHT/tileSize) - 1)
				yCoor = 0;
			
			ticks = 0;
			snake = new Snake(xCoor, yCoor, tileSize, tileSize);
			fullSnake.add(snake);
			
			if (fullSnake.size() > size) 
				fullSnake.remove(0);	
		}
		
	
	}
	
	public void paint(Graphics g) {

		Color myColour = new Color(255, 134, 132, 127);
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		Graphics2D g2d = (Graphics2D) g; 
		
		g2d.setColor(myColour);
		exit = new Rectangle(420, 30, 150, 40);
		g2d.draw(exit);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
		g.drawString("RESTART", 435, 62);
		
		faster = new Rectangle(420, 520, 150, 40);
		g.setColor(Color.BLACK);
		g2d.draw(faster);
		g.setColor(myColour);
		g.drawString("FASTER!", 430, 550);
		
		slower = new Rectangle(30, 520, 150, 40);
		g.setColor(Color.BLACK);
		g2d.draw(slower);
		g.setColor(myColour);
		g.drawString("SLOWER!", 40, 550);
		
		for (int i = 0; i < fullSnake.size(); i++) {
			fullSnake.get(i).draw(g);
		}
		
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
		
		g.setColor(myColour);
		g.fillRect(30, 30, 185, 40);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font ("arial", Font.ITALIC, 35));
		g.drawString("Score: " + score, 40, 60);
		
		if (!started) {
			g.setColor(new Color(255, 134, 132, 200));
			g.fillRect(50, 250, 500, 75);
			g.setColor(Color.WHITE);
			g.setFont(new Font ("arial", Font.ITALIC, 50));
			g.drawString("Press 'Space' to Start", 60, 300);
		} 
		
		if (gameOver) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			g.fillRect(20, 20, 560, 560);
			g.setColor(Color.BLACK);
			g.fillRect(25, 25, 550, 550);
			
			g.setColor(Color.PINK);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
			g.drawString("GAME OVER", 114, 204);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
			g.drawString("SCORE: " + score, 222, 302);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
			g.drawString("Press 'space' to try again!", 62, 402);
			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
			g.drawString("GAME OVER", 110, 200);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
			g.drawString("SCORE: " + score, 220, 300);
			g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
			g.drawString("Press 'space' to try again!", 60, 400);
		}

		
	}
	
	public void start() {
		running = true;
		started = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(running) {
			tick();
			repaint();
		}
	}
	
	public void restart() {
		tileSize = 25;
		xCoor = 25;
		yCoor = 25;
		size = 5;
		fullSnake = new ArrayList<Snake>();
		right = true;
		left = false;
		up = false;
		down = false; 
		apples = new ArrayList<Apples>();
		score = 0;
		speed = 500000;
		start();
	}
	
	private class Key implements KeyListener, MouseListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_RIGHT && !left) {
				right = true;
				up = false;
				down = false;
			}
			
			if (key == KeyEvent.VK_LEFT && !right) {
				left = true;
				up = false;
				down = false;
			}
			
			if (key == KeyEvent.VK_UP && !down) {
				right = false;
				up = true;
				left = false;
			}
			
			if (key == KeyEvent.VK_DOWN && !up) {
				right = false;
				left = false;
				down = true;
			}
			
			if (key == KeyEvent.VK_SPACE) {
				if (!running) {
					gameOver = false;
					restart();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX();
			int y = e.getY();
			//Rectangle(420, 30, 150, 40);
			
			if (x > 420 && x < 570 && y > 30 && y < 70) {
				started = false;
				running = false;
			}
			// faster = new Rectangle(420, 520, 150, 40);
			if (x > 420 && x < 570 && y > 520 && y < 560) {
				if (speed > 100000) 
					speed = speed - 50000;

			}
			//slower = new Rectangle(30, 520, 150, 40);
			if (x > 30 && x < 180 && y > 520 && y < 560) 
				speed = speed + 50000;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}

}
