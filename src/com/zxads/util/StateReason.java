package com.zxads.util;

public class StateReason {

	public static final int NO_REASON = 0;
	
	int reason;

	int state;
	
	public StateReason(int state)
	{
		this(state,NO_REASON);
	}
	
	public StateReason(int state, int reason)
	{
		this.state = state;
		
		this.reason = reason;
	}
	
	public int getState() {
		return state;
	}

	public int getReason() {
		return reason;
	}
	
	public void AddState(int state)
	{
		this.state = this.state | state;
	}
	
	public void RemoveState(int state)
	{
		this.state = state & ~state;
	}

	//指定されたステートがあって、なおかつその理由が指定されたものであること
	public boolean IsState(int state,int reason)
	{
		return (this.state & state) == state && (this.reason & reason) == reason;
	}
	
	public boolean IsState(int state)
	{
		return IsState(state,StateReason.NO_REASON);
	}

}
