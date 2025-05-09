package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {

	gamepanel gp;
	
	public boolean upPressed,downPressed,leftPressed,rightPressed,attacking;


	public Controls(gamepanel gp) {
		this.gp = gp;
	}
	
	
	


	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(gp.gamestate == gp.playstate) {
		
		playstate(code);
	 }
	
		else if(gp.gamestate == gp.dialogue) {
				
			if(code == KeyEvent.VK_E) {
				gp.gamestate = gp.playstate;
			}
	
			
		else if(gp.gamestate == gp.gameover) {
				gameoverState(code);
			}	
		}
}
	public void playstate(int code) {
		if(code == KeyEvent.VK_SPACE) {
			attacking = true;
		}	
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
	}
	public void gameoverState(int code){
		
		
		if(code == KeyEvent.VK_E) {
			
				gp.restart();
		
			}
		}
		
	

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		

		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	
	}
	

}
