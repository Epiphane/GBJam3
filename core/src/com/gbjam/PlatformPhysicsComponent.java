package com.gbjam;

public class PlatformPhysicsComponent extends PhysicsComponent {
	public void update(Entity object) {
		
	}
	
	public boolean collide(Entity object, Entity other) {
		float x[] = { object.getX(), other.getX() };
		float y[] = { object.getY(), other.getY() };
		float xw[] = { x[0] + object.getW(), x[1] + other.getW() };
		float yh[] = { y[0] + object.getH(), y[1] + other.getH() };
		
		if(x[0] >= x[1] && x[0] <= xw[1]) {
			
		}
		
		return false;
	}
}
