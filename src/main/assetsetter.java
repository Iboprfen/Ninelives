package main;

import entity.npcoldman;
import monster.boss;
import monster.mob;
import object.obj_sword;
import entity.princess;

public class assetsetter {
	
	gamepanel gp;
	public assetsetter (gamepanel gp) {
		this.gp = gp;
	}

	public void setobj() {
		
		gp.obj[0] = new obj_sword();
		gp.obj[0].worldX = 26 * gp.tileSize;
		gp.obj[0].worldY = 45 * gp.tileSize;
		
		

	}
public void setNPC() {
		
		gp.npc[0] = new npcoldman(gp);
		gp.npc[0].worldX = gp.tileSize*24;
		gp.npc[0].worldY = gp.tileSize*45;
		
		gp.npc[1] = new princess(gp);
		gp.npc[1].worldX = gp.tileSize*26;
		gp.npc[1].worldY = gp.tileSize*10;
		
		
	}
public void setmonster() {
	
		gp.monster[0] = new mob(gp);
		gp.monster[0].worldX = gp.tileSize*19;
		gp.monster[0].worldY = gp.tileSize*39;
		
		gp.monster[1] = new mob(gp);
		gp.monster[1].worldX = gp.tileSize*22;
		gp.monster[1].worldY = gp.tileSize*34;
		
		gp.monster[2] = new mob(gp);
		gp.monster[2].worldX = gp.tileSize*26;
		gp.monster[2].worldY = gp.tileSize*36;
		
		gp.monster[3] = new mob(gp);
		gp.monster[3].worldX = gp.tileSize*24;
		gp.monster[3].worldY = gp.tileSize*35;
		
		gp.monster[4] = new mob(gp);
		gp.monster[4].worldX = gp.tileSize*19;
		gp.monster[4].worldY = gp.tileSize*28;

		gp.monster[5] = new mob(gp);
		gp.monster[5].worldX = gp.tileSize*20;
		gp.monster[5].worldY = gp.tileSize*24;

		gp.monster[6] = new mob(gp);
		gp.monster[6].worldX = gp.tileSize*24;
		gp.monster[6].worldY = gp.tileSize*27;

		

	}
public void setboss() {
	gp.boss[0] = new boss(gp);
	gp.boss[0].worldX = gp.tileSize*20;
	gp.boss[0].worldY = gp.tileSize*15;
	
}
}
