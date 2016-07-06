package weapon;

import java.awt.Graphics;
import java.awt.Rectangle;

import runGame.Handler;

public abstract class Weapon {
	
	protected Handler handler;
	protected int hbWidth, hbHeight;
	protected double cooldown, damage;
	protected boolean ranged;

	public Weapon(Handler handler, int hbWidth, int hbHeight, double damage, double cooldown, boolean ranged) {
		this.handler = handler;
		this.hbWidth = hbWidth;
		this.hbHeight = hbHeight;
		this.damage = damage;
		this.cooldown = cooldown;
		this.ranged = ranged;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void attack(Rectangle collBox);

	//get n set
	public double getCooldown() {
		return cooldown;
	}
	
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public double getDamage() {
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
}
