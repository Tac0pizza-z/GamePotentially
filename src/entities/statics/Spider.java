package entities.statics;

import java.awt.Graphics;

import gfx.Assets;
import runGame.Handler;
import tiles.Tile;

public class Spider extends StaticEntity{
	
	public Spider(Handler handler, float x, float y){
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		//hitboxes
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = width;
		bounds.height = height;
	}
	
	@Override
	public void tick(){
		System.out.println(this.health);
	}
	
	@Override
	public void die(){
		System.out.println("Spider Died");
	}
	
	@Override
	public void render(Graphics g){
		g.drawImage(Assets.enemy, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
}