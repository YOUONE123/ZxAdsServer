package com.zxads.statemachine.phase.main.battlestep.damage;

import com.zxads.statemachine.StateMachine;

public class AttackerIsExistsCheck extends StateMachine
{
	public AttackerIsExistsCheck() {
	}
	
	public AttackerIsExistsCheck(Object obj)
	{
		super(obj);
	}

	public AttackerIsExistsCheck(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(DealsDamageToZx.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
