package SnakeGame;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		// TODO Auto-generated constructor stub	
		this.add(new GamePanel());
		this.setTitle("Snake game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
