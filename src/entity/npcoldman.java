package entity;

import java.awt.Graphics2D;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamepanel;

public class npcoldman extends entity{
	
	public npcoldman(gamepanel gp) {
		super(gp);
		
		direction = "down1";
		collisionOn = true;
		
		getoldmanImage();
		setdialogue();
	
	}
	public void getoldmanImage() {
		
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/oldman/alter_mann_mit_stock.png"));
			
			}
		
		catch(IOException e){
			e.printStackTrace();
			}

	
		}

	public void setdialogue() { 
		
			dialogues[0] = "Bitte retten Sie die Prinzessin!\nDa hinten ist mein Schwert!\nBenutze es um die Monster zu töten!";
			
	
		}
	public void draw(Graphics2D g2) {
	
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

      if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
         worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
         worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
         worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
      {
          g2.drawImage(down1, screenX, screenY,gp.tileSize,gp.tileSize,null);
      } 
	}

	public void speak() {
		super.speak();
	}
	
}