package com.gbjam.game_components.status;

import com.gbjam.Entity;

public class StatusComponent {
	public enum StatusType {
		HURT,
		RECOIL,
		DEAD
	}
	
	public int statusTicks[];
	private int statuses[];
	
	public StatusComponent() {
		statusTicks = new int[StatusType.values().length];
		statuses = new int[StatusType.values().length];
		statusTicks[StatusType.HURT.ordinal()] = 75;
		statusTicks[StatusType.RECOIL.ordinal()] = 15;
		statusTicks[StatusType.DEAD.ordinal()] = -1;
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
		
	}
	
	public void setStatus(StatusType status, boolean state) {
		statuses[status.ordinal()] = state ? statusTicks[status.ordinal()] : 0;
	}
	
	public boolean is(StatusType status) {
		return statuses[status.ordinal()] != 0;
	}

	public void tickStatus(StatusType _status) {
		if(statuses[_status.ordinal()] > 0)
			statuses[_status.ordinal()] --;
	}
}
