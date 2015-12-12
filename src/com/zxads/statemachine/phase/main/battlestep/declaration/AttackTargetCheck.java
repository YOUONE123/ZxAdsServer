package com.zxads.statemachine.phase.main.battlestep.declaration;

import com.zxads.statemachine.StateMachine;

public class AttackTargetCheck extends StateMachine
{
	public AttackTargetCheck() {
	}
	
	public AttackTargetCheck(Object obj)
	{
		super(obj);
	}

	public AttackTargetCheck(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(SleepAttacker.class);
	}
	protected void EvStateEnter(Object obj) {
	}
	
	protected void EvStateExit() {
	}
}
