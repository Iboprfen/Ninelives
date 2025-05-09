package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class obj_sword extends superobject {

	
	public obj_sword( ) {
		
		name = "sword";
		try {
				Image = ImageIO.read(getClass().getResourceAsStream("/schwert/majestatisches_schwert_16x16.png"));
			
			}
		
		catch(IOException e){
			e.printStackTrace();
			}
		
		
	}
	
}