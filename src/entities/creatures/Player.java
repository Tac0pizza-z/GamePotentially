package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import entities.Entity;
import gfx.Assets;
import input.MouseManager;
import runGame.Game;
import runGame.Handler;
import weapon.melee.Sword;

public class Player extends Creature{
	
	private Graphics g;
	private Graphics2D g2d = (Graphics2D)g;
	//player
	private Player player;
	//mouse input
	private MouseManager mouseManager;
	//temp for equipping weapon
	Sword equippedWep = new Sword(handler, 10);
	//last attack
	private long lastAttack = 0;
	
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
		checkAttack(g2d);
	}
	
	private boolean attackAvailable(){
		long now = System.nanoTime();
		if(now - lastAttack >= equippedWep.getCooldown() * 1000000000 || lastAttack == 0){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("static-access")
	private void checkAttack(Graphics2D g2d){
		//old code
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle hb = new Rectangle();
		if(handler.getKeyManager().aUp && attackAvailable()){
			//set hb
			hb.width = equippedWep.getHbWidth();
			hb.height = equippedWep.getHbHeight();
			hb.x = cb.x + cb.width / 2 - hb.width / 2;
			hb.y = cb.y - hb.height;
		}else if(handler.getKeyManager().aDown && attackAvailable()){
			//set hb
			hb.width = equippedWep.getHbWidth();
			hb.height = equippedWep.getHbHeight();
			hb.x = cb.x + cb.width / 2 - hb.width / 2;
			hb.y = cb.y + cb.height;
		}else if(handler.getKeyManager().aLeft && attackAvailable()){
			//set hb
			hb.height = equippedWep.getHbWidth();
			hb.width = equippedWep.getHbHeight();
			//doesnt work
			hb.x = cb.x - hb.width;
			hb.y = cb.y + cb.height / 2 - hb.height / 2;
		}else if(handler.getKeyManager().aRight && attackAvailable()){
			//set hb
			hb.height = equippedWep.getHbWidth();
			hb.width = equippedWep.getHbHeight();
			//doesnt work
			hb.x = cb.x + cb.height;
			hb.y = cb.y + cb.height / 2 - hb.height / 2;
		}else{
			return;
		}
		lastAttack = System.nanoTime();
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(hb)){ //rotatedHb is a problem
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
		//g.drawRect(hb.x, hb.y, hb.width, hb.height);
	}
}