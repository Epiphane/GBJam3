package com.gbjam.utility;

/***
 * Mutable point ese
 */

public class PointM {
	public int x, y;
	
	public PointM(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public PointM() {
		this(0, 0);
	}
	
	/** Translates this Posture BY the coordinates of the given Posture. */
	public void addPoint(PointM adder) {
		x += adder.x;
		y += adder.y;
	}
	
	public void addPoint(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	/** Multiply both x and y by Z */
	public void mult(int z) {
		x *= z;
		y *= z;
	}
	
	/** This fixes a nasty bug. */
	public boolean equals(Object other) {
		// != if null or not a posture
		if(other == null) return false;
		if(!(other instanceof PointM)) return false;
		
		// Compare their x and y values!
		PointM otherp = (PointM) other;
		return otherp.x == x && otherp.y == y;
	}
	
	public PointM clone() {
		return new PointM(this.x, this.y);
	}
}