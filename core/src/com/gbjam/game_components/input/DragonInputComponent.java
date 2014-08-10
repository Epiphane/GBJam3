package com.gbjam.game_components.input;

import com.gbjam.Entity;
import com.gbjam.GameScreen;

public class DragonInputComponent extends InputComponent {
	public static final int BEGIN_REST = 100;
	public static final int ATTACK_REST = 300;
	public static final int ATTACK_TIME = 100;
	
	private int timer;
	private boolean attacking;
	private float midY;
	/** Acceleration */
	private float ddy;
	private float maxSpeed;
	
	public DragonInputComponent() {
		midY = 32;
		maxSpeed = 0.1f;;
		timer = BEGIN_REST;
	}
	
	public void update(Entity object) {
		
		ddy = 0.001f * (midY - object.getY());
		if(Math.abs(object.getDY() + ddy) < maxSpeed) {
			object.setDY(object.getDY() + ddy);
		}
		
		if(timer-- <= 0) {
			if(attacking) {
				attacking = false;
				object.setGenerate(false);
				timer = ATTACK_REST;
			}
			else {
				attacking = true;
				object.setGenerate(true);
				timer = ATTACK_TIME;
				
				if(GameScreen.player != null) {
					midY = GameScreen.player.getY() - object.getGeneratorComponent().getOffset().getY();
					maxSpeed = 0.5f;
				}
				
				if(midY < 0) {
					object.getGeneratorComponent().setTemplate(0);
				}
				else {
					object.getGeneratorComponent().setTemplate(1);
				}
				
				if(midY < 23) {
					midY = 23;
				}
			}
		}
	}
}
