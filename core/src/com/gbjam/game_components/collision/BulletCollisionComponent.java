package com.gbjam.game_components.collision;

import com.gbjam.Entity;

public class BulletCollisionComponent extends DynamicCollisionComponent {
	
	public void init(ColliderType type_) {
		super.init(type_);
		filter[ColliderType.ENEMY.ordinal()] = true;
	}
	
	protected void collideAnyDir(Entity me, Entity collider) {
		if(collider.getCollisionComponent().type == ColliderType.ENEMY)
			collider.setDead(true);
		me.setDead(true);
	}
}
