package com.gbjam.game_components.collision;

import java.util.ArrayList;

import com.gbjam.Entity;

/***
 * Defines somebody that's stuck to the floor,
 * i.e. subscribes to Gravity. 
 */
public class WalkingCollisionComponent extends DynamicCollisionComponent {
	
	@Override
	public void update(Entity entity, ArrayList<Entity>entities) {
		entity.setOnGround(false);
		entity.setCanJump(false);
		super.update(entity, entities);
	}

	@Override
	protected boolean stomped(Entity me, Entity other) {
		if (other.getCollisionComponent().type == ColliderType.PLATFORM) {
			me.setOnGround(true);
			me.setDY(0);
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean bangedHeadOn(Entity me, Entity other) {
		if (other.getCollisionComponent().type == ColliderType.PLATFORM) {
			float diff = me.getX() % 16 - 10;
			if (Math.abs(diff) < 0.5) {
				// Close enough.  Let 'em by.
				me.setX(me.getX() - diff);
				return false;
			}
			
			me.setDY(0);
			return true;
		}
		return false;
	}
}
