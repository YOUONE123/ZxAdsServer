package com.zxads.statemachine.phase.main.battlestep.declaration;

import com.zxads.statemachine.StateMachine;

public class SelectAttackTarget extends StateMachine
{
	public SelectAttackTarget() {
	}
	
	public SelectAttackTarget(Object obj)
	{
		super(obj);
	}

	public SelectAttackTarget(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(AttackTargetCheck.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
