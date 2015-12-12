package com.zxads.statemachine.phase.main.battlestep.end;

import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.main.battlestep.BattleEventStep;

public class BattleEliminateEffect extends StateMachine
{
	public BattleEliminateEffect() {
	}
	
	public BattleEliminateEffect(Object obj)
	{
		super(obj);
	}

	public BattleEliminateEffect(StateMachine parent)
	{
		super(parent);
	}
	
	int count;
	
	public void EvOperation(){
		count++;
		if(count > 1)
		{
			this.GetParentStateMachine().ExitCurrentSubState();
		}
		else
		{
			ChangeState(BattleTriggerEffectCheck.class,(Integer)count);
		}
	}
	protected void EvStateEnter(Object obj) {
		count = (Integer)obj;
	}
	
	protected void EvStateExit() {
	}
}
