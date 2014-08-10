package com.gbjam.game_components.generator;

import com.gbjam.Entity;
import com.gbjam.resource_mgmt.RandomizerService;

public class DragonGeneratorComponent extends GeneratorComponent {
	@Override
	public void update(Entity other) {
		super.update(other);
		
		template.setX(other.getX());
		template.setY(other.getY());
	}

	@Override
	public void generate(Entity other) {
		float y = RandomizerService.random() * 6 + 1;
		template.setY(other.getY() + y);
		
		super.generate(other);
	}
}
