package weapon.melee;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import entities.creatures.Player;
import runGame.Handler;

public class Sword extends MeleeWeapons{

	public Sword(Handler handler, float degreeOfWep) {
		super(handler, 1, 9999, 5, 2, degreeOfWep, false);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void attack() {
		
	}
	
	
}
