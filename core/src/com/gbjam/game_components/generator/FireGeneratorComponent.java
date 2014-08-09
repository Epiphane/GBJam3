package com.gbjam.game_components.generator;

import com.gbjam.Entity;

public class FireGeneratorComponent extends WeaponGeneratorComponent {

	public FireGeneratorComponent() {
		super();
	}
	
	@Override
	public void update(Entity me) {
		super.update(me);
		
		template.setDY((float) Math.random() - 0.5f);
		template.setLifespan(50);
	}

}
