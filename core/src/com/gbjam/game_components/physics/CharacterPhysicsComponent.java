package com.gbjam.game_components.physics;

import com.gbjam.Entity;

public class CharacterPhysicsComponent extends PhysicsComponent {
	
	@Override
	public void update(Entity object) {
		// If our love's insanity, why are you my gravity?
		if (!object.getOnGround()) {
			object.setDY(object.getDY() - 0.3f);
		}
		
		if (object.getCollisionComponent().blockedLeft && object.getDX() < 0 ||
			object.getCollisionComponent().blockedRight && object.getDX() > 0)
		{
			object.setDX(0);
		}
		
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
	}
}
