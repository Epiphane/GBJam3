package com.gbjam.game_components;

import com.gbjam.Entity;

/***
 * Defines somebody that's stuck to the floor,
 * i.e. subscribes to Gravity. 
 */
public class WalkingCollisionComponent extends DynamicCollisionComponent {

	public WalkingCollisionComponent(ColliderType type_) {
		super(type_);
	}

	@Override
	protected void stomped(Entity me, Entity other) {
		if (other.getCollisionComponent().type == ColliderType.PLATFORM) {
			me.setOnGround(true);
		}
	}
}
