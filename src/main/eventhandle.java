package main;


public class eventhandle {
	
	gamepanel gp;
	eventrect eventRect [][];	
	
	int previouseventX, previouseventY;
	boolean canTouch = true;
	
	public eventhandle(gamepanel gp) { 
		this.gp = gp;
		
		eventRect = new eventrect [gp.maxworldcol][gp.maxworldrow];
		
		int col = 0;
		int row = 0;
		while(col < gp.maxworldcol && row < gp.maxworldrow) {
		
		eventRect[col][row] = new eventrect();
		eventRect[col][row].x = 23;
		eventRect[col][row].y = 23;
		eventRect[col][row].width = 10;
		eventRect[col][row].height = 10;
		eventRect[col][row].eventrectdefaultX = eventRect[col][row].x;
		eventRect[col][row].eventrectdefaultY = eventRect[col][row].y;
		
		col++;
		if(col == gp.maxworldcol) {
			col = 0;
			row++;
		}
	}
}

	public void checkevent() {
		
		int Xdist = Math.abs(gp.player.worldX - previouseventX);
		int Ydist = Math.abs(gp.player.worldY - previouseventY);
		int distance = Math.max(Xdist, Ydist);
		if(distance > gp.tileSize) {
			canTouch = true;
		}
		
		if(canTouch == true) {
		if(hit(26,45,"any") == true) {schwertpickup(24,42,gp.dialogue);}
		}
	
	}
	public boolean hit(int col, int row, String reqDirect) {
		
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
		
		if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventdone == false) {
			if(gp.player.direction.contentEquals(reqDirect) || reqDirect.contentEquals("any")) {
				hit = true;
				
				previouseventX = gp.player.worldX;
				previouseventY = gp.player.worldY;
			}
		}
		gp.player.solidArea.x = gp.player.solidAreadefaultX;
		gp.player.solidArea.y = gp.player.solidAreadefaultY;
		eventRect[col][row].x = eventRect[col][row].eventrectdefaultX;
		eventRect[col][row].y = eventRect[col][row].eventrectdefaultY;
		
		return hit;
	}
	public void schwertpickup(int col, int row,int gamestate) {
		gp.gamestate = gamestate;
		gp.ui.currentDialogue = "Du has das Schwert aufgehoben!";
		canTouch = false;
		eventRect [col][row].eventdone = true;
	}
}
