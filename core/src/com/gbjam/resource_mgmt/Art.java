package com.gbjam.resource_mgmt;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Art {
	public static AnimationSheet character, platform, slime, dragon;
	public static AnimationSheet fly, bullet, fire;
	
	public static Texture healthbar, health, weapons[];
	
	static public void load() throws IOException {
		character = new AnimationSheet("knight.png", 8, 8, 2, 2, 2, 2, 2, 2);
		dragon = new AnimationSheet("dragon.png", 4, 1);
		slime = new AnimationSheet("slime.png", 1, 1);
		bullet = new AnimationSheet("weapon/bullet.png", 1);
		fire = new AnimationSheet("weapon/fire.png", 1);
		platform = new AnimationSheet("platform.png", 1);
		
		fly = new AnimationSheet("fly.png", 2, 2);
		
		healthbar = new Texture(Gdx.files.internal("healthbar.png"));
		health = new Texture(Gdx.files.internal("health.png"));
		weapons = new Texture[3];
		weapons[0] = new Texture(Gdx.files.internal("weapon/ui/machine.png"));
		weapons[1] = new Texture(Gdx.files.internal("weapon/ui/fire.png"));
		weapons[2] = new Texture(Gdx.files.internal("weapon/ui/shotgun.png"));
	}

	/**
	 * Split given texture into a bunch of texture regions so we can change
	 * image based on state
	 * 
	 * @param toSplit Image laid out in a grid that will be split up
	 * @return 2D array of the texture all nicely diced up
	 */
	public static TextureRegion[][] split(String name, int xSlices, int ySlices) {
		Texture toSplit = new Texture(name);
		int width = toSplit.getWidth() / xSlices;
		int height = toSplit.getHeight() / ySlices;
		TextureRegion[][] region = new TextureRegion[xSlices][ySlices];
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				region[x][y] = new TextureRegion(toSplit, x * width, y * height, width, height);
			}
		}
		return region;
	}

	public static AnimationSheet getArt(String type) {
		// TODO: MAKE IT BETTER
		if(type.equals("player"))
			return Art.character;
		else if(type.equals("slime"))
			return Art.slime;
		else if(type.equals("bullet") || type.equals("enemyBullet"))
			return Art.bullet;
		else if(type.equals("fire") || type.equals("enemyFire"))
			return Art.fire;
		else if(type.equals("dragon"))
			return Art.dragon;
		else
			return Art.fly;
	}
}
