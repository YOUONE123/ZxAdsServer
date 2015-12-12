package com.zxads.statemachine.phase.reboot;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.PriorityBeforeProcessing;
import com.zxads.statemachine.StateMachine;

import com.zxads.statemachine.phase.DrawPhase;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;

public class RebootPriorityBeforeProcessing extends StateMachine
{
	GameOperation game;

	public RebootPriorityBeforeProcessing(StateMachine parent)
	{
		super(parent);
		game = GameOperation.getInstance();
	}
	
	public void EvOperation() {
		this.GetParentStateMachine().ExitCurrentSubState();
	}
	
	public void EvExitCurrentSubState()
	{
		this.ExitCurrentSubState();
	}
	
	protected void EvStateEnter() {
		ChangeSubState(PriorityBeforeProcessing.class,"EvExitCurrentSubState");
	}
	
	protected void EvStateExit() {
	}
}