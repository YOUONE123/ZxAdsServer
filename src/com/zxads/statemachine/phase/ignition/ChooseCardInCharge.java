package com.zxads.statemachine.phase.ignition;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.PriorityBeforeProcessing;
import com.zxads.statemachine.StateMachine;
import com.zxads.statemachine.phase.DrawPhase;
import com.zxads.statemachine.phase.EndPhase;
import com.zxads.statemachine.phase.main.battlestep.*;
import com.zxads.util.Card;
import com.zxads.util.CardList;
import com.zxads.util.ICardList;

import static com.zxads.util.ZxConstants.*;

public class ChooseCardInCharge extends StateMachine
{
	GameOperation game;

	public ChooseCardInCharge(StateMachine parent)
	{
		super(parent);
		game = GameOperation.getInstance();
	}

	public void EvExitCurrentSubState()
	{
		this.ExitCurrentSubState();
	}
	
	protected void EvStateEnter() {
		ChangeSubState(PriorityBeforeProcessing.class,"EvExitCurrentSubState");
	}
	
	public void EvOperation() {
		ICardList charge = this.game.getTurnPlayer().getCharge();
		if(charge.size()>0 && this.game.chooseYesNo("IGNITIONを行いますか？"))
		{
			Card chooseCard = this.game.chooseCard(charge.toArray(new Card[charge.size()-1]));
			this.game.putToTrash(chooseCard, REASON_RULE);
			ChangeSubState(PriorityBeforeProcessing.class,"EvExitCurrentSubState");
		}
		else
		{
			this.GetParentStateMachine().ExitCurrentSubState();
		}
	}
	protected void EvStateExit() {
	}
}