package entities.creatures;

import java.awt.Graphics;
import gfx.Assets;
import input.KeyManager;
import runGame.Handler;
import weapon.Weapon;
import weapon.melee.Sword;

public class Player extends Creature{
	
	//temp for equipping weapon
	private Weapon equippedWep = new Sword(handler);
	private static long lastAttack = 0;
	private static int playerBoundX = 0, playerBoundY = 0;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
	
		//player hb stuff, poorly optimized
		//try making these static to fix the whole sword thing
		bounds.x = playerBoundX;
		bounds.y = playerBoundY;
		bounds.width = DEFAULT_CREATURE_WIDTH - 1;
		bounds.height = DEFAULT_CREATURE_HEIGHT - 1;
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
		if((KeyManager.aUp || KeyManager.aDown || KeyManager.aLeft || KeyManager.aRight) && attackAvailable())
			equippedWep.attack();
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

	public void setLastAttack(long newLastAttack) {
		lastAttack = newLastAttack;
	}	
}