package com.gbjam.game_components.status;


public class AttributeComponent {
	public enum AttribType {
		HEALTH, ATTACK
	}
	
	private int attributes[];
	
	public AttributeComponent() {
		attributes = new int[AttribType.values().length];
	}
	
	public AttributeComponent clone() {
		AttributeComponent result = new AttributeComponent();
		for(int i = 0; i < AttribType.values().length; i ++) {
			result.attributes[i] = attributes[i];
		}
		
		return result;
	}
	
	public void setAttribute(AttribType attribute, int state) {
		attributes[attribute.ordinal()] = state;
	}
	
	public int getAttribute(AttribType attribute) {
		return attributes[attribute.ordinal()];
	}
}
