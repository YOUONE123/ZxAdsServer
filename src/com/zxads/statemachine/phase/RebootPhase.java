package com.zxads.statemachine.phase;

import static com.zxads.util.ZxConstants.EVENT_PHASE;
import static com.zxads.util.ZxConstants.EVENT_PHASE_START;
import static com.zxads.util.ZxConstants.PHASE_REBOOT;
import static com.zxads.util.ZxConstants.REASON_RULE;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.main.MainPhaseStart;
import com.zxads.statemachine.phase.reboot.RebootPhaseStart;

public class RebootPhase extends StateMachine {

	private GameOperation game;

	public RebootPhase(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		this.ChangeState(DrawPhase.class);
	}

	@Override
	protected void EvStateEnter() {
		this.game.setCurrentPhase(PHASE_REBOOT);
		this.game.raiseEvent(EVENT_PHASE_START, REASON_RULE);
		this.ChangeSubState(RebootPhaseStart.class);
	}

	@Override
	protected void EvStateExit() {
		this.game.raiseEvent(EVENT_PHASE, REASON_RULE);
	}
}
