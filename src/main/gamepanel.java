package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.entity;
import entity.player;
import object.superobject;
import tile.tilemanager;

public class gamepanel extends JPanel implements Runnable{
	//Screensettings
	final int originalTileSize = 16; //16x16
	final int scale = 3;	
	
	public final int tileSize = originalTileSize * scale; // 48x48
	public final int maxScreencol = 16;
	public final int maxScreenrow = 12;
	public final int screenwidth = tileSize * maxScreencol; //768 pixel
	public final int screenheight = tileSize * maxScreenrow;// 576 pixel
	
	public final int maxworldcol = 50;
	public final int maxworldrow = 50;
	public final int worldwidth = tileSize * maxworldcol;
	public final int worldheight = tileSize * maxworldrow;
	
	
	
	
	//FPS
	int fps = 60;
	
	tilemanager tileM = new tilemanager (this);
	Controls cont = new Controls(this); 
	Thread gameThread;
	public eventhandle eH = new eventhandle(this);
	public UI ui = new UI(this);
	public CollisionChecker cCheck = new CollisionChecker(this);
	public assetsetter aset = new assetsetter(this);
	public superobject obj[] = new superobject[10];
	
	public player player = new player(this,cont);
 	public entity monster [] = new entity[20];
	public entity npc [] = new entity [10];
	
	public int gamestate;
	public final int playstate = 1;
	public final int dialogue = 2;
	
	
	
	
	public gamepanel() {
		
		this.setPreferredSize(new Dimension (screenwidth, screenheight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(cont);
		this.setFocusable(true);
		
		
		
	}
	
	public void setupGame() {
		
		aset.setobj();
		aset.setNPC();
		aset.setmonster();
		gamestate = playstate;
		
		
		
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
		
		
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1){
				update();
				repaint();
				delta--;
				
			}
		
			if(timer >= 1000000000) {
				
		
				timer = 0;
				
			}
			
		}
	}
	public void update() {
		
		if(gamestate == playstate) {
		player.update();
		
		
		for(int i = 0; i<npc.length; i++) {
			if(npc[i] != null) {
				npc[i].update();
			}
		}
		for(int i = 0; i<monster.length; i++) {
			if(monster[i] != null) {
				monster[i].update();
			}
		}
		
	}
		if(gamestate  == dialogue) {
			ui.drawdialogue();
		}
}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		
		tileM.draw(g2);
	
		for(int i = 0;i< obj.length; i++) {
			if(obj[i] !=null) {
				obj[i].draw(g2, this);
			}
			
		}
		
		for(int i = 0;i< npc.length; i++) {
			if(npc[i] !=null) {
				npc[i].draw(g2);
			}
			
		}
		for(int i = 0;i< monster.length; i++) {
			if(monster[i] !=null) {
				monster[i].draw(g2);
			}
			
		}
	
		
		player.draw(g2);
		 
		ui.draw(g2);
		
		g2.dispose();
	}
}


