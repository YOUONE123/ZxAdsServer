package com.zxads.server;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.naef.jnlua.*;
import com.zxads.statemachine.*;
import com.zxads.util.*;
import com.zxads.util.lua.LuaStateEx;

import static com.zxads.util.ZxConstants.*;

class Test extends StateMachine {

	public Test() {
	}

	public void EvOperation() {
		this.ChangeSubState(Okoru.class, "Okoruyoo");
		this.ChangeSubState(TestMachine.class);
		this.ChangeSubState(Heizen.class);
	}

	@Override
	protected void EvStateEnter(Object obj) {
	}

	@Override
	protected void EvStateExit() {
	}
}

public class ZxServer {

	public static void main(String args[]) throws NullPointerException, SQLException {

		CardList cards1 = new CardList(LOCATION_DECK);
		cards1.add(new Card(101002));
		cards1.add(new Card(101002));
		cards1.add(new Card(101002));
		cards1.add(new Card(101002));
		cards1.add(new Card(101002));
		cards1.add(new Card(101002));
		cards1.add(new Card(101001));
		cards1.add(new Card(101002));
		cards1.add(new Card(101002));
		cards1.add(new Card(101002));
		cards1.add(new Card(101002));

		CardList cards2 = new CardList(LOCATION_DECK);

		cards2.add(new Card(101002));
		cards2.add(new Card(101002));
		cards2.add(new Card(101002));
		cards2.add(new Card(101002));
		cards2.add(new Card(101002));
		cards2.add(new Card(101002));
		cards2.add(new Card(101002));
		cards2.add(new Card(101002));

		GameSetting setting = new GameSetting(new Player("PLAYER01",PLAYER_FIRST, cards1),
				new Player("PLAYER02",PLAYER_NONE_FIRST, cards2));

		GameOperation zx = new GameOperation(setting);
	}
}
