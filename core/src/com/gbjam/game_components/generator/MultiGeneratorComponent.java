package com.gbjam.game_components.generator;

import com.gbjam.Entity;

public class MultiGeneratorComponent extends GeneratorComponent {
	protected Entity entities[];
	protected int currentTemplate;
	
	@Override
	public void setTemplate(Entity e) {
		System.out.println("Make me some templates!");
	}
	
	public void setTemplate(int i) {
		if(i < 2)
			template = entities[i];
		
		currentTemplate = i;
	}
	
	public int getTemplate() {
		return currentTemplate;
	}
}
