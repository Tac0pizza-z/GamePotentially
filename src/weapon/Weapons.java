package weapon;

import java.awt.Graphics;

import runGame.Handler;

public abstract class Weapons {
	
	protected Handler handler;
	protected float degreeHeld;
	protected int cooldown, damage, hbWidth, hbHeight;
	protected boolean onCooldown;
	
	public Weapons(Handler handler, float degreeOfWep, int cooldown, int damage, int hbWidth, int hbHeight, boolean onCooldown){
		this.handler = handler;
		this.degreeHeld = degreeHeld;
		this.cooldown = cooldown;
		this.damage = damage;
		onCooldown = false;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void attack();
	
	//check if secondary action, reload, other things should b here
}
