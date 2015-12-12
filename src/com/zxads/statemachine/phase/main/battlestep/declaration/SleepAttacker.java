package com.zxads.statemachine.phase.main.battlestep.declaration;

import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.main.battlestep.BattleEventStep;

public class SleepAttacker extends StateMachine
{
	public SleepAttacker() {
	}
	
	public SleepAttacker(Object obj)
	{
		super(obj);
	}

	public SleepAttacker(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		this.GetParentStateMachine().ChangeState(BattleEventStep.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
