package com.gbjam;

public class GameObject {
	private GraphicsComponent graphics;
	private PhysicsComponent physics;
	private InputComponent input;
	private float x, y;
	
	GameObject(GraphicsComponent _graphics, PhysicsComponent _physics, InputComponent _input) {
		graphics = _graphics;
		physics = _physics;
		input = _input;
		x = y = 50;
	}
	
	public void update(float delta) {
		// Update according to input
		if(input != null)
			input.update(this);

		// Then game logic
		if(physics != null)
			physics.update(this);
		
		// Then draw
		if(graphics != null)
			graphics.render(this);
	}
	
	public float getX() { return x; }
	public float getY() { return y; }

	public void setX(float _x) { x = _x; }
	public void setY(float _y) { y = _y; }
}
