package com.zxads.statemachine.phase.main.battlestep;

import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.main.battlestep.damage.AttackerIsExistsCheck;

public class BattleDamageStep extends StateMachine
{
	public BattleDamageStep() {
	}
	
	public BattleDamageStep(Object obj)
	{
		super(obj);
	}

	public BattleDamageStep(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(BattleEndStep.class);
	}

	protected void EvStateEnter() {
		ChangeSubState(AttackerIsExistsCheck.class);
	}
	
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
