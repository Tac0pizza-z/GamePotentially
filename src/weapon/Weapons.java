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

	//get n set

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHbWidth() {
		return hbWidth;
	}

	public void setHbWidth(int hbWidth) {
		this.hbWidth = hbWidth;
	}

	public int getHbHeight() {
		return hbHeight;
	}

	public void setHbHeight(int hbHeight) {
		this.hbHeight = hbHeight;
	}

	public boolean isOnCooldown() {
		return onCooldown;
	}

	public void setOnCooldown(boolean onCooldown) {
		this.onCooldown = onCooldown;
	}

	public float getDegreeOfWep() {
		return degreeOfWep;
	}

	public void setDegreeOfWep(float degreeOfWep) {
		this.degreeOfWep = degreeOfWep;
	}
	
}
