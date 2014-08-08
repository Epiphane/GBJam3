package com.gbjam.game_components.generator;

import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.game_components.graphics.GeneratorComponent;

public class WeaponGeneratorComponent extends GeneratorComponent {

	public WeaponGeneratorComponent(GameScreen gameScreen, Entity bullet, int gen) {
		super(gameScreen, bullet, gen);
	}

	public WeaponGeneratorComponent(GameScreen gameScreen, Entity bullet, int gen, int sound) {
		super(gameScreen, bullet, gen, sound);
	}

	public void update(Entity other) {
		if((other.getDX() > 0) != (template.getDX() > 0) && other.getDX() != 0) {
			template.setDX(template.getDX() * -1);
		}
		
		if(other.generate()) {
			template.setX(other.getX());
			template.setY(other.getY());
		}
		super.update(other);
	}
	
	public void generate() {
		super.generate();
	}
}