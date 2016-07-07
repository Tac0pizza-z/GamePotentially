package entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import gfx.Assets;
import input.KeyManager;
import runGame.Handler;
import weapon.Weapon;
import weapon.melee.FoamSword;
import weapon.melee.Sword;

public class Player extends Creature{
	
	//temp for equipping weapon
	private Weapon equippedWep = new Sword(handler);
	private Weapon reserveWepOne = new FoamSword(handler);
	private Weapon reserveWepTwo = null;
	private Weapon reserveWepThree = null;
	private Weapon lastWepUsed;
	private long lastWepSwitch = 0;
	private static long lastAttack = 0;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	
		//player hb stuff, poorly optimized
		//try making these static to fix the whole sword thing
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = DEFAULT_CREATURE_WIDTH - 1;
		bounds.height = DEFAULT_CREATURE_HEIGHT - 1;
	}
	
	@Override
	public void tick() {
		//check for movement input
		input();
		//move if inputted
		move();
		//center on player
		handler.getGameCamera().centerOnEntity(this);
		//Attack
		checkAttack();
		//Wepon switch
		checkWepSwitch();
	}
	
	private void checkAttack(){
		if((KeyManager.aUp || KeyManager.aDown || KeyManager.aLeft || KeyManager.aRight) && attackAvailable()){
			Rectangle collBounds = getCollisionBounds(0, 0);
			equippedWep.attack(collBounds);
		}
	}
	
	private boolean attackAvailable(){
		long now = System.nanoTime();
		//potentially suboptimal
		if(lastAttack == 0 || now - lastAttack >= lastWepUsed.getCooldown() * 1000000000){
			lastWepUsed = equippedWep;
			return true;
		}
		return false;
	}
	
	private void checkWepSwitch(){
		long now = System.nanoTime();
		if(KeyManager.switchWep && (now - lastWepSwitch >= 500000000 || lastWepSwitch == 0)){
			lastWepSwitch = System.nanoTime();
			WepSwitch();
		}
	}
	
	private void WepSwitch(){
		//probs unneccessary
		//probs doing this in a rlly suboptimal way
		//checks how many weapons u have every single time
		//player will only be gaining weapons from the shop, maybe use that
		if(reserveWepOne == null){
			return;
		}else if(reserveWepTwo == null){
			Weapon switchingWepOne = equippedWep;
			Weapon switchingWepTwo = reserveWepOne;
			equippedWep = switchingWepTwo;
			reserveWepOne = switchingWepOne;
			return;
		}
		return;
	}
	
	@Override
	public void die(){
		System.out.println("Player Died");
	}
	
	private void input(){
		xMove = 0;
		yMove = 0;
		if(KeyManager.up)
			yMove = -spd;
		if(KeyManager.down)
			yMove = spd;
		if(KeyManager.left)
			xMove = -spd;
		if(KeyManager.right)
			xMove = spd;
	}
	
	public float getPlayerX() {
		return this.x;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), null);
	}

	public static void setLastAttack(long newLastAttack) {
		lastAttack = newLastAttack;
	}
	
	public static Handler getPlayerHandler() {
		return handler;
	}
}