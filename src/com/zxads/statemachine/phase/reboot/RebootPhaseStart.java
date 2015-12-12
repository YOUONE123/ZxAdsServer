package com.zxads.statemachine.phase.reboot;

import static com.zxads.util.ZxConstants.*;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.PriorityBeforeProcessing;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.DrawPhase;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;

public class RebootPhaseStart extends StateMachine {
	int turn = 0;

	GameOperation game;

	public RebootPhaseStart(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		this.ChangeState(RebootPriorityBeforeProcessing.class);
	}

	@Override
	protected void EvStateEnter() {
		this.turn++;
	}

	@Override
	protected void EvStateExit() {
	}
}