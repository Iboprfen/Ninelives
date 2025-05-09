package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamepanel;
import main.utiltool;

public class obj_heart extends superobject {
	gamepanel gp;
	public utiltool uTool = new utiltool();
	
public obj_heart(gamepanel gp) {
		
		name = "heart";
		try {
				Image = ImageIO.read(getClass().getResourceAsStream("/player/heart.png"));
				Image = uTool.scaleImage(Image,gp.tileSize/2,gp.tileSize/2);
			}
		
		catch(IOException e){
			e.printStackTrace();
			}
		
		
	}
	
}