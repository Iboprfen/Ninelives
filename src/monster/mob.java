package monster;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.entity;
import main.gamepanel;

public class mob extends entity{
	
	gamepanel gp;


	public mob(gamepanel gp) {
		super(gp);
		this.gp = gp;
		
		type = 2;
		name = "Monster";
		speed = 2;
		maxlife = 4;
		life = maxlife;
		direction = "idle"; 
		
		solidArea.x = 16;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 40;
		solidAreadefaultX = solidArea.x;
		solidAreadefaultY =	solidArea.y;
		
		getImage();
		setAction();
		
	}

	public void getImage()  {
		
		try{
			idle = ImageIO.read(getClass().getResourceAsStream("/monster/mob.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/monster/mob.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/monster/mob.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/monster/mob.png"));
			right1  = ImageIO.read(getClass().getResourceAsStream("/monster/mob.png"));
			
			}
		catch(IOException e){
			e.printStackTrace();
			}
	
	}
	public void setAction(){
		 actionLockcounter++;

         if(actionLockcounter == 120)
         {
             Random random = new Random();
             int i = random.nextInt(100) + 1;  // pick up  a number from 1 to 100
             if(i <= 25)
             {
                 direction = "up";
             }
             if(i>25 && i <= 50)
             {
                 direction = "down";
             }
             if(i>50 && i <= 75)
             {
                 direction = "left";
             }
             if(i>75 && i <= 100)
             {
                 direction = "right";
             }
             actionLockcounter = 0; // reset
	}
	
  }
	
	public void draw(Graphics2D g2) {
		

		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
      if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
         worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
         worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
         worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
      {
          g2.drawImage(idle, screenX, screenY,gp.tileSize,gp.tileSize,null);
      } 
	}
}
