package com.gbjam;

import com.badlogic.gdx.graphics.Texture;

public class GraphicsComponent {
	private Texture texture;
	
	GraphicsComponent(Texture _texture) {
		texture = _texture;
	}
	
	public void render(GameObject object) {
		GraphicsService.draw(texture, object.getX(), object.getY());
	}
}
