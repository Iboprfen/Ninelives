package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Controls;
import main.gamepanel;



public class player extends entity{
	
	
	Controls cont;
	

	public final int screenX;
	public final int screenY;
	public int hasSword= 0;
	
	
	public player (gamepanel gp, Controls cont) {
		
		super(gp);
		
		this.cont = cont;
		
		screenX = gp.screenwidth/2 - (gp.tileSize/2);
		screenY = gp.screenheight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 16;
		solidArea.width = 28;
		solidArea.height = 28;
		solidAreadefaultX = solidArea.x;
		solidAreadefaultY =	solidArea.y;	
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImg();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 47;
		speed = 3;
		direction = "idle";
		
		maxlife = 9;		
		life = maxlife;
				
				
	}
	
	public void getPlayerImage() {
		
	
			down1 = setup("/player/cat_down1",gp.tileSize, gp.tileSize);
			down2 = setup("/player/cat_down2",gp.tileSize, gp.tileSize);
			down3 = setup("/player/cat_down3",gp.tileSize, gp.tileSize);
			idle = setup("/player/cat_idle",gp.tileSize, gp.tileSize);
			left1 = setup("/player/cat_left1",gp.tileSize, gp.tileSize);
			left2 = setup("/player/cat_left2",gp.tileSize, gp.tileSize);
			left3 = setup("/player/cat_left3",gp.tileSize, gp.tileSize);
			right1 = setup("/player/cat_right1",gp.tileSize, gp.tileSize);
			right2 = setup("/player/cat_right2",gp.tileSize, gp.tileSize);
			right3= setup("/player/cat_right3",gp.tileSize, gp.tileSize);
			up1 = setup("/player/cat_up1",gp.tileSize, gp.tileSize);
			up2 = setup("/player/cat_up2",gp.tileSize, gp.tileSize);
			up3= setup("/player/cat_up3",gp.tileSize, gp.tileSize);
	}
	public void getPlayerAttackImg(){
		
		
			attack_up = setup("/player/attack_up",gp.tileSize, gp.tileSize * 2);
			attack_left = setup("/player/attack_left",gp.tileSize*2, gp.tileSize);
			attack_right = setup("/player/atack_right",gp.tileSize*2, gp.tileSize);
			attack_down = setup("/player/attac_down",gp.tileSize, gp.tileSize*2);
			
		
		
		}
		
	
	
	public void update () {
		
		if(cont.attacking == true) {
			
			attacking();
			
		}
		
		else if(cont.upPressed == false || cont.downPressed == false || cont.leftPressed == false || cont.rightPressed == false) {
			
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
			
			int objIndex = gp.cCheck.checkobj(this, true);
			pickup(objIndex);
			
			
			
			int npcindex = gp.cCheck.checkentity(this, gp.npc);
			interactnpc(npcindex);
			
			
			int monsterindex = gp.cCheck.checkentity(this, gp.monster);
			contactMonster(monsterindex);
			
			gp.eH.checkevent();
			
			
			if(collisionOn == false) {
				
				switch(direction) {
				case "up":		worldY -= speed;	break;
				case "down":	worldY += speed;	break;
				case "left":	worldX-= speed;		break;
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
		
		if(invincible == true) {
			iframes++;
			if(iframes > 60) {
				invincible = false;
				iframes = 0;
			}
		}
	
	}

	public void attacking() {
		
		
			spriteCounter++;
			if(spriteCounter > 30) {
				if(spriteNum == 1) {
					spriteNum = 2;
					int currentWorldX = worldX;
					int currentWorldY = worldY;
					int solidAreaWidth = solidArea.width;
					int solidAreaHeight = solidArea.height;
					
					switch(direction) {
					
					case "up" : worldY -= attackArea.height; break;
					case "down" : worldY += attackArea.height; break;
					case "left" : worldX -= attackArea.width; break;
					case "right" : worldX += attackArea.width; break;
					}
					
					solidArea.width = attackArea.width;
					solidArea.height = attackArea.height;
					
					int monsterindex = gp.cCheck.checkentity(this, gp.monster);
					damageMonster(monsterindex);					
					worldX = currentWorldX;
					worldY = currentWorldY;
					solidArea.width = solidAreaWidth;
					solidArea.height = solidAreaHeight;
					
				}
				else if (spriteNum == 2 ) {
					
					
					spriteNum = 3;
					
			
					
					
				}
				else if (spriteNum == 3 ) {
					spriteNum = 1;
					
				}			
				spriteCounter = 0;
				cont.attacking = false;
		
			}
		}

	public void pickup(int i) {
		
		if(i != 999) {
			
			String objectname = gp.obj[i].name;
			
			switch(objectname) {
			case "sword" :
				hasSword++;
				gp.obj[i] = null;
			 break;
			}
		}
	}
	
	public void interactnpc(int i) {
		
		if(i != 999) {
			collisionOn = true;
			
			gp.gamestate = gp.dialogue;
			gp.npc[i].speak();
			
		}
	}
	public void contactMonster(int i){
		
		if(i != 999) {
			if(invincible == false) {
			life -= 1;
			invincible = true;
			}
		}
		
	}
	public void damageMonster(int i) { 
		
		if(i != 999) {
			
			System.out.println("lol");
			
		}
		else {
			System.out.println("miss");
		}
	}
	
	public void  draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if( cont.attacking == false) {
			if(spriteNum == 1) {image = up1;}
			if(spriteNum == 2) {image = up2;}
			if(spriteNum == 3) {image = up3;}
			}
			if(cont.attacking == true) {
				if(spriteNum == 1) {image = attack_up;}
				if(spriteNum == 2) {image = attack_up;}
				if(spriteNum == 3) {image = attack_up;}
			}
				break;
		case "down":
			if( cont.attacking == false) {
			if(spriteNum == 1) {image = down1;}
			if(spriteNum == 2) {image = down2;}
			if(spriteNum == 3) {image = down3;}
			}
			if(cont.attacking == true) {
				if(spriteNum == 1) {image = attack_down;}
				if(spriteNum == 2) {image = attack_down;}
				if(spriteNum == 3) {image = attack_down;}
			}
			break;	
		case "left":
			if( cont.attacking == false) {
			if(spriteNum == 1) {image = left1;}
			if(spriteNum == 2) {image = left2;}
			if(spriteNum == 3) {image = left3;}
			}
			if(cont.attacking == true) {
				if(spriteNum == 1) {image = attack_left;}
				if(spriteNum == 2) {image = attack_left;}
				if(spriteNum == 3) {image = attack_left;}
			}
			break;
		case "right":
			if( cont.attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
			}
			if(cont.attacking == true) {
				if(spriteNum == 1) {image = attack_right;}
				if(spriteNum == 2) {image = attack_right;}
				if(spriteNum == 3) {image = attack_right;}
			}
			break;
		case "idle":
			if( cont.attacking == false) {
			if(spriteNum == 1) {image = idle;}
			if(spriteNum == 2) {image = idle;}
			if(spriteNum == 3) {image = idle;}
			}
			if(cont.attacking == true) {
				if(spriteNum == 1) {image = attack_down;}
				if(spriteNum == 2) {image = attack_down;}
				if(spriteNum == 3) {image = attack_down;}
			}
			break;
		
		}
		
		if(invincible == true) {
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			
		}
		
		
		g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);
	
	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	//g2.setFont(new Font("Arial",Font.PLAIN,26));
	//g2.setColor(Color.white);
	//g2.drawString("iframes : "+ iframes,10,400);
	
	}
	
} 