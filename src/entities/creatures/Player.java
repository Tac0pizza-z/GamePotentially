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
import input.KeyManager;
import input.MouseManager;
import runGame.Game;
import runGame.Handler;
import weapon.Weapon;
import weapon.melee.Sword;

public class Player extends Creature{
	
	//temp for equipping weapon
	private Weapon equippedWep = new Sword(handler);
	private long lastAttack = 0;
	private KeyManager keyManager;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	
		//player hb stuff, poorly optimized
		//try making these static to fix the whole sword thing
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
	
	private boolean attackAvailable(){
		long now = System.nanoTime();
		if(now - lastAttack >= equippedWep.getCooldown() * 1000000000 || lastAttack == 0){
			return true;
		}
		return false;
	}
	
	private void checkAttack(){
		if((keyManager.aUp || keyManager.aDown || keyManager.aLeft || keyManager.aRight) && attackAvailable())
			equippedWep.attack();
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
	}

	public Weapon getEquippedWep() {
		return equippedWep;
	}

	public void setEquippedWep(Sword equippedWep) {
		this.equippedWep = equippedWep;
	}

	public long getLastAttack() {
		return lastAttack;
	}

	public void setLastAttack(long lastAttack) {
		this.lastAttack = lastAttack;
	}
	
	
}