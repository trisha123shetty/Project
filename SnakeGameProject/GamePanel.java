package SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	static final int screen_height=600;
	static final int screen_width=600;
	
	static final int unit_size=25;// how big we want the items in the game
	static final int game_unit=(screen_width*screen_height)/unit_size;
	static final int delay=75;// how fast is the game is running
	
	final int x[]=new int[game_unit];
	final int y[]=new int[game_unit];
	int bodyParts=6;
	int appleEaten;
	int appleX;
	int appleY;
	char direction='R';
	boolean running=false;
	Timer timer;
	Random random;
 public GamePanel() {
	// TODO Auto-generated constructor stub
	 random = new Random();
	 this.setPreferredSize(new Dimension(screen_width,screen_height));
	 this.setBackground(Color.black);
	 this.setFocusable(true);
	 this.addKeyListener(new myKeyAdapter());
	startGame(); 
}
 
 public void startGame() {
	 newApple();//To create a new apple in the screen
	 running=true;
	 timer = new Timer(delay,this);
	 timer.start();
	 
 }
 public void  paintComponent(Graphics g) {
	 super.paintComponent(g);// calls Jpanel's default paint behavior
	 draw(g);
 }
 
 public void draw(Graphics g) {
	 if(running) {
//		 for(int i=0;i<screen_height/unit_size;i++) {// to draw line to become matrix
//			 g.drawLine(i*unit_size, 0, i*unit_size,screen_height);//y axis
//			 g.drawLine(0, i*unit_size, screen_width, i*unit_size);//x_axis line
//		 }
		 g.setColor(Color.red);
		 g.fillOval(appleX, appleY, unit_size, unit_size);//this will create a apple with round shape

		 //drawing snake
		 for(int i=0;i<bodyParts;i++) {
			 if(i==0)//head of the snake
			 {
				 g.setColor(new Color(45,100,0));
				 g.fillRect(x[i], y[i], unit_size, unit_size);
			 }else {//body of the snake
				 g.setColor(Color.green);
//				 g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				 g.fillRect(x[i], y[i], unit_size, unit_size);

			 }
		 }
		 g.setColor(Color.red);
		 g.setFont(new Font("Times new Roman",Font.BOLD,40));
		 FontMetrics metrics = getFontMetrics(g.getFont());
		 g.drawString("Score :"+appleEaten, (screen_width-metrics.stringWidth("Score :"+appleEaten))/2, g.getFont().getSize());
		 
	 }else {
		 gameOver(g);
	 }
 
 }
 
 public void newApple() {// to generate the new Cordinates of the apple
 appleX= random.nextInt((int)screen_width/unit_size)*unit_size;//x cordinate
 appleY=random.nextInt((int)screen_height/unit_size)*unit_size;//y cordinate
 }
 public void move() {
	 
	 for(int i=bodyParts;i>0;i--) {
		 x[i]=x[i-1];
		 y[i]=y[i-1];
	 }
	 // for tracking the size of snake;
	 
	 switch (direction) {
	 case 'U':
		 y[0]=y[0]-unit_size;
		 break;
	 case 'D':
		 y[0]=y[0]+unit_size;
		 break;
	 case 'L':
		 x[0]=x[0]-unit_size;
		 break;
	 case 'R':
		 x[0]=x[0]+unit_size;
		 break;
		 }
 }
 public void checkApple() {
	 if((x[0]== appleX) &&(y[0]==appleY)) {
		 bodyParts++;
		 appleEaten++;
		 newApple();
	 }
 }
 
 public void checkCollision() {
	 // checks if head collides with body
	for(int i=bodyParts;i>0;i--) {
		if((x[0] ==x[i] )&&(y[0]==y[i])) {
			running = false;
		}
	}
	//checks if head left border
	if(x[0]<0) {
		running= false;
	}
	//checks if head touches right border
	if(x[0]>screen_width) {
		running=false;
	}
	// checks if head touches top border\
	if(y[0]<0) {
		running=false;
	}
	// checks if head touches bottom border\
	if(y[0]>screen_height) {
		running = false;
	}
	if(running==false) {
		timer.stop();
	}
 }
 
 public void gameOver(Graphics g) {
	 //game over text
	 g.setColor(Color.red);
	 g.setFont(new Font("Times new Roman",Font.BOLD,75));
	 FontMetrics metrics1 = getFontMetrics(g.getFont());
	 g.drawString("Game Over", (screen_width-metrics1.stringWidth("Game Over"))/2, screen_height/2);
	 g.setColor(Color.red);
	 g.setFont(new Font("Times new Roman",Font.BOLD,40));
	 FontMetrics metrics2 = getFontMetrics(g.getFont());
	 g.drawString("Score :"+appleEaten, (screen_width-metrics2.stringWidth("Score :"+appleEaten))/2, g.getFont().getSize());
	 
	 
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(running) {
			move();
			checkApple();
			checkCollision();
		}
			repaint();
		
		
	}

	public class myKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			//for controlling  snake movement by using keyboard arrow
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction!='R') {
					direction='L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction!='L') {
					direction='R';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction!='U') {
					direction='D';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction!='D') {
					direction='U';
				}
				break;
			}
		}
	}
	
}
