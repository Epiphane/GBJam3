package com.gbjam.game_components.physics;

import com.gbjam.Entity;
import com.gbjam.GBJam3;
import com.gbjam.game_components.status.StatusComponent.StatusType;

public class BulletPhysicsComponent extends PhysicsComponent {
	public void update(Entity object) {
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
		//if(object.getX() + object.getPolygon().getBoundingRectangle().width < 0 || object.getX() > ) {
		//	object.setStatus(StatusType.DEAD, true);
		//}
	}
	
	public boolean collide(Entity object, Entity other) {
		return false;
	}
}
