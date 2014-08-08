package com.gbjam.game_components;

import com.gbjam.Entity;

public class CharacterPhysicsComponent extends PhysicsComponent {
	
	@Override
	public void update(Entity object) {
		// If our love's insanity, why are you my gravity?
		if (!object.getOnGround()) {
			object.setDY(object.getDY() - 0.5f);
//			System.out.println("Gravity in effect");
		}
		
//		System.out.println("Going from " + object.getY());
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
//		System.out.println(" to " + object.getY());
	}
}
