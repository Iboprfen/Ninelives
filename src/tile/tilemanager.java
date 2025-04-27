package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.gamepanel;

public class tilemanager {

	gamepanel  gp;
	public Tile[]  tile;
	public int mapTilenum[][];
	
	public tilemanager (gamepanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		
		mapTilenum = new int [gp.maxworldcol][gp.maxworldrow];
		
		getTileImage();
		
		loadMap("/maps/testmap.txt");
	}
	
	public void getTileImage() {
		
		try {
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cobble.png"));
			tile[0].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			
			}
		catch(IOException e) {
			e.printStackTrace();
			
			}
		
	}
	public void loadMap(String filepath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxworldcol  && row < gp.maxworldrow) {
				
				String line = br.readLine();
				
				while(col < gp.maxworldcol) {
					
					String numbers[] = line.split (" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTilenum[col][row] = num;
					col++;
				}
				if ( col == gp.maxworldcol) {
					col = 0 ;
					row++;
				}
				
			}
			br.close();
	
		}catch(Exception e) {
			
		}
		
		
		
		
		
		
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		int worldcol = 0;
		int worldrow = 0;
		
		
		while(worldcol < gp.maxworldcol  && worldrow < gp.maxworldrow) {
			
			int tilenum = mapTilenum[worldcol][worldrow];
			
			int worldX = worldcol * gp.tileSize;
			int worldY = worldrow * gp.tileSize;
			int screenx = worldX - gp.player.worldX + gp.player.screenX;
			int screeny = worldY - gp.player.worldY + gp.player.screenY;
			
			g2.drawImage(tile[tilenum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);
			worldcol++;
			
			if (worldcol == gp.maxworldcol) {
				
				worldcol = 0;
			
				worldrow++;
			}			
		}
	}
	
}
