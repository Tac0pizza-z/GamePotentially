package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import entities.Entity;
import gfx.Assets;
import input.MouseManager;
import runGame.Game;
import runGame.Handler;
import weapon.melee.Sword;

public class Player extends Creature{
	
	private Graphics2D g;
	//player
	private Player player;
	//mouse input
	private MouseManager mouseManager;
	//temp for equipping weapon
	Sword equippedWep = new Sword(handler, 10);
	//last attack
	private long lastAttack = 0;
	//temp for viewing rect
	Rectangle hb = new Rectangle();
	
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
		checkAttack(g);
	}
	
	private boolean attackAvailable(){
		long now = System.nanoTime();
		if(now - lastAttack >= equippedWep.getCooldown() * 1000000000 || lastAttack == 0){
			return true;
		}
		return false;
	}
	
	private void checkAttack(Graphics2D g){
		Rectangle cb = getCollisionBounds(0, 0);
		hb.width = equippedWep.getHbWidth();
		hb.height = equippedWep.getHbHeight();
		//problems here
		hb.x = (int) player.getX();
		hb.y = (int) player.getY();
		//old code
		/**
		if(handler.getKeyManager().aUp && checkIfAttackAvailable()){
			//set hb
			hb.width = equippedWep.getHbWidth();
			hb.height = equippedWep.getHbHeight();
			hb.x = cb.x + cb.width / 2 - hb.width / 2;
			hb.y = cb.y - hb.height;
		}else if(handler.getKeyManager().aDown && checkIfAttackAvailable()){
			//set hb
			hb.width = equippedWep.getHbWidth();
			hb.height = equippedWep.getHbHeight();
			hb.x = cb.x + cb.width / 2 - hb.width / 2;
			hb.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft && checkIfAttackAvailable()){
			//set hb
			hb.height = equippedWep.getHbWidth();
			hb.width = equippedWep.getHbHeight();
			//doesnt work
			hb.x = cb.x - hb.width;
			hb.y = cb.y + cb.height / 2 - hb.height / 2;
		}else if(handler.getKeyManager().aRight && checkIfAttackAvailable()){
			//set hb
			hb.height = equippedWep.getHbWidth();
			hb.width = equippedWep.getHbHeight();
			//doesnt work
			hb.x = cb.x + cb.height;
			hb.y = cb.y + cb.height / 2 - hb.height / 2;
		}else{
			return;
		}
		**/
		//new code (doesnt work)
		if(mouseManager.isLeftPressed() && attackAvailable()){
			g.rotate(mouseManager.getAngle(player));
		}
		lastAttack = System.nanoTime();
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(hb)){
				//prob change this to a method once more items in game
				e.hurt(equippedWep.getDamage());
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
		g.drawRect(hb.x, hb.y, hb.width, hb.height);
	}
}