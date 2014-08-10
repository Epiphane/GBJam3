package com.gbjam.game_components.collision;

import java.util.ArrayList;

import com.gbjam.Entity;

public class PlayerCollisionComponent extends WalkingCollisionComponent {
	protected void stomped(Entity me, Entity stompedGuy) {
		if(stompedGuy.getCollisionComponent().type == ColliderType.ENEMY)
			me.setDY(5);

		super.stomped(me, stompedGuy);
	}
}
