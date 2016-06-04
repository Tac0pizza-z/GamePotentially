package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	private boolean[] keys;
	public boolean up, down, left, right;
	//temp attack stuff
	public boolean aUp, aDown, aLeft, aRight;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		//temp attack stuff
		aUp = keys[KeyEvent.VK_I];
		aDown = keys[KeyEvent.VK_K];
		aLeft = keys[KeyEvent.VK_J];
		aRight = keys[KeyEvent.VK_L];
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keys[arg0.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
