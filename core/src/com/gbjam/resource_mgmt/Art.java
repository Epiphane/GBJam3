package com.gbjam.resource_mgmt;

import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Art {
	public static TextureRegion[][] character, platform;
	public static TextureRegion[][] fly;
	
	static public void load() throws IOException {
		character = singleton("character.png");
		platform = singleton("platform.png");
		
		fly = split("fly.png", 17, 34);
	}
	
	/**
	 * Split given texture into a bunch of texture regions so we can change
	 * image based on state
	 * 
	 * @param toSplit Image laid out in a grid that will be split up
	 * @return 2D array of the texture all nicely diced up
	 */
	public static TextureRegion[][] split(String name, int width, int height) {
		Texture toSplit = new Texture(name);
		int xSlices = toSplit.getWidth() / width;
		int ySlices = toSplit.getHeight() / height;
		TextureRegion[][] region = new TextureRegion[xSlices][ySlices];
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				region[x][y] = new TextureRegion(toSplit, x * width, y * height, width, height);
			}
		}
		return region;
	}
	
	/**
	 * Sometimes you just want 1 frame.  I won't judge.
	 */
	public static TextureRegion[][] singleton(String name) {
		Texture toSplit = new Texture(name);
		TextureRegion[][] region = new TextureRegion[1][1];
		region[0][0] = new TextureRegion(toSplit, toSplit.getWidth(), toSplit.getHeight());
		return region;
	}
}
