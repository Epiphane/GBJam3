package com.gbjam.utility;

import java.util.Random;


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
	
	public static PointM pointMFromDir(int direction) {
		switch(direction) {
		case 0:
			return new PointM(0, 1);
		case 2:
			return new PointM(1, 0);
		case 4:
			return new PointM(0, -1);
		case 6:
			return new PointM(-1, 0);
		default:
			System.out.println("Bad direction passed into pointFromDir! was: " + direction);
			return new PointM(0, 0);
		}
	}
	
	public static int dirFromPoint(PointM point) {
		if (point.equals(new Point(0, 1))) {
			return 0;
		}
		if (point.equals(new Point(1, 0))) {
			return 2;
		}
		if (point.equals(new Point(0, -1))) {
			return 4;
		}
		if (point.equals(new Point(-1, 0))) {
			return 6;
		}
		
		return -1;
	}
	
	private static final long SEED = 69;
	private static Random gen;
	
	/** Generate random number in [min, max) */
	public static int random(int min, int max) {
		if (gen == null) {
//			gen = new Random(SEED);
			gen = new Random();
		}
		
		return gen.nextInt(max - min) + min;
	}
	
	/** Keeps an int lower than max, and wraps if it goes negative */
	public static int keepInRange(int val, int max) {
		if (val < 0)
			return max + val;
		
		return val % max;
	}
	
	public static boolean pointInBounds(PointM point, int minX, int maxX, int minY, int maxY) {
		if (point.x < minX || point.y < minY)
			return false;
		
		if (point.x >= maxX || point.y >= maxY)
			return false;
		
		return true;
	}
	
	public static void reserveSpace(boolean[][] occupiedCells, int ix, int iy) {
		// for the record,
		int height = occupiedCells[0].length; // returns the height, mmkay?
		int width = occupiedCells.length;

		for (int ndxY = iy - 2; ndxY < iy + 3; ndxY++) {
			for (int ndxX = ix - 1; ndxX < ix + 2; ndxX++) {
				if (ndxY >= 0 && ndxY < height && ndxX >= 0 && ndxX < width) {
					occupiedCells[ndxX][ndxY] = true;
				}
			}
		}
	}
	
	public static void setCoordsToWhat(boolean[][] cells, int x, int y, boolean what) {
		int height = cells[0].length;
		int width = cells.length;
		
		if (x >= 0 && x < width && y >= 0 && y < height)
			cells[x][y] = what;
	}
}
