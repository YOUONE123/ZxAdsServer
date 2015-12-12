package com.zxads.statemachine.phase.draw;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.PriorityBeforeProcessing;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.DrawPhase;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;
import com.zxads.util.Reason;

public class DrawPhaseDrawCard extends StateMachine {
	GameOperation game;

	public DrawPhaseDrawCard(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {

		this.game.getTurnPlayer().draw(Reason.PHASE);
		this.game.getTurnPlayer().draw(Reason.PHASE);

		this.ChangeState(DrawPhasePriorityBeforeProcessing.class);
	}

	@Override
	protected void EvStateEnter() {

	}

	@Override
	protected void EvStateExit() {
	}
}