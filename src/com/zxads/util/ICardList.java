package com.zxads.util;

import java.util.Collection;


public interface ICardList extends Collection<Card> {


	public void setPlayer(Player player);

	public Player getPlayer();

	public int getLocation();

	public void setLocation(int location);
	
	public void addAll(ICardList cards);

	public boolean remove(Card card);

	public Card get(int i);
}
