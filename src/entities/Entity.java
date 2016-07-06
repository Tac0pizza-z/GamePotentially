package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import runGame.Game;
import runGame.Handler;

public abstract class Entity {

	public static final int DEFAULT_HEALTH = 10;
	//REMINDER: Handler is static for all entities, this may cause problems later
	protected static Handler handler;
	protected float x, y;
	protected int width, height;
	protected double health;
	protected boolean active = true;
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height){
		Entity.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//make sure to change this so u can actually set health for enemies u goof
		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt(double d){
		health -= d;
		if(health <= 0 && active == true) {
			active = false;
			die();
		}
	}

	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	//getters, setters
	public double getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
