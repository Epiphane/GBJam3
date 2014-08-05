package com.gbjam.game_components;

import java.util.ArrayList;

import com.gbjam.Entity;

public class PlatformPhysicsComponent extends PhysicsComponent {
	public void update(Entity object, ArrayList<Entity> entities) {
		
	}
	
	public boolean collide(Entity object, Entity other) {
		float x[] = { object.getX(), other.getX() };
		float y[] = { object.getY(), other.getY() };
		float xw[] = { x[0] + object.getW(), x[1] + other.getW() };
		float yh[] = { y[0] + object.getH(), y[1] + other.getH() };
		
		if(xw[0] >= x[1] && x[0] <= xw[1] && yh[0] >= y[1] && y[0] <= yh[1]) {
			return true;
		}
		
		return false;
	}
}
