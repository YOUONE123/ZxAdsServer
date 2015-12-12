package com.zxads.statemachine.phase.main.battlestep.event;

import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.main.battlestep.BattleDamageStep;


public class SelectEventOrPass extends StateMachine
{
	public SelectEventOrPass() {
	}
	
	public SelectEventOrPass(Object obj)
	{
		super(obj);
	}

	public SelectEventOrPass(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		this.GetParentStateMachine().ChangeState(BattleDamageStep.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
