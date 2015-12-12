package com.zxads.util;

import java.util.ArrayList;
import java.util.List;

import com.zxads.statemachine.GameOperation;


public class StateReasonList {

	List<StateReason> states;
	
	public StateReasonList()
	{
		states = new ArrayList<StateReason>();
	}
	
	public void ClearStates()
	{
		states.clear();
	}
	public void AddState(int state)
	{
		AddState(state,StateReason.NO_REASON);
	}

	public boolean IsState(int state)
	{
		return IsState(state,StateReason.NO_REASON);
	}

	public void AddState(int state, int reason)
	{
		for(StateReason stateReason : states)
		{
			if(stateReason.getReason() == reason)
			{
				stateReason.AddState(state);
				return;
			}
		}
		
		states.add(new StateReason(state,reason));
	}
	
	public void RemoveState(int state)
	{
		List<StateReason> removeList = new ArrayList<StateReason>();

		for(StateReason stateReason : states)
		{
			if(stateReason.IsState(state))
			{
				removeList.add(stateReason);
			}
		}
		
		states.removeAll(removeList);
	}
	

	public boolean IsState(int state,int reason)
	{
		for(StateReason stateReason : states)
		{
			if(stateReason.IsState(state,reason))
			{
				return true;
			}
		}
		return false;
	}
}
