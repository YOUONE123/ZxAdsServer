package com.zxads.util.lua;

import java.util.List;

import com.zxads.util.IChooseMessage;
import com.zxads.util.IEffect;
import com.zxads.util.Player;
import com.zxads.util.Card;

public interface LuaGameOperationable extends IChooseMessage {

	public int chooseMessage(String title, String description, String[] choices);

	public int chooseMessage(String description, String[] choices);

	public int chooseMessage(String[] choices);

	public IEffect chooseEffect(IEffect... effects);

	public void draw(Player player, int reason);

	public List<Card> getCards();

	public List<IEffect> getInducedEffects();

	public Player getNonTurnPlayer();

	public Player getOppenentPlayer(Player myself);

	public Player getTurnPlayer();

	public void toggleTurnPlayer();
}
