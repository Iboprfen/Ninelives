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
		
		mapTilenum = new int [gp.maxScreencol][gp.maxScreenrow];
		
		getTileImage();
		
		loadMap("/maps/maptest.txt");
	}
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cobble.png"));
			tile[1].collision = true;
			
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
			
			while(col < gp.maxScreencol  && row < gp.maxScreenrow) {
				
				String line = br.readLine();
				
				while(col < gp.maxScreencol) {
					
					String numbers[] = line.split (" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTilenum[col][row] = num;
					col++;
				}
				if ( col == gp.maxScreencol) {
					col = 0 ;
					row++;
				}
				
			}
			br.close();
	
		}catch(Exception e) {
			
		}
		
		
		
		
		
		
		
	}
	
	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreencol  && row < gp.maxScreenrow) {
			
			int tilenum = mapTilenum[col][row];
			
			g2.drawImage(tile[tilenum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if (col == gp.maxScreencol) {
				
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
			
		}
	}
	
}
