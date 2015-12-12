package com.zxads.statemachine.phase.main;

import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;

public class BattleStep extends StateMachine
{
	public BattleStep() {
	}
	
	public BattleStep(Object obj)
	{
		super(obj);
	}

	public BattleStep(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation() {
		this.GetParentStateMachine().ChangeState(EndPhase.class);
	}
	
	protected void EvStateEnter() {
		ChangeSubState(BattleDeclarationStep.class);
	}
	
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}