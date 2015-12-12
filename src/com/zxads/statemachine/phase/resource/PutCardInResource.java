package com.zxads.statemachine.phase.resource;

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

public class PutCardInResource extends StateMachine
{
	GameOperation game;

	public PutCardInResource(StateMachine parent)
	{
		super(parent);
		game = GameOperation.getInstance();
	}
	
	
	public void EvExitCurrentState()
	{
		this.GetParentStateMachine().ExitCurrentSubState();
	}
	
	public void EvOperation() {
		ICardList hand = this.game.getTurnPlayer().getHand();
		if(hand.size()>0 && this.game.chooseYesNo("カードをリソースに置きますか？"))
		{
			Card chooseCard = this.game.chooseCard(hand.toArray(new Card[hand.size()-1]));
			this.game.putToResource(chooseCard, REASON_RULE);
			ChangeSubState(PriorityBeforeProcessing.class,"EvExitCurrentState");
		}
		else
		{
			this.GetParentStateMachine().ExitCurrentSubState();
		}
	}
	
	protected void EvStateEnter() {
	}
	
	protected void EvStateExit() {
	}
}