package com.zxads.statemachine.phase.draw;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.PriorityBeforeProcessing;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.DrawPhase;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;

public class DrawPhasePriorityBeforeProcessing extends StateMachine
{
	GameOperation game;

	int phase = 0;

	public DrawPhasePriorityBeforeProcessing(StateMachine parent)
	{
		super(parent);
		game = GameOperation.getInstance();
	}
	
	public void EvOperation() {
		this.GetParentStateMachine().ExitCurrentSubState();
	}
	
	public void EvDrawCard()
	{
		ChangeState(DrawPhaseDrawCard.class);
	}
	

	public void EvExitCurrentSubState()
	{
		this.ExitCurrentSubState();
	}
	
	protected void EvStateEnter() {
		if(phase == 0)
		{
			phase++;
			ChangeSubState(PriorityBeforeProcessing.class,"EvDrawCard");
		}
		else if(phase == 1)
		{
			phase = 0;
			ChangeSubState(PriorityBeforeProcessing.class,"EvExitCurrentSubState");
		}
	}
	
	
	protected void EvStateExit() {
	}
}