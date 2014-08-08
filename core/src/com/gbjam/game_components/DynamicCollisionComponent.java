package com.gbjam.game_components;

import java.util.ArrayList;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.gbjam.Entity;
import com.gbjam.utility.Point;

public class DynamicCollisionComponent extends CollisionComponent {
	
	public DynamicCollisionComponent(ColliderType type_) {
		super(type_);
	}
	
	@Override
	public void update(Entity entity, ArrayList<Entity> entities) {
		// Check if the entity is currently inside anything
		Polygon myPolygon = entity.getPolygon();
		
		for (Entity collider : entities) {
			if (entity == collider || collider.getCollisionComponent() == null)
				continue; // Onanism is discouraged
			
			if (!filter[collider.getCollisionComponent().type.ordinal()])
				continue;
			
			if (Intersector.overlapConvexPolygons(myPolygon, collider.getPolygon())) {
				// You're inside someone.  How rude.  Back out slowly.
				int steps = 100;
				while (steps-- > 0 && Intersector.overlapConvexPolygons(myPolygon, collider.getPolygon())) {
					entity.setX(entity.getX() - entity.getDX() * 0.05f);
					entity.setY(entity.getY() - entity.getDY() * 0.05f);
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
		Rectangle rect = entity.getPolygon().getBoundingRectangle();
		rect.setX(entity.getPolygon().getX());
		rect.setY(entity.getPolygon().getY());
		
		float smidge = 0.2f;
		
		// BOTTOM
		Point bottomRight = new Point(rect.x + rect.width, rect.y - smidge);
		Point bottomLeft = new Point(rect.x, rect.y - smidge);
		
		// LEFT
		Point leftBottom = new Point(rect.x - smidge, rect.y);
		Point leftTop = new Point(rect.x - smidge, rect.y + rect.height);
		
		// TOP
		Point topLeft = new Point(rect.x, rect.y + rect.height + smidge);
		Point topRight = new Point(rect.x + rect.width, rect.y + rect.height + smidge);
		
		// RIGHT
		Point rightTop = new Point(rect.x + rect.width + smidge, rect.y + rect.height);
		Point rightBottom = new Point(rect.x + rect.width + smidge, rect.y);
		
		for (Entity collider : entities) {
			Polygon collideGon = collider.getPolygon();
			
			// BUTT
			if (polygonContainsPoints(collideGon, bottomRight, bottomLeft)) {
				stomped(entity, collider);
			}
			
			// LEFT
			if (polygonContainsPoints(collideGon, leftBottom, leftTop)) {
				bumpedWithLeftSide(entity, collider);
			}
			
			// TOP
			if (polygonContainsPoints(collideGon, topLeft, topRight)) {
				bangedHeadOn(entity, collider);
			}
			
			// RIGHT
			if (polygonContainsPoints(collideGon, rightTop, rightBottom)) {
				bumpedWithRightSide(entity, collider);
			}
			
		}
	}

	/** Returns true if EITHER point a OR point b are in the polygon */
	private boolean polygonContainsPoints(Polygon poly, Point a, Point b) {
		return poly.contains(a.getX(), a.getY()) || poly.contains(b.getX(), b.getY());
	}
	
	
	protected void stomped(Entity me, Entity stompedGuy) {
		// override me!
	}
	
	protected void bumpedWithRightSide(Entity me, Entity collider) {
		// override me!
	}

	protected void bangedHeadOn(Entity me, Entity collider) {
		// override me!
	}

	protected void bumpedWithLeftSide(Entity me, Entity collider) {
		// override me!
	}
	
	protected void collideAnyDir(Entity me, Entity collider) {
		// override me!
	}
}
