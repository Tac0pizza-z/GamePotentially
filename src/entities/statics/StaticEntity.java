package entities.statics;

import java.awt.Graphics;

import entities.Entity;
import runGame.Handler;

public class StaticEntity extends Entity{
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void die() {
		
	}

}
