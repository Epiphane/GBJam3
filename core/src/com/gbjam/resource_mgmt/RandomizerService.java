package com.gbjam.resource_mgmt;

import java.util.Random;

public class RandomizerService {
	private static Random random;
	
	public static void load() {
		random = new Random();
		
		random.setSeed(42);
	}
	
	public static float random() {
		return random.nextFloat();
	}
}
