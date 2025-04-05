package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.player;
import tile.tilemanager;

public class gamepanel extends JPanel implements Runnable{
	//Screensettings
	final int originalTileSize = 16; //16x16
	final int scale = 3;	
	
	public final int tileSize = originalTileSize * scale; // 48x48
	final int maxScreencol = 16;
	final int maxScreenrow = 12;
	final int screenwidth = tileSize * maxScreencol; //768 pixel
	final int screenheight = tileSize * maxScreenrow;// 576 pixel
	
	//FPS
	int fps = 60;
	
	tilemanager tileM = new tilemanager (this);
	Controls cont = new Controls(); 
	Thread gameThread;
	player player = new player(this,cont);
	
	public gamepanel() {
		
		this.setPreferredSize(new Dimension (screenwidth, screenheight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(cont);
		this.setFocusable(true);
		
		
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	

	@Override
	public void run() {
		
		double drawInterval = 1000000000/fps;
		double delta =	0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1){
				update();
				repaint();
				delta--;
				drawCount++; 
			}
		
			if(timer >= 1000000000) {
				
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
				
			}
			
		}
	}
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
}


