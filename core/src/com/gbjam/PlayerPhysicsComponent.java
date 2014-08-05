package com.gbjam;

public class PlayerPhysicsComponent extends PhysicsComponent {
	public void update(Entity object) {
		object.setX(object.getX() + object.getDX());
		object.setY(object.getY() + object.getDY());
	}
}
