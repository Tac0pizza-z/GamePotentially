package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entities.creatures.Player;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	//make sure to fix the what happens when the mouse exits the screen
	private static boolean leftPressed;
	private static boolean rightPressed;
	private static boolean midPressed;
	private int mouseX, mouseY;
	
	public MouseManager(){
		
	}
	
	public float getAngle(Player player) {
	    float angle = (float) Math.toDegrees(Math.atan2(player.getY() - mouseY, player.getX() - mouseX));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
	
	//getters
	public static boolean isLeftPressed(){
		return leftPressed;
	}
	
	public static boolean isRightPressed(){
		return rightPressed;
	}
	
	public static boolean isMidPressed() {
		return midPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	//detect mouse usage
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = true;
		}else if(e.getButton() == MouseEvent.BUTTON2){
			midPressed = true;
		}else if(e.getButton() == MouseEvent.BUTTON3){
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = false;
		}else if(e.getButton() == MouseEvent.BUTTON2){
			midPressed = false;
		}else if(e.getButton() == MouseEvent.BUTTON3){
			rightPressed = false;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = -10;
		mouseY = -10;
	}


}
