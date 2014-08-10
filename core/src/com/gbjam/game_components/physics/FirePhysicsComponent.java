package com.gbjam.game_components.physics;

import com.gbjam.Entity;

public class FirePhysicsComponent extends BulletPhysicsComponent {

	public FirePhysicsComponent() {
		super();
	}
	
	public void update(Entity object) {
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
	}
}
