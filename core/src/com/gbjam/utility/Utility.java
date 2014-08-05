package com.gbjam.utility;


public class Utility {
	public static Point pointFromDir(int direction) {
		switch(direction) {
		case 0:
			return new Point(0, 1);
		case 2:
			return new Point(1, 0);
		case 4:
			return new Point(0, -1);
		case 6:
			return new Point(-1, 0);
		default:
			return new Point(0, 0);
		}
	}
}
