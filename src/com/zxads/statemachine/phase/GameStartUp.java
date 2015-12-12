package com.zxads.statemachine.phase;

import com.zxads.statemachine.StateMachine;

public class GameStartUp extends StateMachine
{

	public GameStartUp(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(RebootPhase.class);
	}
	protected void EvStateEnter() {
	}
	
	protected void EvStateExit() {
	}
}
