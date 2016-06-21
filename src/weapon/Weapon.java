package weapon;

import java.awt.Graphics;
import java.awt.Rectangle;

import runGame.Handler;

public abstract class Weapon {
	
	protected Handler handler;
	protected int cooldown, damage, hbWidth, hbHeight;
	protected boolean ranged;

	public Weapon(Handler handler, int hbWidth, int hbHeight, int damage, int cooldown, boolean ranged) {
		this.handler = handler;
		this.hbWidth = hbWidth;
		this.hbHeight = hbHeight;
		this.damage = damage;
		this.cooldown = cooldown;
		this.ranged = ranged;
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
}
