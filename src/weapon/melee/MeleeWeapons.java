package weapon.melee;

import runGame.Handler;
import weapon.Weapons;

public abstract class MeleeWeapons extends Weapons{

	public MeleeWeapons(Handler handler, int hbWidth, int hbHeight, int damage, int cooldown, float degreeOfWep, boolean onCooldown) {
		super(handler, hbWidth, hbHeight, damage, cooldown, degreeOfWep, onCooldown);
	}
	//also make sure onCooldown isnt redundant here and in Sword.java
	//make sure this isnt too bare

}
