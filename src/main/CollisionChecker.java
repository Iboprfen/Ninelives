package main;

import entity.entity;

public class CollisionChecker {
	
	gamepanel gp;
	
	public CollisionChecker(gamepanel gp) {
		this.gp = gp;
		}
	
	public void checkTile (entity entity) {
		
		int entityLeftWorldX = entity.x + entity.solidArea.x;
		int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.y + entity.solidArea.y;
		int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;
		
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
}
