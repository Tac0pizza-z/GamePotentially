package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import gfx.Assets;
import runGame.Game;
import runGame.Handler;
import weapon.melee.Sword;

public class Player extends Creature{
	
	//temp for equipping weapon
	Object equippedWep = new Sword(handler, 10);
	
	//temp for viewing hitboxes
	Rectangle hb = new Rectangle();
	int hbSize = 20;
	
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
		//Attack
		checkAttack();
	}
	
	//temp attack stuff
	private void checkAttack(){
		Rectangle cb = getCollisionBounds(0, 0);
		
		//temp removal for viewing hb
		//Rectangle hb = new Rectangle();
		//int hbSize = 20;
		
		
		hb.width = hbSize;
		hb.height = hbSize;
		
		if(handler.getKeyManager().aUp){
			//center of player
			hb.x = cb.x + cb.width / 2 - hbSize / 2;
			//direction hitting
			hb.y = cb.y - hbSize;
		}else if(handler.getKeyManager().aDown){
			hb.x = cb.x + cb.width / 2 - hbSize / 2;
			hb.y = cb.y + cb.height;			
		}else if(handler.getKeyManager().aLeft){
			hb.x = cb.x - hbSize;
			hb.y = cb.y + cb.height / 2 - hbSize / 2;	
		}else if(handler.getKeyManager().aRight){
			hb.x = cb.x + cb.width;
			hb.y = cb.y + cb.height / 2 - hbSize / 2;	
		}else{
			return;
		}
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(hb)){
				//prob change this to a method once more items in game
				e.hurt(1);
				return;
			}
		}
	}
	
	@Override
	public void die(){
		System.out.println("Player Died");
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
		//view hitbox
		//g.drawRect(hb.x, hb.y, hbSize, hbSize);
	}
}