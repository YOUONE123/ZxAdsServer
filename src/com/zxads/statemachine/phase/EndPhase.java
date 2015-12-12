package com.zxads.statemachine.phase;

import static com.zxads.util.ZxConstants.EVENT_PHASE;
import static com.zxads.util.ZxConstants.EVENT_PHASE_START;
import static com.zxads.util.ZxConstants.PHASE_END;
import static com.zxads.util.ZxConstants.REASON_RULE;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.reboot.RebootPhaseStart;

public class EndPhase extends StateMachine {
	private GameOperation game;

	public EndPhase(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		GameOperation.getInstance().toggleTurnPlayer();
		System.out.println("end");
		this.ChangeState(RebootPhase.class);
	}

	@Override
	protected void EvStateEnter() {
		this.game.setCurrentPhase(PHASE_END);
		this.game.raiseEvent(EVENT_PHASE_START, REASON_RULE);
	}

	@Override
	protected void EvStateExit() {
		this.game.raiseEvent(EVENT_PHASE, REASON_RULE);
	}

}
