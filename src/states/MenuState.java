package states;

import java.awt.Graphics;

import gfx.Assets;
import runGame.Handler;

public class MenuState extends State{
	
	public MenuState(Handler handler){
		super(handler);
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, 10, 10, null);
	}
	

}
