package com.gbjam.utility;

public class Point {
	private float x, y;
	
	public Point(float _x, float _y) {
		x = _x;
		y = _y;
	}
	
	public Point add(Point other) {
		return new Point(x + other.x, y + other.y);
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
}
