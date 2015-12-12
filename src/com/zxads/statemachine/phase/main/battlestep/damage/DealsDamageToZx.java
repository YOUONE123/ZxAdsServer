package com.zxads.statemachine.phase.main.battlestep.damage;

import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.main.battlestep.BattleEndStep;

public class DealsDamageToZx extends StateMachine
{
	public DealsDamageToZx() {
	}
	
	public DealsDamageToZx(Object obj)
	{
		super(obj);
	}

	public DealsDamageToZx(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		this.GetParentStateMachine().ChangeState(BattleEndStep.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
