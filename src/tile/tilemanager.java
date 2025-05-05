package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;


import main.gamepanel;
import main.utiltool;

public class tilemanager {

	gamepanel  gp;
	public Tile[]  tile;
	public int mapTilenum[][];
	
	public tilemanager (gamepanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		
		mapTilenum = new int [gp.maxworldcol][gp.maxworldrow];
		
		getTileImage();
		
		loadMap("/maps/map.txt");
	}
	
	public void getTileImage() {
		
		setup(0, "cobble", true);
		setup(1, "grass", false);
		setup(2, "steine_16x16", false);
		setup(3, "water", true);
		
	}
	public void setup(int index, String imageName, boolean collision)
    {                                                                  
        utiltool uTool = new utiltool();                             
        try                                                             
        {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch (IOException e)
        {
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
	
	
	 public void draw(Graphics2D g2)
	    {
	        int worldCol = 0;
	        int worldRow = 0;
	        while(worldCol < gp.maxworldcol && worldRow < gp.maxworldrow)
	        {
	            int tileNum = mapTilenum[worldCol][worldRow]; //drawing current map

	            int worldX = worldCol * gp.tileSize;
	            int worldY = worldRow * gp.tileSize;
	            int screenX = worldX - gp.player.worldX + gp.player.screenX;
	            int screenY = worldY - gp.player.worldY + gp.player.screenY;

	            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
	               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
	               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
	            {
	                g2.drawImage(tile[tileNum].image, screenX, screenY,gp.tileSize,gp.tileSize,null);
	            }

	            worldCol++;

	            if(worldCol == gp.maxworldcol)
	            {
	                worldCol = 0;
	                worldRow++;
	            }
	        }
	
}
}