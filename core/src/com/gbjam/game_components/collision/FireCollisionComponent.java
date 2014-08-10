package com.gbjam.game_components.collision;


public class FireCollisionComponent extends BulletCollisionComponent {
	public void init(ColliderType type_) {
		super.init(type_);
		
		filter[ColliderType.PLATFORM.ordinal()] = false;
	}
}
