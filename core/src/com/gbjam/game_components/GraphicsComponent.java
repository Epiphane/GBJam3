package com.gbjam.game_components;

import com.badlogic.gdx.graphics.Texture;
import com.gbjam.Entity;
import com.gbjam.resource_mgmt.GraphicsService;

public class GraphicsComponent {
	private Texture texture;
	
	public GraphicsComponent(Texture _texture) {
		texture = _texture;
	}
	
	public void render(Entity object) {
		GraphicsService.draw(texture, object.getX(), object.getY());
	}
}
