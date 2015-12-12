package com.zxads.util;

import com.naef.jnlua.*;
import com.zxads.statemachine.GameOperation;

public interface IEffect {

	public Card getCard();

	public void SetCard(Card card);

	public boolean checkCondition(GameOperation game);

	public boolean checkCost();

	public void cost();

	public boolean operation(GameOperation game);


	public int getType();
	public int getCategory();
	public int getTargetRange();
	public int getReset();
	public int getValue();
	public int getCode();
	public int getRange();
	public int getCount();
	public int getCountLimit();
	public void setCount(int num);
}
