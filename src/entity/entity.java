package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamepanel;
import main.utiltool;
import monster.mob;

public class entity {
	gamepanel gp;
	
	public int worldX;
	public int worldY;
	public int speed;
	public BufferedImage  idle,down1,down2,down3,left1,left2,left3,right1,right2,right3,up1,up2,up3;
	public BufferedImage attack_up,attack_down,attack_left,attack_right;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0,0,48,48 );
	public Rectangle attackArea = new Rectangle(0,0,0,0);
	public int solidAreadefaultX, solidAreadefaultY;
	public boolean collisionOn = false;
	public int actionlockcounter = 0;
	String dialogues[] = new String [20];
	int dialogueind = 0;
	public String name;
	public int actionLockcounter = 0;
	public boolean invincible = false;
	public int iframes = 0;
	public int type;

	
	public int maxlife;
	public int life;

	
	public entity(gamepanel gp) {
		this.gp = gp;
	}
	public void setAction() {
		
	}
	public void speak() {
		if(dialogues[dialogueind] == null) {
			dialogueind = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueind];
		dialogueind++;
		
		
	}
	public void update() {
		
		setAction();
		
		
		collisionOn = false;
		gp.cCheck.checkTile(this);
		boolean contactPlayer = gp.cCheck.checkPlayer(this);
		gp.cCheck.checkentity(this, gp.npc);
		gp.cCheck.checkentity(this, gp.monster);
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				
				gp.player.life -= 1;
				gp.player.invincible = true;
			}	
		}
		
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":		worldY -= speed;	break;
			case "down":	worldY += speed;	break;
			case "left":	worldX -= speed;		break;
			case "right":	worldX += speed;	break;
			}
			
		}
			
		spriteCounter++;
		if(spriteCounter > 20) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2 ) {
				spriteNum = 3;
				
			}
			else if (spriteNum == 3 ) {
				spriteNum = 1;
				
			}			
			spriteCounter = 0;
	
		}
	
	}
		


	public void draw(Graphics2D g2) {
		BufferedImage Image = null;
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
	public BufferedImage setup(String imagePath, int width, int height)
    {
        utiltool uTool = new utiltool();
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image,width,height);   //it scales to tilesize , will fix for player attack(16px x 32px) by adding width and height
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;

    }
}