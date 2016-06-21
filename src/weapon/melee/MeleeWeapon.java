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
		Rectangle hb = new Rectangle();
		if(KeyManager.aUp){
			//set hb
			hb.width = player.getEquippedWep().getHbWidth();
			hb.height = player.getEquippedWep().getHbHeight();
			hb.x = (int) (player.getX() + player.getWidth() / 2 - hb.width / 2);
			hb.y = (int) (player.getY() - hb.height);
		}else if(KeyManager.aDown){
			//set hb
			hb.width = player.getEquippedWep().getHbWidth();
			hb.height = player.getEquippedWep().getHbHeight();
			hb.x = (int) (player.getX() + player.getWidth() / 2 - hb.width / 2);
			hb.y = (int) (player.getY() + player.getHeight());
		}else if(KeyManager.aLeft){
			//set hb
			hb.height = player.getEquippedWep().getHbWidth();
			hb.width = player.getEquippedWep().getHbHeight();
			hb.x = (int) (player.getX() - hb.width);
			hb.y = (int) (player.getY() + player.getHeight() / 2 - hb.height / 2);
		}else if(KeyManager.aRight){
			//set hb
			hb.height = player.getEquippedWep().getHbWidth();
			hb.width = player.getEquippedWep().getHbHeight();
			hb.x = (int) (player.getX() + player.getHeight());
			hb.y = (int) (player.getY() + player.getHeight() / 2 - hb.height / 2);
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
