package com.zxads.statemachine.phase.main;

import static com.zxads.util.ZxConstants.*;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;

public class MainPhaseStart extends StateMachine {
	GameOperation game;

	public MainPhaseStart(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		this.ChangeState(BattleStep.class);
	}

	@Override
	protected void EvStateEnter() {
	}

	@Override
	protected void EvStateExit() {
	}
}