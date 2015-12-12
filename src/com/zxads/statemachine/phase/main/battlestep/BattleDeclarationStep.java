package com.zxads.statemachine.phase.main.battlestep;

import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.main.battlestep.declaration.*;

public class BattleDeclarationStep extends StateMachine
{
	public BattleDeclarationStep() {
	}
	
	public BattleDeclarationStep(Object obj)
	{
		super(obj);
	}

	public BattleDeclarationStep(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(BattleEventStep.class);
	}
	protected void EvStateEnter() {
		ChangeSubState(SelectAttacker.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
