package weapon.melee;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import entities.creatures.Player;
import input.KeyManager;
import runGame.Handler;
import weapon.Weapon;

public class MeleeWeapon extends Weapon{

	protected Handler handler;
	protected int hbWidth, hbHeight;
	protected double damage, cooldown;
	
	public MeleeWeapon(Handler handler, int hbWidth, int hbHeight, double damage, double cooldown) {
		super(handler, hbWidth, hbHeight, damage, cooldown, false);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	public void attack(Rectangle collBox) {
		//reg attack method, changed if weapon has unique properties
		Rectangle hb = new Rectangle();
		Rectangle cb = collBox;
		if(KeyManager.aUp){
			//set hb
			hb.width = this.getHbWidth();
			hb.height = this.getHbHeight();
			hb.x = cb.x + cb.width / 2 - hb.width / 2;
  			hb.y = cb.y - hb.height;
		}else if(KeyManager.aDown){
			//set hb
			hb.width = this.getHbWidth();
			hb.height = this.getHbHeight();
			hb.x = cb.x + cb.width / 2 - hb.width / 2;
  			hb.y = cb.y + cb.height;
		}else if(KeyManager.aLeft){
			//set hb
			hb.width = this.getHbHeight();
			hb.height = this.getHbWidth();
			hb.x = cb.x - hb.width;
  			hb.y = cb.y + cb.height / 2 - hb.height / 2;
		}else if(KeyManager.aRight){
			//set hb
			hb.width = this.getHbHeight();
			hb.height = this.getHbWidth();
			hb.x = cb.x + cb.height;
 			hb.y = cb.y + cb.height / 2 - hb.height / 2;
		}else{
			return;
		}
		Player.setLastAttack(System.nanoTime());
		//get handler from player.java
		for(Entity e : Player.getPlayerHandler().getWorld().getEntityManager().getEntities()){
			if(e instanceof Player)
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(hb)){
				//prob change once more items in game
				e.hurt(this.getDamage());
				return;
			}
		}
	}
}
