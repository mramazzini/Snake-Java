import java.awt.*;  
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.*;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
  
public class Main extends Canvas implements Runnable{
	 private  Snake snake;
	 private Apple apple;
	 private int score = 0;
	 private boolean running= true;
	 
    public void paint(Graphics g) {
    	
    	
    	//Draw Brown Backdrop
    	g.setColor(new Color(102,51,0));
    	g.fillRect(0, 0, 1000, 1000);
    	
    	
    	//Draw field
    	for(int i=0; i<17; i++) {
        	
        	if(i%2==0) {
        		g.setColor(new Color(0,102,0));
        	}
        	else {
        		g.setColor(new Color(0,150,0));
        	}
        	g.fillRect(50, 50+(i*50), 875, 100+(i*50));
        }

    	//Draw Snake Body
        g.setColor(Color.black);
        for(int i=1; i<snake.bodyLength(); i++) {
        	g.fillRect(snake.bodyCoordinates_X()[i]*25, snake.bodyCoordinates_Y()[i]*25, 24, 24);
        }
        //Draw Snake HEad
        g.setColor(Color.blue);
        g.fillRect(snake.head_X(), snake.head_Y(), 24,24);
        
        //Draw Apple
        g.setColor(Color.red);
        g.fillRect(apple.getX(), apple.getY(),24,24);
        
        g.setColor(new Color(102,51,0));
    	g.fillRect(0, 900, 1000, 1000);
        
        
    }
    
    public Main() {
    	 
        KeyListener listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                char k = e.getKeyChar();
                
                if(k=='w' && !snake.direction().equals("south") && !snake.decidedToMove()){
            
                    snake.setDirection("north");
                    snake.decideToMove(true);
                }
                if(k=='s' && !snake.direction().equals("north") && !snake.decidedToMove()){
                    snake.setDirection("south");
                    snake.decideToMove(true);
                }
                if(k=='a' && !snake.direction().equals("east") && !snake.decidedToMove()){
                    snake.setDirection("west");
                    snake.decideToMove(true);
                }
                if(k=='d' && !snake.direction().equals("west") && !snake.decidedToMove()){
                    snake.setDirection("east");
                    snake.decideToMove(true);
                }
                if(e.getKeyCode() == 1 && running == false) {
                	running = true;
                	score = 0;
                	snake = new Snake();
                	
                }

            }
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
        };
        snake = new Snake();
        apple = new Apple();
        
        JFrame f=new JFrame();
        JPanel p = new JPanel();
        f.addKeyListener(listener);
        f.setResizable(false);
        f.add(p);
        f.add(this);
        p.setFocusable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setSize(993,1010);  
        //f.setLayout(null);  
        f.setVisible(true);  
        
        
        Thread t1 = new Thread(this);
        t1.run();
    }
    
    public void updateSnake() {
    	
    	//Old arrays
    	int[] x_vals = snake.bodyCoordinates_X();
    	int[] y_vals = snake.bodyCoordinates_Y();
    	
    	
    	//New Arrays 
    	int[] x_vals2 = new int[x_vals.length];
    	int[] y_vals2 = new int[y_vals.length];
    	
    	if(snake.direction().equals("north")) {
    		x_vals2[0] = x_vals[0];
    		y_vals2[0] = y_vals[0]-1;
    	}
    	if(snake.direction().equals("south")) {
    		x_vals2[0] = x_vals[0];
    		y_vals2[0] = y_vals[0]+1;
    	}
    	if(snake.direction().equals("east")) {
    		x_vals2[0] = x_vals[0]+1;
    		y_vals2[0] = y_vals[0];
    	}
    	if(snake.direction().equals("west")) {
    		x_vals2[0] = x_vals[0]-1;
    		y_vals2[0] = y_vals[0];
    	}
    	
    	
    	for(int i=0; i<x_vals.length-1; i++) {
    		
    		x_vals2[i+1] = x_vals[i];
    		y_vals2[i+1] = y_vals[i];
    		
    		
    		
    	}
    	snake.set_body_X(x_vals2);
    	snake.set_body_Y(y_vals2);
    	
    }
    public void checkCollision() {
    	if(apple.getX() == snake.head_X() && apple.getY() == snake.head_Y()) {
    		
    		score++;
    		System.out.println(score);
    		apple.moveApple();
    		snake.addTail();
    	}
    	
    	for(int i=1; i<snake.bodyCoordinates_X().length; i++) {
    		if(snake.bodyCoordinates_X()[i] == snake.bodyCoordinates_X()[0] && snake.bodyCoordinates_Y()[i] == snake.bodyCoordinates_Y()[0]) {
    			running = false;
    		}
    	}
    	if(snake.head_X()<50 || snake.head_X()>900 || snake.head_Y()<50 || snake.head_Y()>900) {
    		running = false;
    	}
    }
    
    
    public void run()
    {
        try {
            while(true){
            	if(running == true) {
                Thread.sleep(50);
                updateSnake();
                checkCollision();
                repaint();
            	}
            	snake.decideToMove(false);
            }
        }
        catch(InternalError | InterruptedException i1){
            System.out.println("thread err");
        }
        
    }
    
    

}

