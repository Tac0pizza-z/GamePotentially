package weapon;

import java.awt.Graphics;

import runGame.Handler;

public abstract class Weapons {
	
	protected Handler handler;
	protected int cooldown, damage, hbWidth, hbHeight;
	protected boolean onCooldown;
	private float degreeOfWep;
	
	public Weapons(Handler handler, int hbWidth, int hbHeight, int damage, int cooldown, float degreeOfWep, boolean onCooldown){
		this.handler = handler;
		this.hbWidth = hbWidth;
		this.hbHeight = hbHeight;
		this.damage = damage;
		this.cooldown = cooldown;
		this.degreeOfWep = degreeOfWep;
		onCooldown = false;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void attack();
	
	//check if secondary action, reload, other things should b here
}
