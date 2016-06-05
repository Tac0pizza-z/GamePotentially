package weapon.melee;

import runGame.Handler;
import weapon.Weapons;

public abstract class MeleeWeapons extends Weapons{

	public MeleeWeapons(Handler handler, float degreeOfWep, int cooldown, int damage, int hbWidth, int hbHeight, boolean onCooldown) {
		super(handler, degreeOfWep, cooldown, damage, hbWidth, hbHeight, onCooldown);
	}
	//also make sure onCooldown isnt redundant here and in Sword.java
	//make sure this isnt too bare

}
