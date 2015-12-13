package com.zxads.statemachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

import com.zxads.statemachine.phase.*;
import com.zxads.util.*;
import com.zxads.util.lua.LuaGameOperationable;

import static com.zxads.util.ZxConstants.*;

public class GameOperation {

	public static GameOperation getInstance() {
		return _Instance;
	}

	IChooseMessage chooser = new ChooseMessageCui();

	public ICardList cards;

	public Square square;

	public Object temporary;

	public Square force;

	public List<Player> players;

	int turnPlayerNum;

	RootGameState gameState;

	List<IEffect> inducedEffects;

	int currentPhase;

	static GameOperation _Instance;

	public GameOperation(GameSetting gameSetting) {
		_Instance = this;

		this.players = gameSetting.getPlayers();

		this.cards = new CardList();

		this.cards.addAll(this.players.get(0).getCards());
		this.cards.addAll(this.players.get(1).getCards());

		this.square = new Square(LOCATION_SQUARE);

		this.temporary = null;
		
		this.force = new Square(LOCATION_FORCE);

		this.turnPlayerNum = 0;

		for (Card card : this.cards) {
			card.Initialize();
		}

		this.inducedEffects = new ArrayList<IEffect>();
		this.gameState = new RootGameState();

		for (int i = 0; i < 100; i++) {
			this.gameState.Operation();
		}

	}

	public IChooseMessage getChooser()
	{
		return this.chooser;
	}

	public boolean chooseYesNo(String title, String description)
	{
		return this.chooser.chooseYesNo(title, description);
	}

	public boolean chooseYesNo(String description)
	{
		return this.chooser.chooseYesNo(description);
	}

	public int chooseMessage(String choose[])
	{
		return this.chooser.chooseMessage(choose);
	}

	public int chooseMessage(String choose1, String choose2)
	{
		return this.chooser.chooseMessage(choose1, choose2);
	}

	public int chooseMessage(String title, String description, String[] choices)
	{
		return this.chooser.chooseMessage(title, description, choices);
	}

	public int chooseMessage(String description, String[] choices)
	{
		return this.chooser.chooseMessage(description, choices);
	}

	public IEffect chooseEffect(String title, String description, IEffect... effects)
	{
		return this.chooser.chooseEffect(title, description, effects);
	}

	public IEffect chooseEffect(String description, IEffect... effects)
	{
		return this.chooser.chooseEffect(description, effects);
	}

	public IEffect chooseEffect(IEffect... effects)
	{
		return this.chooser.chooseEffect(effects);
	}

	public Card chooseCard(String title, String description, Card... cards)
	{
		return this.chooser.chooseCard(title, description, cards);
	}

	public Card chooseCard(String description, Card... cards)
	{
		return this.chooser.chooseCard(description, cards);
	}

	public Card chooseCard(Card... cards)
	{
		return this.chooser.chooseCard(cards);
	}

	public int chooseSquare(Player player, final int SQUARE_STATE)
	{
		return this.chooseSquare(player,SQUARE_STATE, 0);
	}

	public int chooseSquare(Player player, final int SQUARE_STATE, final int SQUARE_LOCATION)
	{
		List<Integer> squares = this.square.filter(player, SQUARE_STATE, SQUARE_LOCATION);
		
		List<String> choices = new ArrayList<String>();
		
		for(int squareNum : squares)
		{
			String choise = "\n";
			int squareTenkey = 0;
			
			switch(squareNum)
			{
			case SQUARE_LOCATION_1:
				squareTenkey = 1;
				break;
			case SQUARE_LOCATION_2:
				squareTenkey = 2;
				break;
			case SQUARE_LOCATION_3:
				squareTenkey = 3;
				break;
			case SQUARE_LOCATION_4:
				squareTenkey = 4;
				break;
			case SQUARE_LOCATION_5:
				squareTenkey = 5;
				break;
			case SQUARE_LOCATION_6:
				squareTenkey = 6;
				break;
			case SQUARE_LOCATION_7:
				squareTenkey = 7;
				break;
			case SQUARE_LOCATION_8:
				squareTenkey = 8;
				break;
			case SQUARE_LOCATION_9:
				squareTenkey = 9;
				break;
			}
			
			for(int i = 1 ; i <= 9; i++)
			{
				if(squareTenkey == i)
				{
					choise += "[" + i + "]\t";
				}
				else
				{
					choise += " " + i + " \t";
				}
				if(i % 3 == 0)
				{
					choise += "\n";
				}
			}
			choices.add(choise);
		}
		
		return squares.get(this.chooseMessage("以下のスクエアから選んでください。", choices.toArray(new String[choices.size()])));
	}

	public void draw(Player player, int reason) {
		player.draw(reason);
	}
	
	public boolean reboot(Card card, final int REASON)
	{
		if(card.isSleep())
		{
			card.setSleep(false);
			this.raiseEvent(card, EVENT_REBOOT, REASON);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void rebootResource(Player player, final int REASON)
	{
		ICardList cards = player.getResource();
		for(Card card : cards)
		{
			this.reboot(card, REASON);
		}
	}
	
	public boolean sleep(Card card, final int REASON)
	{
		if(card.isSleep() == false)
		{
			card.setSleep(true);
			this.raiseEvent(card, EVENT_SLEEP, REASON);
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	public ICardList getCards() {
		return this.cards;
	}

	public int getCurrentPhase() {
		return this.currentPhase;
	}

	public List<IEffect> getInducedEffects() {
		return this.inducedEffects;
	}

	public Player getNonTurnPlayer() {
		if (this.turnPlayerNum == 0) {
			return this.players.get(1);
		} else {
			return this.players.get(0);
		}
	}

	public Player getOppenentPlayer(Player myself) {
		if (this.players.get(0) == myself) {
			return this.players.get(1);
		} else {
			return this.players.get(0);
		}
	}

	public Player getTurnPlayer() {
		return this.players.get(this.turnPlayerNum);
	}

	public Card putToCharge(Card card, int REASON) {
		return this.putToZone(card, card.GetPlayer().getCharge(), EVENT_TO_CHARGE, REASON);
	}

	public Card putToDeck(Card card, int REASON) {
		return this.putToZone(card, card.GetPlayer().getDeck(), EVENT_TO_DECK, REASON);
	}

	public Card putToHand(Card card, int REASON) {
		return this.putToZone(card, card.GetPlayer().getHand(), EVENT_TO_HAND, REASON);
	}

	public Card putToLife(Card card, int REASON) {
		return this.putToZone(card, card.GetPlayer().getLife(), EVENT_TO_LIFE, REASON);
	}

	public Card putToResource(Card card, int REASON) {
		return this.putToZone(card, card.GetPlayer().getResource(), EVENT_TO_RESOURCE, REASON);
	}
	public Card putToTrash(Card card, int REASON) {
		return this.putToZone(card, card.GetPlayer().getTrash(), EVENT_TO_TRASH, REASON);
	}
	public Card putToSquare(Card card,final int SQUARE_LOCATION,final int REASON) {
		ICardList zone = card.getZone();
		if(zone != null) zone.remove(card);
		Card newCard = new Card(card);
		newCard.setLocation(LOCATION_SQUARE | SQUARE_LOCATION);
		newCard.setZone(this.square);
		this.square.put(card, SQUARE_LOCATION);

		this.raiseEvent(newCard, EVENT_TO_TEMPORARY, REASON);
		return newCard;
	}
	public Card putToTemporary(Card card, int REASON) {
		ICardList zone = card.getZone();
		if(zone != null) zone.remove(card);
		Card newCard = new Card(card);
		newCard.setLocation(LOCATION_TEMPORARY);
		newCard.setZone(null);
		this.temporary = newCard;

		this.raiseEvent(newCard, EVENT_TO_TEMPORARY, REASON);
		return newCard;
	}
	public IEffect putToTemporary(IEffect effect, int REASON) {
		this.temporary = effect;
		
		this.raiseEvent(effect, EVENT_TO_TEMPORARY, REASON);
		return effect;
	}
	
	private Card putToZone(Card card, ICardList toZone,int EVENT, int REASON)
	{
		ICardList zone = card.getZone();
		if(zone != null) zone.remove(card);
		Card newCard = new Card(card);
		toZone.add(newCard);
		newCard.setLocation(toZone.getLocation());
		newCard.setZone(toZone);

		this.raiseEvent(newCard, EVENT, REASON);
		return newCard;
	}

	public void raiseEvent(int EVENT, int REASON) {
		ICardList cards = this.getCards();
		for (Card card : cards) {
			this.raiseEvent(card, EVENT, REASON);
		}
	}

	public void raiseEvent(Card card, int EVENT, int REASON) {
		List<IEffect> cardEffects = card.getEffects();

		for (IEffect effect : cardEffects) {

			this.raiseEvent(effect, EVENT, REASON);

		}
	}
	

	public void raiseEvent(IEffect effect, int EVENT, int REASON) {
		// type
		int type = effect.getType();

		if ((type | EFFECT_TYPE_TRIGGER) == type) {

			// range
			int range = effect.getRange();

			if (range == 0
					|| (effect.getCard().getLocation() | range) == range) {

				// code
				int code = effect.getCode();
				if (code == 0 || (code | EVENT) == EVENT) {

					// setResaon
					effect.getCard().setReason(REASON);

					// condition
					if (effect.checkCondition(this)) {

						// count
						int countLimit = effect.getCountLimit();

						if (countLimit == 0
								|| countLimit > effect.getCount()) {
							this.inducedEffects.add(effect);
						}
					}

					// clearReason
					effect.getCard().clearReason();

				}
			}
		}
	}


	public void setCurrentPhase(int currentPhase) {
		this.currentPhase = currentPhase;
	}

	public void toggleTurnPlayer() {
		if (this.turnPlayerNum == 0) {
			this.turnPlayerNum = 1;
		} else {
			this.turnPlayerNum = 0;
		}
	}
	
	
	public boolean useResourceCost(Player player, final int COLOR, int num, final int REASON)
	{
		return player.useResourceCost(COLOR,num,REASON);
	}
	
	
	public boolean checkResourceCost(Player player, final int COLOR)
	{
		return player.checkResourceCost(COLOR);
	}
	
	public boolean checkResourceCost(Player player, final int COLOR, int num)
	{
		return player.checkResourceCost(COLOR,num);
	}
	
	public boolean CheckPlayCard(Card card)
	{
		//zx or event
		if( card.getType() == TYPE_ZX || card.getType() == TYPE_EFFECT_ZX)
		{
			//zx
			//cost
			if(this.checkResourceCost(card.GetPlayer(), card.getColor(), card.getCost()) == false)return false;
			//found square
			for(Entry<Integer,Card> entry : this.square.entrySet())
			{
				// not op player square
				if(entry.getKey() == this.getOppenentPlayer(card.GetPlayer()).getPlayerSquare())continue;
				// empty square
				if(square == null)return true;
				// my zx square
				if(entry.getValue().GetPlayer() == card.GetPlayer())return true;
			}
			// not found square
			return false;
			
		}
		else if ( card.getType() == TYPE_EVENT)
		{
			//event
			//cost
			return this.checkResourceCost(card.GetPlayer(), card.getColor(), card.getCost());
			
		}
		else
		{
			System.err.println("Error GameOperation CheckPlayCard Unknown Type");
			return false;
		}
	}
	
	
	public boolean PlayCard(Card card)
	{
		if(this.CheckPlayCard(card))return false;
		
		//zx or event
		if( card.getType() == TYPE_ZX || card.getType() == TYPE_EFFECT_ZX)
		{
			//zx
		}
		else if ( card.getType() == TYPE_EVENT)
		{
			//event
			if(this.CheckPlayCard(card))return false;
		}
		else
		{
			System.err.println("Error GameOperation PlayCard Unknown Type");
			return false;
		}
		return true;
	}
	
	public boolean PlayEffect(IEffect effect, int REASON)
	{
		if(effect.checkCost())
		{
			this.putToTemporary( effect, REASON);
			if(effect.checkCost())
			{
				effect.cost();
				effect.operation(this);
				this.temporary = null;
				return true;
			}
			else
			{
				//TODO
				this.temporary = null;
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
}
