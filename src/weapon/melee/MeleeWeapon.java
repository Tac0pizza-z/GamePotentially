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
	protected int hbWidth, hbHeight, damage, cooldown;
	private Player player;
	
	public MeleeWeapon(Handler handler, int hbWidth, int hbHeight, int damage, int cooldown) {
		super(handler, hbWidth, hbHeight, damage, cooldown, false);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	public void attack() {
		//reg attack method, changed if weapon has unique properties
		Rectangle cb = player.getCollisionBounds(0, 0);
		Rectangle hb = new Rectangle();
		handler.getKeyManager();
		if(KeyManager.aUp){
			//set hb
			hb.width = player.getEquippedWep().getHbWidth();
			hb.height = player.getEquippedWep().getHbHeight();
			hb.x = cb.x + cb.width / 2 - hb.width / 2;
			hb.y = cb.y - hb.height;
		}else if(KeyManager.aDown){
			//set hb
			hb.width = player.getEquippedWep().getHbWidth();
			hb.height = player.getEquippedWep().getHbHeight();
			hb.x = cb.x + cb.width / 2 - hb.width / 2;
			hb.y = cb.y + cb.height;
		}else if(KeyManager.aLeft){
			//set hb
			hb.height = player.getEquippedWep().getHbWidth();
			hb.width = player.getEquippedWep().getHbHeight();
			hb.x = cb.x - hb.width;
			hb.y = cb.y + cb.height / 2 - hb.height / 2;
		}else if(KeyManager.aRight){
			//set hb
			hb.height = player.getEquippedWep().getHbWidth();
			hb.width = player.getEquippedWep().getHbHeight();
			hb.x = cb.x + cb.height;
			hb.y = cb.y + cb.height / 2 - hb.height / 2;
		}else{
			return;
		}
		player.setLastAttack(System.nanoTime());
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(hb)){
				//prob change once more items in game
				e.hurt(this.getDamage());
				return;
			}
		}
	}
}
