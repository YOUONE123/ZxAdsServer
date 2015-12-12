package com.zxads.statemachine.phase.main.battlestep;

import com.zxads.statemachine.StateMachine;

import com.zxads.statemachine.phase.main.battlestep.end.BattleTriggerEffectCheck;

public class BattleEndStep extends StateMachine
{
	public BattleEndStep() {
	}
	
	public BattleEndStep(Object obj)
	{
		super(obj);
	}

	public BattleEndStep(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		this.GetParentStateMachine().ExitCurrentSubState();
	}
	protected void EvStateEnter() {
		ChangeSubState(BattleTriggerEffectCheck.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
