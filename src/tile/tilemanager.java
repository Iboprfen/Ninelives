package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamepanel;

public class tilemanager {

	gamepanel  gp;
	Tile[]  tile;
	
	
	public tilemanager (gamepanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		
		getTileImage();
	}
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cobble.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			
			}
		catch(IOException e) {
			e.printStackTrace();
			
			}
		
	}
	
	public void draw(Graphics2D g2) {
		
		g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize,null );
		g2.drawImage(tile[1].image, 0, 48, gp.tileSize, gp.tileSize,null );
		
	}
	
}
