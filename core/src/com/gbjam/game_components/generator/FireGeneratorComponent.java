package com.gbjam.game_components.generator;

import com.gbjam.Entity;

public class FireGeneratorComponent extends WeaponGeneratorComponent {

	public FireGeneratorComponent() {
		super();
	}
	
	@Override
	public void update(Entity me) {
		super.update(me);
		
		template.setDX(me.getDX() + (me.getFacingRight() ? 1 : -1));
		template.setDY((float) Math.random() - 0.5f);
	}

	@Override
	public void generate(Entity me) {
		
		super.generate(me);
	}
}
