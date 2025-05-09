package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamepanel;
import main.utiltool;

public class entity {
	gamepanel gp;
	
	public int worldX;
	public int worldY;
	public int speed;
	public BufferedImage  idle,down1,down2,down3,left1,left2,left3,right1,right2,right3,up1,up2,up3;
	public BufferedImage attack_up,attack_down,attack_left,attack_right;
	public String direction = "down";
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
	public BufferedImage Image;
	public boolean collision = false;
	public boolean onpath = false;
	public int princessspeak = 0;

	
	public int maxlife;
	public int life;

	
	public entity(gamepanel gp) {
		this.gp = gp;
	}
	public void setAction() {
		
	}
	public void damagereact() {}
	public void speak() {
		if(dialogues[dialogueind] == null) {
			dialogueind = 0;
			
		}
		gp.ui.currentDialogue = dialogues[dialogueind];
		dialogueind++;
		
		
	}
	public void checkcollision() {
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
		
	}
	public void update() {
		

		
		
		
		setAction();
		checkcollision();
		
		
		
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":		worldY -= speed;	break;
			case "down":	worldY += speed;	break;
			case "left":	worldX -= speed;	break;
			case "right":	worldX += speed;	break;
			}
			
		}
			
		spriteCounter++;
		if(spriteCounter > 7) {
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
		

		if(invincible == true) {
			iframes++;
			if(iframes > 40) {
				invincible = false;
				iframes = 0;
			}
		}
	
	}
	public BufferedImage setup(String imagePath, int width, int height) {
	    utiltool uTool = new utiltool();
	    BufferedImage image = null;

	    try {
	        System.out.println("Loading image from: " + imagePath + ".png");
	        image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
	        if (image == null) {
	            System.err.println("Failed to load image: " + imagePath + ".png");
	        } else {
	            System.out.println("Image loaded successfully.");
	            System.out.println("Original Image Dimensions: " + image.getWidth() + "x" + image.getHeight());
	            image = uTool.scaleImage(image, width, height);
	            System.out.println("Image scaled successfully.");
	            System.out.println("Scaled Image Dimensions: " + image.getWidth() + "x" + image.getHeight());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return image;
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        System.out.println("Image: " + image);
        System.out.println("Tile Size: " + gp.tileSize);
        System.out.println("Screen X: " + screenX);
        System.out.println("Screen Y: " + screenY);

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            if (invincible == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }

            g2.drawImage(image, screenX, screenY, null);
        }
    }
	public void searchpath(int goalcol,int goalrow) {
		
		int startcol = (worldX + solidArea.x)/gp.tileSize;
		int startrow = (worldY + solidArea.y)/gp.tileSize;
		
		gp.pfind.setnodes(startcol, startrow, goalcol, goalrow , this);
		
		if(gp.pfind.search() == true) {
			
			int nextX = gp.pfind.pathlist.get(0).col*gp.tileSize;
			int nextY = gp.pfind.pathlist.get(0).row*gp.tileSize;
			
			int enleftX = worldX + solidArea.x;
			int enrightX = worldX + solidArea.x + solidArea.width;
			int entopY = worldY + solidArea.y;
			int enbottomY = worldY + solidArea.y + solidArea.height;
			
			if(entopY > nextY && enleftX >= nextX && enrightX < nextX + gp.tileSize) {
				
				direction = "up";
			}

			else if(entopY < nextY && enleftX >= nextX && enrightX < nextX + gp.tileSize) {
				
				direction = "down";
			}
			else if(entopY >= nextY && enbottomY < nextY  + gp.tileSize) {
		
					if(enleftX > nextX) {
						direction = "left";
					}
					if(enleftX < nextX) {
						direction = "right";
						
					}
					else if(entopY > nextY && enleftX > nextX) {
						
						direction = "up";
						checkcollision();
						if(collision == true) {
							direction = "left";
						}
					}
					else if(entopY > nextY && enleftX < nextX) {
						
						direction = "up";
						checkcollision();
						if(collision == true) {
							direction = "right";
						}
					}
					else if (entopY < nextY && enleftX > nextX) {
						
						direction = "down";
						checkcollision();
						if(collision == true) {
							direction = "left";
						}
					}
					else if (entopY < nextY && enleftX < nextX) {
						
						direction = "down";
						checkcollision();
						if(collision == true) {
							direction = "right";
						}
					}	
			}
			//int nextcol = gp.pfind.pathlist.get(0).col;
			//int nextrow = gp.pfind.pathlist.get(0).row;
			//if(nextcol == goalcol && nextrow == goalrow) {
			//	onpath = false;
			}
		}
		
	}
          

	
	

	