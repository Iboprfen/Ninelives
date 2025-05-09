package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import object.obj_heart;
import object.superobject;

public class UI {
	
	gamepanel gp;
	Graphics2D g2;
	Font arial_40;
	BufferedImage heartI;
	
	public boolean messageOn = false;
	int messageCounter = 0;
	public boolean gameFinish = false;
	public String currentDialogue = " ";
	public int commandNum = 0;
	public Timer restartTimer;
	public UI(gamepanel gp){
		
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.BOLD, 40 );
		
		superobject heart = new obj_heart(gp);
		heartI = heart.Image;
	}
	
	public void showMessage(String text) {
		
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		if(gp.gamestate == gp.playstate) {
			drawPlayerlife();
			
		}
		
		if(gp.gamestate == gp.dialogue) { 
			drawPlayerlife();
			drawdialogue(); 
			
		}
		if(gp.gamestate == gp.gameover) {
			
			drawGameOver();
			
		}  
	
	}
	public void drawGameOver() {
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0,0, gp.screenwidth, gp.screenheight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		
		text = "Du bist gestorben";
		
		//schatten
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		
		//text
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
			
}

	public void drawdialogue() {
		
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int height = gp.tileSize*4;
		int width = gp.screenwidth -(gp.tileSize*4);

		drawsubwin(x , y , height , width);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 30;
		}
}

	public void drawPlayerlife() {
		
	
		int x = gp.tileSize/2;
		int y = gp.tileSize /2;
		int i = 0;
		while(i < gp.player.life) {
			g2.drawImage(heartI, x, y,null);
			i++;
			x += gp.tileSize/2;
		}
	
	}
	public void drawsubwin(int x , int y , int height , int width) { 
	
		Color c = new Color(0,0,0,215);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color (255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
	}
	 public int getXforCenteredText(String text)
	    {
	        int textLenght;
	        textLenght = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); // Gets width of text.
	        int x = gp.screenwidth / 2 - textLenght/2;
	        return x;
	    }
}
