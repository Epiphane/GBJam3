package com.gbjam.game_components.graphics;

import com.gbjam.Entity;

public class DragonGraphicsComponent extends GraphicsComponent {
	private static final int TICKS_PER_FLAP = 14;
	private static final int TICKS_TIL_FLAP = 50;

	@Override
	public void render(Entity object) {
		// Do check here to see what state the entity is in (hurt, moving, star-power, whatever)
		super.render(object);

		if (object.generate())
			setState(1, 0);
		else {
			setState(0, 0);
			if (this.ticksSinceLastFrame >= TICKS_PER_FLAP && this.frame != 0
					|| this.ticksSinceLastFrame >= TICKS_TIL_FLAP) 
				incrementFrame();
		}
	}
	
	/**
	 * Sets animation based on general state, and direction.
	 * Preserves direction if only state is sent and direction == 0
	 */
	public void setState(int state, int frame) {
		if (this.state != state) {
			this.state = state;
			this.ticksSinceLastFrame = 0;
			this.frame = frame;
		}
	}
}
