package com.gbjam.game_components.input;

import com.gbjam.Entity;
import com.gbjam.GameScreen;

public class KnightInputComponent extends AIInputComponent {
	@Override
	public void update(Entity object) {
		super.update(object);

		float dx = Math.abs(GameScreen.player.getX() - object.getX());
		float dy = Math.abs(GameScreen.player.getY() - object.getY());
		
		object.setGenerate(dy < 16 && dx < 48);
		
		if(object.generate()) {
			if(GameScreen.player.getX() > object.getX()) {
				object.setDX(0.5f);
				object.setFacingRight(true);
			}
			else {
				object.setDX(-0.5f);
				object.setFacingRight(false);
			}
		}
	}
}
