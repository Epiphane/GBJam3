package com.gbjam.game_components.physics;

import com.gbjam.Entity;
import com.gbjam.GBJam3;

public class BulletPhysicsComponent extends PhysicsComponent {
	public void update(Entity object) {
		object.setX(object.getX() + object.getDX());
		if(object.getX() + object.getW() < 0 || object.getX() > GBJam3.GAME_WIDTH) {
			object.setDead(true);
		}
	}
	
	public boolean collide(Entity object, Entity other) {
		return false;
	}
}
