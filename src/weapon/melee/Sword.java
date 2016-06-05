package weapon.melee;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import entities.creatures.Player;
import runGame.Handler;

public class Sword extends MeleeWeapons{

	public Sword(Handler handler, float degreeOfWep, int cooldown, int damage, int hbWidth, int hbHeight, boolean onCooldown) {
		super(handler, degreeOfWep, 3, 5, 5, 10, false);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void attack() {
		//fix this in the morning
		Rectangle cb = getCollisionBounds(0, 0);
		if(handler.getKeyManager().aUp){
			//center of player
			Player.setX(cb.x + cb.width / 2 - hbSize / 2);
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
	
	
}
