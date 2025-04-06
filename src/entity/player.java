package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Controls;
import main.gamepanel;

public class player extends entity{
	
	gamepanel gp;
	Controls cont;

	
	public player (gamepanel gp, Controls cont) {
		
		this.gp = gp;
		this.cont = cont;
		
		solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 16;
		solidArea.width = 28;
		solidArea.height = 28;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 3;
		direction = "idle";
	}
	
	public void getPlayerImage() {
		
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/cat_down3.png"));
			idle = ImageIO.read(getClass().getResourceAsStream("/player/cat_idle.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/cat_left3.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_right2.png"));
			right3= ImageIO.read(getClass().getResourceAsStream("/player/cat_right3.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/cat_up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/cat_up2.png"));
			up3= ImageIO.read(getClass().getResourceAsStream("/player/cat_up3.png"));
			}
		
		catch(IOException e){
			e.printStackTrace();
			}
		
		
		
	}
	
	public void update () {
		
		if(cont.upPressed == false || cont.downPressed == false || cont.leftPressed == false || cont.rightPressed == false) {
			
			direction = "idle";
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
		
		
		if(cont.upPressed == true || cont.downPressed == true || cont.leftPressed == true || cont.rightPressed == true) {

			if(cont.upPressed == true) {
				direction = "up";
				
			}
			
			if(cont.downPressed == true) {
				direction = "down";
				
			}
			
			if(cont.leftPressed == true) {
				direction = "left";
				
			}
			
			if(cont.rightPressed == true) {
				direction = "right";
				
			}
		
			//check tile collision
			collisionOn = false;
			gp.cCheck.checkTile(this);
			
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":	y -= speed;	break;
				case "down":	y += speed;	break;
				case "left":	x -= speed;	break;
				case "right":	x += speed;	break;
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
	}
		
	
	public void  draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			if(spriteNum == 3) {
				image = up3;
		
			}
				break;
		
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			if(spriteNum == 3) {
				image = down3;
		
			}
			break;	
		
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			if(spriteNum == 3) {
				image = left3;
		
			}
			break;
		
		case "right":
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				if(spriteNum == 3) {
					image = right3;
			
				}
				break;
		case "idle":
			if(spriteNum == 1) {
				image = idle;
				
			}
			if(spriteNum == 2) {
				image = idle;
				
			}
			if(spriteNum == 3) {
				image = idle;
				
			}
			break;
		
		}
	g2.drawImage(image, x, y, gp.tileSize , gp.tileSize , null);
	
	}
	
} 