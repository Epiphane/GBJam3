package com.gbjam.game_components.generator;

import com.gbjam.Entity;

public class WeaponGeneratorComponent extends GeneratorComponent {
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
