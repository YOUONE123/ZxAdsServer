package com.zxads.util;

import java.util.*;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.LuaValueProxy;
import com.zxads.statemachine.GameOperation;

public class CardList  implements ICardList  {
	
	ArrayList<Card> list;
	
	private int location = 0;

	private Player player;
	
	public CardList(Collection<? extends Card> cards,int location)
	{
		this.list = new ArrayList<Card>();
		for(Card card : cards)this.list.add(card);
		this.location = location;
	}

	public CardList(Collection<? extends Card> cards)
	{
		this(cards,0);
	}

	public CardList(int location)
	{
		this(new ArrayList<Card>(),location);
	}

	public CardList()
	{
		this(0);
	}


	public void setPlayer(Player player)
	{
		this.player  = player;
	}

	public Player getPlayer()
	{
		return this.player;
	}



	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}


	public Card getTop()
	{
		return this.get(this.list.size()-1);
	}


	@Override
	public Card get(int i) {
		return this.list.get(i);
	}

	public CardList filter(LuaValueProxy func)
	{
		LuaState luaState = func.getLuaState();
		// Prepare a function call
		//luaState.getGlobal(this.card.getFileName()); // Push the function on the
		
		CardList filterList = new CardList();
		
		int size = this.size();
		
		for(int i = 0; i < size; i++)
		{
			luaState.pushJavaObject(func);
			luaState.pushJavaObject(this.get(i));
			luaState.call(1, 1);
			if (!luaState.isBoolean(-1)) {
				System.out.println("Error:list filter return is not boolean");
			}
			boolean result = luaState.toBoolean(-1);
			if(result)
			{
				filterList.add(this.list.get(i));
			}
			luaState.pop(2);
		}
		return filterList;
	}

	@Override
	public boolean add(Card card) {
		return this.list.add(card);
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public Iterator<Card> iterator() {
		return this.list.iterator();
	}

	@Override
	public boolean remove(Card card) {
		return this.list.remove(card);
	}

	@Override
	public Card[] toArray() {
		return this.list.toArray(new Card[this.list.size()-1]);
	}

	@Override
	public boolean addAll(Collection<? extends Card> arg0) {
		return this.list.addAll(arg0);
	}

	@Override
	public void clear() {
		this.list.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return this.list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.list.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean remove(Object arg0) {
		return this.list.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.list.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.list.retainAll(arg0);
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.list.toArray(arg0);
	}

	@Override
	public void addAll(ICardList cards) {
		this.list.addAll(cards);
	}

	/*


	AddCard	( pgroup, pcard ) -> nil
	CheckWithSumEqual	( pgroup, function, acc, min, max, args... ) -> nil
	CheckWithSumGreater	( pgroup, function, acc, args... ) -> bool
	Clear	( pgroup ) -> nil
	Clone	( pgroup ) -> Group
	CreateGroup	( ) -> Group
	DeleteGroup	( pgroup ) -> nil
	Equal	( pgroup, sgroup ) -> bool
	Filter	( pgroup, function, pexception, args... ) -> Group
	(tp,c69846323.cfilter,1,1,e:GetHandler())
	FilterCount	( pgroup, function, pexception, args... ) -> int
	FilterSelect	( pgroup, playerid, function, min, max, pexception, args... ) -> unknown
	ForEach	( pgroup, function ) -> nil
	FromCards	( cards... ) -> Group
	GetClassCount	( pgroup, function, args... ) -> int
	GetCount	( pgroup ) -> int
	GetFirst	( pgroup ) -> Card
	GetMaxGroup	( pgroup, function, args... ) -> [Group, int]
	GetMinGroup	( pgroup, function, args... ) -> [Group, int]
	GetNext	( pgroup ) -> Card
	GetSum	( pgroup, function, args... ) -> int
	IsContains	( pgroup, pcard ) -> bool
	IsExists	( pgroup, function, count, pcard, args... ) -> bool
	KeepAlive	( pgroup ) -> nil
	Merge	( pgroup, mgroup ) -> nil
	RandomSelect	( pgroup, playerid, count ) -> unknown
	Remove	( pgroup, function, pexception, args... ) -> nil
	RemoveCard	( pgroup, pcard ) -> nil
	SearchCard	( pgroup, function, args... ) -> Card
	Select	( pgroup, playerid, min, max, pexception ) -> unknown
	SelectWithSumEqual	( pgroup, playerid, function, acc, min, max, args... ) -> unknown
	SelectWithSumGreater	( pgroup, playerid, function, acc, args... ) -> unknown
	Sub	( pgroup, sgroup ) -> nil*/
}