package com.gbjam.game_components.collision;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.gbjam.Entity;
import com.gbjam.utility.Point;

public class DynamicCollisionComponent extends CollisionComponent {
	private static final float TINY_FACTOR = 0.02f;
	
	public DynamicCollisionComponent() {
	
	}

	@Override
	public void update(Entity entity, ArrayList<Entity> entities) {
		super.update(entity, entities);
		
		blockedLeft = blockedRight = false;
		
		// Check if the entity is currently inside anything
		Polygon myPolygon = entity.getPolygon();
		
		for (Entity collider : entities) {
			if (entity == collider || collider.getCollisionComponent() == null)
				continue; // Onanism is discouraged
			
			if (!filter[collider.getCollisionComponent().type.ordinal()])
				continue;
			
			if (Intersector.overlapConvexPolygons(myPolygon, collider.getPolygon())) {
				// You're inside someone.  How rude.  Find a way out
				
				// First, check if we can escape horizontally and be just fine
				entity.setX(entity.getX() - entity.getDX());
				if (Intersector.overlapConvexPolygons(entity.getPolygon(), collider.getPolygon())) {
					// No? Maybe we can escape vertically and be just fine
					entity.setX(entity.getX() + entity.getDX());
					
					entity.setY(entity.getY() - entity.getDY());
					if (Intersector.overlapConvexPolygons(entity.getPolygon(), collider.getPolygon())) {
						// We're stuck doing both at a time :(
						entity.setY(entity.getY() + entity.getDY());
						int steps = 100;
						while (steps-- > 0 && Intersector.overlapConvexPolygons(myPolygon, collider.getPolygon())) {
							entity.setX(entity.getX() - entity.getDX() * TINY_FACTOR);
							entity.setY(entity.getY() - entity.getDY() * TINY_FACTOR);
						}
					}
					else {
						// Guess the Y value was our problem!
						entity.setY(entity.getY() + entity.getDY());
						int steps = 100;
						while (steps-- > 0 && Intersector.overlapConvexPolygons(myPolygon, collider.getPolygon())) {
							entity.setY(entity.getY() - entity.getDY() * TINY_FACTOR);
						}
					}
				}
				else {
					// Guess the X value was our problem!
					entity.setX(entity.getX() + entity.getDX());
					int steps = 100;
					while (steps-- > 0 && Intersector.overlapConvexPolygons(myPolygon, collider.getPolygon())) {
						entity.setX(entity.getX() - entity.getDX() * TINY_FACTOR);
					}
				}

				// Check for unconditional collisions that don't care what direction
				// you're from (i.e. powerup, level exit, lava, etc...?)
				collideAnyDir(entity, collider);
			}
		}
		
		// We're now OUTSIDE of anyone we were colliding with.  Check just around our borders to see if
		// we should do anything about people we're gingerly touching
		
		// Check feet to see if we are stomping on someone / brushing up against them
		checkFeet(entity, entities);
	}
	
	private void checkFeet(Entity entity, ArrayList<Entity>entities) {
		float v[] = entity.getPolygon().getTransformedVertices();
		
		float smidge = 0.2f;
		
		// BOTTOM
		Point bottomRight = new Point(v[4] - smidge, v[1] - smidge);
		Point bottomLeft = new Point(v[0] + smidge, v[1] - smidge);
		
		// LEFT
		Point leftBottom = new Point(v[0] - smidge, v[1]);
		Point leftTop = new Point(v[0] - smidge, v[5]);
		
		// TOP
		Point topLeft = new Point(v[0] + smidge, v[5] + smidge);
		Point topRight = new Point(v[4] - smidge, v[5] + smidge);
		
		// RIGHT
		Point rightTop = new Point(v[4] + smidge, v[5]);
		Point rightBottom = new Point(v[4] + smidge, v[1]);
		
		for (Entity collider : entities) {
			if (entity == collider || collider.getCollisionComponent() == null)
				continue;
			
			Polygon collideGon = collider.getPolygon();
			
			// BUTT
			if (polygonContainsPoints(collideGon, bottomRight, bottomLeft)) {
				if(stomped(entity, collider))
					continue;
			}
			
			// TOP
			if (polygonContainsPoints(collideGon, topLeft, topRight)) {
				if(bangedHeadOn(entity, collider))
					continue;
			}
			
			// LEFT
			if (polygonContainsPoints(collideGon, leftBottom, leftTop)) {
				if(bumpedWithLeftSide(entity, collider))
					continue;
			}
			
			// RIGHT
			if (polygonContainsPoints(collideGon, rightTop, rightBottom)) {
				if(bumpedWithRightSide(entity, collider))
					continue;
			}
			
		}
	}

	/** Returns true if EITHER point a OR point b are in the polygon */
	private boolean polygonContainsPoints(Polygon poly, Point a, Point b) {
		return poly.contains(a.getX(), a.getY()) || poly.contains(b.getX(), b.getY());
	}
	
	
	protected boolean stomped(Entity me, Entity stompedGuy) {
		// override me!
//		System.out.println("hit butt");
		return false;
	}
	
	protected boolean bumpedWithRightSide(Entity me, Entity collider) {
		// override me!
		blockedRight = true;
//		System.out.println("hit right");
		return true;
	}

	protected boolean bangedHeadOn(Entity me, Entity collider) {
		// override me!
//		System.out.println("hit head");
		return false;
	}

	protected boolean bumpedWithLeftSide(Entity me, Entity collider) {
		// override me!
		blockedLeft = true;
//		System.out.println("head left");
		return true;
	}
	
	protected void collideAnyDir(Entity me, Entity collider) {
		// override me!
	}
}
