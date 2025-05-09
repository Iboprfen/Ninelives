package entity;

import java.awt.Graphics2D;

import main.gamepanel;

public class princess extends entity {
	
	public princess(gamepanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	
	direction = "down1";
	collisionOn = true;
	
	getprincessImage();
	setdialogue();
	}

public void getprincessImage() {
	
		down1 = setup("/princess/maedchen_aussen_weiss_weg_16x16",gp.tileSize, gp.tileSize);
		
	}

public void setdialogue() { 
	
	dialogues[0] = "Du hast mich gerettet! \n Danke mein Held! \n Mwah! xoxo";
		
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
