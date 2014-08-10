package com.gbjam.game_components.collision;

import java.util.ArrayList;

import com.badlogic.gdx.math.Polygon;
import com.gbjam.Entity;
import com.gbjam.game_components.collision.CollisionComponent.ColliderType;
import com.gbjam.game_components.status.AttributeComponent.AttribType;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.utility.Point;

public class EnemyCollisionComponent extends WalkingCollisionComponent {
	public void init(ColliderType _type) {
		super.init(_type);
		filter[ColliderType.PLAYER.ordinal()] = true;
	}
	
	@Override
	protected void checkFeet(Entity entity, ArrayList<Entity>entities) {
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
			if (!filter[collider.getCollisionComponent().type.ordinal()])
				continue;
			
			if (entity == collider || collider.getCollisionComponent() == null)
				continue;
			
			Polygon collideGon = collider.getPolygon();
			
			// BUTT
			if (polygonContainsPoints(collideGon, bottomRight, bottomLeft)) {
				if(collider.getCollisionComponent().type == ColliderType.PLATFORM) {
					if(collideGon.contains(bottomRight.getX(), bottomRight.getY()) != collideGon.contains(bottomLeft.getX(), bottomLeft.getY())) {
						blockedRight = blockedLeft = true;
					}
				}
				
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
	
	protected boolean bumpedWithLeftSide(Entity me, Entity collider) {
		// HAH. DIE, PLAYER SCUM
		if(!collider.is(StatusType.HURT) && collider.getCollisionComponent().type == ColliderType.PLAYER) {
			collider.inflictStatus(StatusType.HURT, me.getAttribute(AttribType.ATTACK));
			collider.inflictStatus(StatusType.KNOCKBACK, -1);
		}
		return true;
	}
	
	protected boolean bumpedWithRightSide(Entity me, Entity collider) {
		// HAH. DIE, PLAYER SCUM
		if(!collider.is(StatusType.HURT) && collider.getCollisionComponent().type == ColliderType.PLAYER) {
			collider.inflictStatus(StatusType.HURT, me.getAttribute(AttribType.ATTACK));
			collider.inflictStatus(StatusType.KNOCKBACK, 1);
		}
		return true;
	}
}
