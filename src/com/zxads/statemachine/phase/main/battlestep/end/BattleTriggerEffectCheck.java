package com.zxads.statemachine.phase.main.battlestep.end;

import com.zxads.statemachine.StateMachine;

public class BattleTriggerEffectCheck extends StateMachine
{
	public BattleTriggerEffectCheck() {
	}
	
	public BattleTriggerEffectCheck(Object obj)
	{
		super(obj);
	}

	public BattleTriggerEffectCheck(StateMachine parent)
	{
		super(parent);
	}
	
	public void EvOperation(){
		ChangeState(BattleEliminateEffect.class,(Integer)count);
	}
	
	int count = 0;
	
	public void EvOperation(int count){
	}

	protected void EvStateEnter() {
		count = 0;
	}
	
	protected void EvStateEnter(Object obj) {
		count = (Integer)obj;
	}
	
	protected void EvStateExit() {
	}
}
