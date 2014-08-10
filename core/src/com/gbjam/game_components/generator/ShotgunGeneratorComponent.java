package com.gbjam.game_components.generator;

import com.gbjam.Entity;

public class ShotgunGeneratorComponent extends WeaponGeneratorComponent {
	
	public ShotgunGeneratorComponent() {
		super();
	}
	
	@Override
	public void generate(Entity me) {
		super.generate(me);

		// Gimme two more and randomize their DY
		Entity clone = template.clone();
		clone.setX(clone.getX() + offset.getX());
		clone.setY(clone.getY() + offset.getY());
		clone.setDY((float) (Math.random() * 2) - 1);
		world.addEntity(clone);
		
		Entity clone2 = template.clone();
		clone2.setX(clone2.getX() + offset.getX());
		clone2.setY(clone2.getY() + offset.getY());
		clone2.setDY((float) (Math.random() * 2) - 1);
		world.addEntity(clone2);
	}
}
