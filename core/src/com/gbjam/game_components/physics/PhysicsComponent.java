package com.gbjam.game_components.physics;

import com.gbjam.Entity;

public class PhysicsComponent {
	public void update(Entity object) {		
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
		
		if(object.getDX() > 0 && !object.getFacingRight()) {
			object.setFacingRight(true);
		}
		if(object.getDX() < 0 && object.getFacingRight()) {
			object.setFacingRight(false);
		}
	}
}
