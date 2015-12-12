package com.zxads.statemachine.phase.main.battlestep.declaration;

import com.zxads.statemachine.StateMachine;


public class SelectAttacker extends StateMachine
{
	public SelectAttacker() {
	}
	
	public SelectAttacker(Object obj)
	{
		super(obj);
	}

	public SelectAttacker(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(SelectAttackTarget.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
