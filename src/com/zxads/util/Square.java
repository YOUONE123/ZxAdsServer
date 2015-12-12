package com.zxads.util;
import static com.zxads.util.ZxConstants.*;

import java.util.*;
import java.util.Map.Entry;

public class Square extends AbstractCardList {
	
	Map<Integer,Card> square;
	
	public Square(final int LOCATION)
	{
		this.setLocation(LOCATION);
		square = new HashMap<Integer,Card>();
		square.put(SQUARE_LOCATION_1, null);
		square.put(SQUARE_LOCATION_2, null);
		square.put(SQUARE_LOCATION_3, null);
		square.put(SQUARE_LOCATION_4, null);
		square.put(SQUARE_LOCATION_5, null);
		square.put(SQUARE_LOCATION_6, null);
		square.put(SQUARE_LOCATION_7, null);
		square.put(SQUARE_LOCATION_8, null);
		square.put(SQUARE_LOCATION_9, null);
	}
	
	public void put(Card card, int LOCATION_SQUARE)
	{
		square.put(LOCATION_SQUARE, card);
	}
	
	public Set<Map.Entry<Integer,Card>> entrySet()
	{
		return this.square.entrySet();
	}
	
	@Override
	public void addAll(ICardList cards) {
		// TODO 自動生成されたメソッド・スタブ
		System.err.println("Square err");
	}

	@Override
	public boolean add(Card newCard) {
		// TODO 自動生成されたメソッド・スタブ
		System.err.println("Square err");
		return false;
	}

	@Override
	public int size() {
		// TODO 自動生成されたメソッド・スタブ
		return this.square.size();
	}

	@Override
	public Card get(final int SQUARE_LOCATION) {
		return this.square.get(SQUARE_LOCATION);
	}

	@Override
	public Card[] toArray() {
		List<Card> cards = new ArrayList();
		for(Card value : this.square.values())
		{
			cards.add(value);
		}
		
		return cards.toArray(new Card[cards.size()-1]);
	}

	@Override
	public Iterator<Card> iterator() {
	    return square.values().iterator();
	}

	@Override
	public boolean addAll(Collection<? extends Card> arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void clear() {
		square.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.square.values().contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.square.values().containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.square.isEmpty();
	}


	@Override
	public boolean remove(Card card) {
		return this.remove((Object)card);
	}
	
	@Override
	public boolean remove(Object arg0) {
		List<Integer> removeKey = new ArrayList<Integer>();
		for(Entry<Integer,Card> entry : this.square.entrySet())
		{
			if(entry.getValue() == arg0)
			{
				removeKey.add(entry.getKey());
			}
		}
		
		for(Integer key : removeKey)
		{
			this.square.remove(key);
			this.square.put(key, null);
		}
		
		return removeKey.size() > 0 ? true : false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		int count = 0;
		for(Card card : this)count += this.remove(card) ? 1 : 0;
		return count > 0;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		List<Card> removeList = new ArrayList<Card>();
		for(Card card : this)
		{
			if(!arg0.contains(card))
			{
				removeList.add(card);
			}
		}
		for(Card card : removeList)
		{
			this.remove(card);
		}
		return removeList.size() > 0;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.square.values().toArray(arg0);
	}
	

	public List<Integer> filter(Player player,  final int SQUARE_STATE)
	{
		return filter(player, SQUARE_STATE,0);
	}
	
	public List<Integer> filter(Player player, final int SQUARE_STATE, final int SQUARE_LOCATION)
	{
		List<Integer> squares = new ArrayList<Integer>();
		for(Entry<Integer,Card> entry : this.entrySet())
		{
			//location
			if(SQUARE_LOCATION == 0 || ( SQUARE_LOCATION & entry.getKey()) != 0)
			{

				//state
				if(entry.getValue() != null)
				{
					//SQUARE_EXIST_ZX
					if(( SQUARE_STATE & SQUARE_EXIST_ZX) != 0)
					{
						squares.add(entry.getKey());
					}
					else
					{
						//SQUARE_EXIST_FRIEND_ZX or SQUARE_EXIST_ENEMY_ZX
						if(entry.getValue().GetPlayer() == player)
						{
							if(( SQUARE_STATE & SQUARE_EXIST_FRIEND_ZX) != 0)
							{
								squares.add(entry.getKey());
							}
						}
						else
						{
							if(( SQUARE_STATE & SQUARE_EXIST_ENEMY_ZX) != 0)
							{
								squares.add(entry.getKey());
							}
						}
						
					}
				}else
				{
					//SQUARE_NOT_EXIST_ZX
					if(( SQUARE_STATE & SQUARE_NOT_EXIST_ZX) != 0)
					{
						squares.add(entry.getKey());
					}
				}
				
			}
		}
		
		return squares;
	}

}
