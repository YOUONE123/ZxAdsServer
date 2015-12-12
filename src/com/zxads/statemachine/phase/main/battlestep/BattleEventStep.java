package com.zxads.statemachine.phase.main.battlestep;

import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.main.battlestep.declaration.SelectAttacker;
import com.zxads.statemachine.phase.main.battlestep.event.GivePriorityTurnPlayer;

public class BattleEventStep extends StateMachine
{
	public BattleEventStep() {
	}
	
	public BattleEventStep(Object obj)
	{
		super(obj);
	}

	public BattleEventStep(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(BattleDamageStep.class);
	}
	protected void EvStateEnter() {
		ChangeSubState(GivePriorityTurnPlayer.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
