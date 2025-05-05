package main;

import entity.entity;

public class CollisionChecker {
	
	gamepanel gp;
	
	public CollisionChecker(gamepanel gp) {
		this.gp = gp;
		}
	
	public void checkTile (entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tilenum1, tilenum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tilenum1 = gp.tileM.mapTilenum[entityLeftCol][entityTopRow];
			tilenum2 = gp.tileM.mapTilenum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY - entity.speed)/gp.tileSize;
			tilenum1 = gp.tileM.mapTilenum[entityLeftCol][entityBottomRow];
			tilenum2 = gp.tileM.mapTilenum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tilenum1 = gp.tileM.mapTilenum[entityLeftCol][entityTopRow];
			tilenum2 = gp.tileM.mapTilenum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tilenum1 = gp.tileM.mapTilenum[entityRightCol][entityTopRow];
			tilenum2 = gp.tileM.mapTilenum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
				entity.collisionOn = true;
			}
			break;

		}
	}
	public int checkobj(entity entity, boolean player) {
		
		int index = 999;
		
		for (int i = 0;i<gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case"up": entity.solidArea.y -= entity.speed; break;
				case"left": entity.solidArea.x += entity.speed; break;
				case"right": entity.solidArea.x += entity.speed; break;	
				case"down":entity.solidArea.y += entity.speed; break; }
				
				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
					if(player == true) {
						index = i;
					}
				}
				entity.solidArea.x = entity.solidAreadefaultX;
				entity.solidArea.y = entity.solidAreadefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreadefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreadefaultY;
				
			}
			
		}
		
		return index;
		
	}
	public int checkentity(entity entity, entity[] target) {

		int index = 999;
		
		for (int i = 0;i<target.length; i++) {
			
			if(target[i] != null) {
				
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				switch(entity.direction) {
				case"up": entity.solidArea.y -= entity.speed; break;
				case"left": entity.solidArea.x -= entity.speed; break;
				case"right": entity.solidArea.x += entity.speed; break;	
				case"down": entity.solidArea.y += entity.speed; break;
				}
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if(target[i] != entity) {
					entity.collisionOn = true;
					index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreadefaultX;
				entity.solidArea.y = entity.solidAreadefaultY;
				target[i].solidArea.x = target[i].solidAreadefaultX;
				target[i].solidArea.y = target[i].solidAreadefaultY;
				
			}
			
		}
		
		return index;	
		
	}
	
	public boolean checkPlayer(entity entity) {
		
		boolean contactPlayer = false;
		
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case"up": entity.solidArea.y -= entity.speed; break;
		case"left":	entity.solidArea.x -= entity.speed; break;
		case"right": entity.solidArea.x += entity.speed; break;	
		case"down": entity.solidArea.y += entity.speed; break;}
		
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		
		entity.solidArea.x = entity.solidAreadefaultX;
		entity.solidArea.y = entity.solidAreadefaultY;
		gp.player.solidArea.x = gp.player.solidAreadefaultX;
		gp.player.solidArea.y = gp.player.solidAreadefaultY;
		
		return contactPlayer;
		
		
	}
	
	
	
}
