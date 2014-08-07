package com.gbjam.game_components;

import com.gbjam.Entity;
import com.gbjam.resource_mgmt.AnimationSheet;

public class PlayerGraphicsComponent extends GraphicsComponent {
	private static final int TICKS_PER_WALK = 7;
	private static final int TICKS_PER_IDLE = 14;
	
	public PlayerGraphicsComponent(AnimationSheet sheet) {
		super(sheet);
	}

	@Override
	public void render(Entity object) {
		// Do check here to see what state the entity is in (hurt, moving, star-power, whatever)
		super.render(object);

		setState(0, (int) object.getDX());
		if (object.getDX() == 0) {
			if (this.ticksSinceLastFrame >= TICKS_PER_IDLE)
				incrementFrame();
		}
		else if (object.getDX() > 0) {
			if (this.ticksSinceLastFrame >= TICKS_PER_WALK)
				incrementFrame();
		}
		else if (object.getDX() < 0) {
			if (this.ticksSinceLastFrame >= TICKS_PER_WALK) 
				incrementFrame();
		}
	}
}
