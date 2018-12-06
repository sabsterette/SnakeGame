package snakeGame;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Main extends JFrame {

	public Main() {

		setTitle("Snake!");
		setResizable(false);
		this.setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);getClass();
		
		init();
		
	}
	
	public void init() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		Board b = new Board();
		add(b);
		
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new Main();
		
	}
	
}
