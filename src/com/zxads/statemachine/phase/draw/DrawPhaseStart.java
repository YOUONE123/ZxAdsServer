package com.zxads.statemachine.phase.draw;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.PriorityBeforeProcessing;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.DrawPhase;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;

import static com.zxads.util.ZxConstants.*;

public class DrawPhaseStart extends StateMachine {
	GameOperation game;

	public DrawPhaseStart(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		this.ChangeState(DrawPhasePriorityBeforeProcessing.class);
	}

	@Override
	protected void EvStateEnter() {
	}

	@Override
	protected void EvStateExit() {
	}
}