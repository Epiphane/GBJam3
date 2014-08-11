package com.gbjam.game_components.input;

import com.gbjam.Entity;
import com.gbjam.GameScreen;

public class HeartInputComponent extends InputComponent {
	private float midY;
	/** Acceleration */
	private float ddy;
	private float maxSpeed;
	
	public HeartInputComponent() {
		midY = 32;
		maxSpeed = 0.1f;
	}
	
	public void update(Entity object) {
		float dist = (midY - object.getY());
		ddy = 0.001f * dist;
		if(Math.abs(object.getDY() + ddy) < maxSpeed) {
			object.setDY(object.getDY() + ddy);
		}
	}
}
