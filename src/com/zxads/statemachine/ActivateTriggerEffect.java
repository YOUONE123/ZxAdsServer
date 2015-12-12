package com.zxads.statemachine;

import java.util.List;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.StateMachine;
import com.zxads.util.IEffect;
import static com.zxads.util.ZxConstants.*;

public class ActivateTriggerEffect extends StateMachine {
	GameOperation game;

	String event;

	public ActivateTriggerEffect(StateMachine parent) {
		super(parent);
		this.game = GameOperation.getInstance();
	}

	public void EvOperation() {
		List<IEffect> effects = this.game.getInducedEffects();

		while (effects.size() > 0) {

			IEffect activateEffect = null;

			if (effects.size() > 1) {
				activateEffect = this.game.chooseEffect(effects
						.toArray(new IEffect[effects.size()]));
			} else {
				activateEffect = effects.get(0);
			}

			GameOperation.getInstance().PlayEffect(activateEffect, REASON_RULE);

			effects.remove(activateEffect);
		}

		this.CallFunc(this.event);
	}

	@Override
	protected void EvStateEnter(Object obj) {
		this.event = (String) obj;
	}

	@Override
	protected void EvStateExit() {
	}
}