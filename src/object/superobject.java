package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.gamepanel;

public class superobject {
	
	public BufferedImage Image;
	public String name;
	public boolean collision = false;
	public int worldX,worldY;
	public Rectangle solidArea = new Rectangle (0,0,48,48);
	public int solidAreadefaultX = 0;
	public int solidAreadefaultY = 0;
	
	public void draw(Graphics2D g2,gamepanel gp) {
		
		
		
		   int screenX = worldX - gp.player.worldX + gp.player.screenX;
           int screenY = worldY - gp.player.worldY + gp.player.screenY;

           if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
              worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
              worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
              worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
           {
               g2.drawImage(Image, screenX, screenY,gp.tileSize,gp.tileSize,null);
           }
		
		
	}
	
}