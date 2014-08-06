package com.gbjam.game_components;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.gbjam.Entity;
import com.gbjam.utility.Point;

public class CharacterPhysicsComponent extends PhysicsComponent {
	
	@Override
	public void update(Entity object, ArrayList<Entity> entities) {
//		object.setCanJump(bottomFoot(object, entities)); 
		// TODO: should check the result of "bottomFoot" to see what to do - i.e. if it's a platform, don't care about gravity + set canJump to true,
		// if it's an enemy, bounce off and deal damage
	}
	
	/** Are we standing on another entity? */
	private Entity bottomFoot(Entity object, ArrayList<Entity> entities) {
		Rectangle rect = object.getPolygon().getBoundingRectangle();
		
		// Check bottom left corner
		Point bottomRight = new Point(rect.x, rect.y + 0.2f);
		
		for (Entity collider : entities) {
			Polygon collideGon = collider.getPolygon();
			if (collideGon.contains(bottomRight.getX(), bottomRight.getY())) {
				return collider;
			}
		}
		
		return null;
	}
}
