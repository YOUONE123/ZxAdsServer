package com.zxads.util;

import java.util.*;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.LuaValueProxy;
import com.zxads.statemachine.GameOperation;

public abstract class AbstractCardList  implements ICardList  {
	
	private int location = 0;

	private Player player;

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