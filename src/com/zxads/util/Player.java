package com.zxads.util;

import java.util.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import static com.zxads.util.ZxConstants.*;

import com.zxads.statemachine.GameOperation;
import com.zxads.statemachine.phase.ignition.ChooseCardInCharge;

public class Player {

	String name;

	ICardList cards;

	ICardList deck;

	ICardList trash;

	ICardList remove;

	ICardList charge;

	ICardList life;

	ICardList resource;

	ICardList hand;
	
	int playerNum;

	public Player(String name,int PLAYER_NUM, CardList cards) {
		this.name = name;

		this.cards = new CardList();

		this.deck = new CardList(LOCATION_DECK);
		this.deck.setPlayer(this);

		this.trash = new CardList(LOCATION_TRASH);
		this.trash.setPlayer(this);

		this.remove = new CardList(LOCATION_REMOVED);
		this.remove.setPlayer(this);

		this.charge = new CardList(LOCATION_CHARGE);
		this.charge.setPlayer(this);

		this.life = new CardList(LOCATION_LIFE);
		this.life.setPlayer(this);

		this.resource = new CardList(LOCATION_RESOURCE);
		this.resource.setPlayer(this);

		this.hand = new CardList(LOCATION_HAND);
		this.hand.setPlayer(this);

		for (Card card : cards) {
			card.SetPlayer(this);
			this.cards.add(card);
			this.deck.add(card);
			card.setZone(this.deck);
			card.setLocation(LOCATION_DECK);
		}

		for (int i = 0; i < 4; i++) {
			Card card = this.deck.get(this.deck.size() - 1);
			this.hand.add(card);
			this.deck.remove(card);
			card.setLocation(LOCATION_HAND);
			card.setZone(this.hand);
		}
		
		this.playerNum = PLAYER_NUM;
	}

	public void draw(int REASON) {
		Card card = this.deck.get(this.deck.size() - 1);
		GameOperation.getInstance().putToHand(card, REASON);
		GameOperation.getInstance().raiseEvent(EVENT_DRAW, REASON);
	}
	
	public boolean useResourceCost(final int COLOR, int num, final int REASON)
	{
		if(this.checkResourceCost(COLOR,num) == false)return false;
		
		ICardList resourceCards = new CardList(this.getResource());
		
		CardList rebootColorCards = new CardList();
		CardList rebootCards = new CardList();
		
		for(Card card : resourceCards)
		{
			if(card.isSleep() == false)
			{
				rebootCards.add(card);
				if(card.getColor() == COLOR)
				{
					rebootColorCards.add(card);
				}
			}
		}
		
		Card useColorCard = GameOperation.getInstance().chooseCard("スリープにするカードを選択してください。",rebootColorCards.toArray(new Card[rebootColorCards.size()-1]));
		GameOperation.getInstance().sleep(useColorCard, REASON);
		
		for(int i = 0; i < num - 1; i++)
		{
			Card useCard = GameOperation.getInstance().chooseCard("スリープにするカードを選択してください。",rebootColorCards.toArray(new Card[rebootColorCards.size()-1]));
			GameOperation.getInstance().sleep(useCard, REASON);
		}
		
		return true;
	}
	
	

	public boolean checkResourceCost(final int COLOR)
	{
		ICardList cards = this.getResource();
		for(Card card : cards)
		{
			if(card.getColor() == COLOR && card.isSleep() == false) return true;
		}
		return false;
	}
	
	public boolean checkResourceCost(final int COLOR, int num)
	{
		if(this.checkResourceCost(COLOR) == false)return false;
		if(this.getResource().size() > num)return true;
		else return false;
	}
	
	public int getPlayerSquare()
	{
		if(this.playerNum == PLAYER_FIRST)
		{
			return SQUARE_LOCATION_2;
		}
		else
		{
			return SQUARE_LOCATION_8;
		}
	}

	public ICardList getCards() {
		return this.cards;
	}

	public ICardList getCharge() {
		return this.charge;
	}

	public ICardList getDeck() {
		return this.deck;
	}

	public ICardList getHand() {
		return this.hand;
	}

	public ICardList getLife() {
		return this.life;
	}

	public String getName() {
		return this.name;
	}

	public ICardList getRemove() {
		return this.remove;
	}

	public ICardList getResource() {
		return this.resource;
	}

	public ICardList getTrash() {
		return this.trash;
	}

	public void setCards(CardList cards) {
		this.cards = cards;
	}

	public void setName(String name) {
		this.name = name;
	}

}
