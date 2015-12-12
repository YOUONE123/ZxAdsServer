package com.zxads.statemachine.phase.main.battlestep.event;

import com.zxads.statemachine.StateMachine;

public class GivePriorityTurnPlayer extends StateMachine
{
	public GivePriorityTurnPlayer() {
	}
	
	public GivePriorityTurnPlayer(Object obj)
	{
		super(obj);
	}

	public GivePriorityTurnPlayer(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(SelectEventOrPass.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
