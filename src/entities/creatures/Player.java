package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import gfx.Assets;
import runGame.Game;
import runGame.Handler;

public class Player extends Creature{
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = DEFAULT_CREATURE_WIDTH - 1;
		bounds.height = DEFAULT_CREATURE_WIDTH - 1;
	}
	
	@Override
	public void tick() {
		input();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	private void input(){
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -spd;
		if(handler.getKeyManager().down)
			yMove = spd;
		if(handler.getKeyManager().left)
			xMove = -spd;
		if(handler.getKeyManager().right)
			xMove = spd;
	}
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), null);
	}
}