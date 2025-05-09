package entity;

import java.awt.Graphics2D;



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
		
			down1 = setup("/oldman/alter_mann_mit_stock",gp.tileSize, gp.tileSize);
			
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
          g2.drawImage(down1, screenX, screenY,null);
      } 
	}

	public void speak() {
		super.speak();
	}
	
}