package com.zxads.statemachine;

public class PriorityBeforeProcessing extends StateMachine
{

	public PriorityBeforeProcessing(StateMachine parent)
	{
		super(parent);
	}
	String event;
	
	public void EvOperation(){
		CallFunc(event);
	}

	public void EvActivateTriggerEffect(){
		ChangeSubState(ActivateTriggerEffect.class,"EvExitSubState");
	}
	

	public void EvExitSubState(){
		this.ExitCurrentSubState();
	}
	
	protected void EvStateEnter(Object obj) {
		event = (String)obj;
		
		ChangeSubState(ActivateTriggerEffect.class,"EvExitSubState");
		
		//ChangeSubState(RuleEffectCheck.class,"EvActivateTriggerEffect");
	}
	
	protected void EvStateExit() {
	}
}
