package com.gbjam.game_components.input;

import com.gbjam.Entity;

public class DragonInputComponent extends InputComponent {
	private float midY;
	/** Acceleration */
	private float ddy;
	
	public DragonInputComponent() {
		midY = 10;
	}
	
	public void update(Entity object) {
		ddy = 0.5f * (midY - object.getY());
		object.setDY(object.getDY() + ddy);
	}
}
