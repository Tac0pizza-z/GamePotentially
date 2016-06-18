package weapon.melee;

import java.awt.Graphics;
import java.awt.Rectangle;

import entities.Entity;
import entities.creatures.Player;
import runGame.Handler;

public class Sword extends MeleeWeapon{
	
	public Sword(Handler handler) {
		super(handler, 800, 400, 5, 2);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}
}
