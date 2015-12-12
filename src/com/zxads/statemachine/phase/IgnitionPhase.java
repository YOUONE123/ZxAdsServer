package com.zxads.statemachine.phase;
import static com.zxads.util.ZxConstants.*;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.resource.ResourcePriorityBeforeProcessing;


public class IgnitionPhase extends StateMachine
{
	GameOperation game;
	
	public IgnitionPhase(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		this.ChangeState(MainPhase.class);
	}

	@Override
	protected void EvStateEnter() {
		this.game.setCurrentPhase(PHASE_IGNITION);
		this.game.raiseEvent(EVENT_PHASE_START, REASON_RULE);
		//this.ChangeSubState(ResourcePriorityBeforeProcessing.class);
	}

	@Override
	protected void EvStateExit() {
		this.game.raiseEvent(EVENT_PHASE, REASON_RULE);
	}
}
