package com.gbjam.game_components.physics;

import com.gbjam.Entity;
import com.gbjam.GBJam3;
import com.gbjam.game_components.status.StatusComponent.StatusType;

public class FirePhysicsComponent extends BulletPhysicsComponent {

	public FirePhysicsComponent() {
		super();
	}
	
	public void update(Entity object) {
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
		if(object.getX() + object.getPolygon().getBoundingRectangle().width < 0 || object.getX() > GBJam3.GAME_WIDTH) {
			object.setStatus(StatusType.DEAD, true);
		}
		
		object.setLifespan(object.getLifespan() - 1);
		if (object.getLifespan() == 0) {
			object.setStatus(StatusType.DEAD, true);
		}
	}
}
