package com.gbjam;

public class GameObject {
	private GraphicsComponent graphics;
	
	GameObject(GraphicsComponent _graphics) {
		graphics = _graphics;
	}
	
	public void update(float delta) {
		if(graphics != null)
			graphics.render();
	}
}
