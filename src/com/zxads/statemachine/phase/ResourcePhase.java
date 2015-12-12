package com.zxads.statemachine.phase;

import static com.zxads.util.ZxConstants.EVENT_PHASE;
import static com.zxads.util.ZxConstants.EVENT_PHASE_START;
import static com.zxads.util.ZxConstants.PHASE_RESOURCE;
import static com.zxads.util.ZxConstants.REASON_RULE;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.reboot.RebootPhaseStart;
import com.zxads.statemachine.phase.resource.ResourcePriorityBeforeProcessing;

public class ResourcePhase extends StateMachine {

	private GameOperation game;

	public ResourcePhase(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		this.ChangeState(IgnitionPhase.class);
	}

	@Override
	protected void EvStateEnter() {
		this.game.setCurrentPhase(PHASE_RESOURCE);
		this.game.raiseEvent(EVENT_PHASE_START, REASON_RULE);
		this.ChangeSubState(ResourcePriorityBeforeProcessing.class);
	}

	@Override
	protected void EvStateExit() {
		this.game.raiseEvent(EVENT_PHASE, REASON_RULE);
	}
}
