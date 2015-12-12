package com.zxads.util;

import java.util.*;

public class GameSetting {

	List<Player> players;
	
	
	public GameSetting(Player player0,Player player1)
	{
		this.players = new ArrayList<Player>();
		
		this.players.add(player0);
		this.players.add(player1);
	}


	public List<Player> getPlayers() {
		return players;
	}


}
