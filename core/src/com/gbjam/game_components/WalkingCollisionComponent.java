package com.gbjam.game_components;

import java.util.ArrayList;

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
	public void update(Entity entity, ArrayList<Entity>entities) {
		entity.setOnGround(false);
		entity.setCanJump(false);
		super.update(entity, entities);
	}

	@Override
	protected void stomped(Entity me, Entity other) {
		if (other.getCollisionComponent().type == ColliderType.PLATFORM) {
			me.setOnGround(true);
			me.setDY(0);
			
			System.out.println("Stomp'd");
		}
	}
	
	@Override
	protected void bangedHeadOn(Entity me, Entity other) {
		if (other.getCollisionComponent().type == ColliderType.PLATFORM) {
			me.setDY(0);
		}
	}
}
