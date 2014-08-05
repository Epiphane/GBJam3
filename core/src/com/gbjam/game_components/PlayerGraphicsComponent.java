package com.gbjam.game_components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gbjam.Entity;

public class PlayerGraphicsComponent extends GraphicsComponent {

	public PlayerGraphicsComponent(TextureRegion[][] _texture) {
		super(_texture);
	}

	@Override
	public void render(Entity object) {
		if (object.getDX() == 0) {
			
		}
		else if (object.getDX() > 0) {
			
		}
		else if (object.getDX() < 0) {
			
		}
	}
}
