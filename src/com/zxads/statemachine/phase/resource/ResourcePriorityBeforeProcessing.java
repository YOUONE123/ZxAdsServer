package com.zxads.statemachine.phase.resource;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.PriorityBeforeProcessing;
import com.zxads.statemachine.StateMachine;

import com.zxads.statemachine.phase.DrawPhase;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;

public class ResourcePriorityBeforeProcessing extends StateMachine
{
	GameOperation game;

	public ResourcePriorityBeforeProcessing(StateMachine parent)
	{
		super(parent);
		game = GameOperation.getInstance();
	}
	
	public void EvOperation() {
		this.ChangeState(PutCardInResource.class);
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