package com.zxads.statemachine.phase;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.draw.DrawPhaseStart;
import static com.zxads.util.ZxConstants.*;

import static com.zxads.util.ZxConstants.*;

public class DrawPhase extends StateMachine {

	private GameOperation game;

	public DrawPhase(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		this.game.chooseSquare(this.game.getTurnPlayer(), SQUARE_NOT_EXIST_ZX);
		this.ChangeState(ResourcePhase.class);
	}

	@Override
	protected void EvStateEnter() {
		this.game.setCurrentPhase(PHASE_DRAW);
		this.game.raiseEvent(EVENT_PHASE_START, REASON_RULE);
		this.ChangeSubState(DrawPhaseStart.class);
	}

	@Override
	protected void EvStateExit() {
		this.game.raiseEvent(EVENT_PHASE, REASON_RULE);
	}
}
