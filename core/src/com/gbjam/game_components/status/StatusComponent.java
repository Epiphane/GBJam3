package com.gbjam.game_components.status;

import com.gbjam.Entity;
import com.gbjam.game_components.status.AttributeComponent.AttribType;

public class StatusComponent {
	public enum StatusType {
		HURT,
		RECOIL,
		ALIVE,
		DEAD, 
		KNOCKBACK
	}
	
	public int statusTicks[];
	private int statuses[];
	
	public StatusComponent() {
		statusTicks = new int[StatusType.values().length];
		statuses = new int[StatusType.values().length];
		statusTicks[StatusType.HURT.ordinal()] = 75;
		statusTicks[StatusType.RECOIL.ordinal()] = 15;
		statusTicks[StatusType.DEAD.ordinal()] = -1;
		statusTicks[StatusType.KNOCKBACK.ordinal()] = -1;

		statusTicks[StatusType.ALIVE.ordinal()] = -1;
		statuses[StatusType.ALIVE.ordinal()] = -1;
	}
	
	public StatusComponent clone() {
		StatusComponent result = new StatusComponent();
		for(int i = 0; i < StatusType.values().length; i ++) {
			result.statusTicks[i] = statusTicks[i];
			result.statuses[i] = statuses[i];
		}
		
		return result;
	}
	
	public void update(Entity object) {
		for(int i = 0; i < statuses.length; i ++)
			if(statuses[i] > 0)
				statuses[i] --;
		
		if(statuses[StatusType.ALIVE.ordinal()] == 0) {
			statuses[StatusType.DEAD.ordinal()] = -1;
		}
		
		if(statuses[StatusType.KNOCKBACK.ordinal()] != 0 && object.getOnGround() && statuses[StatusType.KNOCKBACK.ordinal()] != statusTicks[StatusType.KNOCKBACK.ordinal()])
			statuses[StatusType.KNOCKBACK.ordinal()] = 0;
		if(statuses[StatusType.KNOCKBACK.ordinal()] == statusTicks[StatusType.KNOCKBACK.ordinal()])
			statuses[StatusType.KNOCKBACK.ordinal()] --;
	}
	
	public void setStatus(StatusType status, boolean state) {
		statuses[status.ordinal()] = state ? statusTicks[status.ordinal()] : 0;
	}
	
	public boolean is(StatusType status) {
		return statuses[status.ordinal()] != 0;
	}
}
