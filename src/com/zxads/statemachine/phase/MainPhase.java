package com.zxads.statemachine.phase;

import static com.zxads.util.ZxConstants.EVENT_PHASE;
import static com.zxads.util.ZxConstants.EVENT_PHASE_START;
import static com.zxads.util.ZxConstants.PHASE_MAIN;
import static com.zxads.util.ZxConstants.REASON_RULE;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.draw.DrawPhaseStart;
import com.zxads.statemachine.phase.main.BattleStep;
import com.zxads.statemachine.phase.main.MainPhaseStart;
import com.zxads.statemachine.phase.main.battlestep.damage.AttackerIsExistsCheck;

public class MainPhase extends StateMachine {

	private GameOperation game;

	public MainPhase(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		this.ChangeState(EndPhase.class);
	}

	@Override
	protected void EvStateEnter() {
		this.game.setCurrentPhase(PHASE_MAIN);
		this.game.raiseEvent(EVENT_PHASE_START, REASON_RULE);
		this.ChangeSubState(MainPhaseStart.class);
	}

	@Override
	protected void EvStateExit() {
		this.game.raiseEvent(EVENT_PHASE, REASON_RULE);
	}
}
