package com.gbjam;

public class Entity {
	private GraphicsComponent graphics;
	private PhysicsComponent physics;
	private InputComponent input;
	private float x, y, dx, dy;
	
	Entity(GraphicsComponent _graphics, PhysicsComponent _physics, InputComponent _input) {
		graphics = _graphics;
		physics = _physics;
		input = _input;
		x = y = 50;
		dx = dy = 0;
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
	public float getDX() { return dx; }
	public float getDY() { return dy; }

	public void setX(float _x) { x = _x; }
	public void setY(float _y) { y = _y; }
	public void setDX(float _dx) { dx = _dx; }
	public void setDY(float _dy) { dy = _dy; }
}
