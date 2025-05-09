package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.pathfinder;
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
	
	
	public tilemanager tileM = new tilemanager (this);
	Controls cont = new Controls(this); 
	Thread gameThread;
	public eventhandle eH = new eventhandle(this);
	public UI ui = new UI(this);
	public CollisionChecker cCheck = new CollisionChecker(this);
	public assetsetter aset = new assetsetter(this);
	public superobject obj[] = new superobject[10];
	ArrayList<entity> entityList = new ArrayList<>();
	public pathfinder pfind = new pathfinder (this);
	
	
	public player player = new player(this,cont);
 	public entity monster [] = new entity[20];
	public entity npc [] = new entity [10];
	public entity boss[] = new entity[1];
	
	// Spiel Status
	public int gamestate;
	public final int playstate = 1;
	public final int dialogue = 2;
	public final int gameover = 3;
	
	
	
	
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
		aset.setboss();
		gamestate = playstate;
		
		
		
	}
	 public void restart() {
	       
		 player.setDefaultValues();
		 aset.setobj();
		 aset.setNPC();
		 aset.setmonster();
		 aset.setboss();
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
		for(int i = 0; i<boss.length; i++) {
			if(boss[i] != null) {
				boss[i].update();
			}
		}
		
	}
		
		
		else if(gamestate  == dialogue) {
			ui.drawdialogue();
		}
		else if(gamestate == gameover) {
			ui.drawGameOver();
		}
}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		
		tileM.draw(g2);
	
		entityList.add(player);
		
		
		for(int i = 0;i< npc.length; i++) {
			if(npc[i] !=null) {
				entityList.add(npc[i]);
			}
			
		}
		for(int i = 0;i<obj.length; i++) {
			if(obj[i] !=null) {
				obj[i].draw(g2, this);
			}
			
		}
		for(int i = 0;i< monster.length; i++) {
			if(monster[i] !=null) {
				entityList.add(monster[i]);
			}
		}
			for(int i = 0;i< boss.length; i++) {
				if(boss[i] !=null) {
					entityList.add(boss[i]);
				}
			
			}
	
		Collections.sort(entityList, new Comparator<entity>() {
			public int compare(entity e1, entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
				return result;
			}
		
		});
		
		for(int i = 0; i < entityList.size(); i++) {
			entityList.get(i).draw(g2);
		}
		for(int i = 0; i < entityList.size(); i++) {
			entityList.remove(i);
		}
		
		ui.draw(g2);
		
		g2.dispose();
	}
}


