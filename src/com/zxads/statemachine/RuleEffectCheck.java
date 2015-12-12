package com.zxads.statemachine;

import com.zxads.statemachine.phase.GameStartUp;

public class RuleEffectCheck extends StateMachine
{

	public RuleEffectCheck(StateMachine parent)
	{
		super(parent);
	}
	String event;
	
	public void EvOperation(){
		CallFunc(event);
	}
	protected void EvStateEnter(Object obj) {
		event = (String)obj;
	}
	
	protected void EvStateExit() {
	}
}
