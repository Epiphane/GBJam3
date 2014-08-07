package com.gbjam.game_components;

import com.gbjam.Entity;

public class CharacterPhysicsComponent extends PhysicsComponent {
	
	@Override
	public void update(Entity object) {
		// If our love's insanity, why are you my gravity?
//		object.setDY(object.getDY() - 1);
		
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
	}
}
