package com.zxads.util;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.LuaValueProxy;
import com.zxads.statemachine.GameOperation;
import com.zxads.util.lua.LuaEffectable;
import com.zxads.util.lua.LuaZxCardable;
import static com.zxads.util.ZxConstants.*;

public class Effect implements IEffect, LuaEffectable {

	/*
	 * @Override public void setOperation(LuaValueProxy lua) { this.operation =
	 * lua; }
	 * 
	 * @Override public void setCondition(LuaValueProxy lua) { this.condition =
	 * lua; }
	 */
	public static LuaEffectable createEffect() {
		return new Effect();
	}

	Card card;

	LuaValueProxy condition = null;

	LuaValueProxy operation = null;

	private int type = 0;

	private int category = 0;

	private int countLimit = 0;

	private int targetRange = 0;

	private LuaValueProxy targetFunc;

	private int reset = 0;

	private LuaValueProxy valueFunc;

	private int value = 0;

	private int code = 0;

	private int range = 0;

	int count = 0;

	private LuaValueProxy cost;

	@Override
	public Card getCard() {
		return this.card;
	}

	@Override
	public int getCategory() {
		return this.category;
	}

	@Override
	public int getCode() {
		return this.code;
	}

	public LuaValueProxy getCondition() {
		return this.condition;
	}

	@Override
	public int getCount() {
		return this.count;
	}

	@Override
	public int getCountLimit() {
		return this.countLimit;
	}

	public LuaValueProxy getOperation() {
		return this.operation;
	}

	@Override
	public int getRange() {
		return this.range;
	}

	@Override
	public int getReset() {
		return this.reset;
	}

	public LuaValueProxy getTargetFunc() {
		return this.targetFunc;
	}

	@Override
	public int getTargetRange() {
		return this.targetRange;
	}

	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	public LuaValueProxy getValueFunc() {
		return this.valueFunc;
	}

	// function Card.condition(game, player, op_player, card, effect)
	@Override
	public boolean checkCondition(GameOperation game) {
		if (this.condition == null) {
			return true;
		}
		LuaState luaState = this.condition.getLuaState();
		// Prepare a function call
		luaState.getGlobal(this.card.getFileName()); // Push the function on the
													// stack
		luaState.pushJavaObject(this.condition);
		luaState.pushJavaObject(game);
		luaState.pushJavaObject(this.card.GetPlayer());
		luaState.pushJavaObject(game.getOppenentPlayer(this.card.GetPlayer()));
		luaState.pushJavaObject(this.card);
		luaState.pushJavaObject(this);
		luaState.call(5, 1);

		if (!luaState.isBoolean(-1)) {
			System.out.println("Error:effect operation return is not boolean");
		}

		boolean result = luaState.toBoolean(-1);

		luaState.pop(2);

		return result;
	}

	// function Card.operation(game, player, op_player, card, effect)
	@Override
	public boolean operation(GameOperation game) {
		if (this.operation == null) {
			return true;
		}

		LuaState luaState = this.operation.getLuaState();
		// Prepare a function call
		luaState.getGlobal(this.card.getFileName()); // Push the function on the
													// stack
		luaState.pushJavaObject(this.operation);
		luaState.pushJavaObject(game);
		luaState.pushJavaObject(this.card.GetPlayer());
		luaState.pushJavaObject(game.getOppenentPlayer(this.card.GetPlayer()));
		luaState.pushJavaObject(this.card);
		luaState.pushJavaObject(this);
		luaState.call(5, 1);

		if (!luaState.isBoolean(-1)) {
			System.out.println("Error:effect operation return is not boolean");
		}

		boolean result = luaState.toBoolean(-1);

		luaState.pop(2);

		return result;
	}

	public void resetCount() {
		this.count = 0;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public void SetCard(Card card) {
		this.card = card;
	}

	@Override
	public void setCategory(int num) {
		this.category = num;
	}

	@Override
	public void setCode(int num) {
		this.code = num;
	}

	@Override
	public void setCondition(LuaValueProxy lua) {
		this.condition = lua;
	}

	@Override
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void setCountLimit(int num) {
		this.countLimit = num;
	}

	@Override
	public void setOperation(LuaValueProxy lua) {
		this.operation = lua;
	}

	@Override
	public void setRange(int num) {
		this.range = num;
	}

	@Override
	public void setReset(int num) {
		this.reset = num;
	}

	@Override
	public void setTarget(LuaValueProxy lua) {
		this.targetFunc = lua;
	}

	public void setTargetFunc(LuaValueProxy targetFunc) {
		this.targetFunc = targetFunc;
	}

	@Override
	public void setTargetRange(int num) {
		this.targetRange = num;
	}

	@Override
	public void setType(int num) {
		this.type = num;

	}

	@Override
	public void setValue(int num) {
		this.value = num;
	}

	@Override
	public void setValue(LuaValueProxy lua) {
		this.valueFunc = lua;
	}
	
	public void setCost(LuaValueProxy cost) {
		this.cost = cost;
	}

	@Override
	public boolean checkCost() {
		if (this.cost == null) {
		return true;
		}
		LuaState luaState = this.cost.getLuaState();
		// Prepare a function call
		luaState.getGlobal(this.card.getFileName()); // Push the function on the
		
		GameOperation game = GameOperation.getInstance();
		
		luaState.pushJavaObject(this.cost);
		luaState.pushJavaObject(game);
		luaState.pushJavaObject(this.card.GetPlayer());
		luaState.pushJavaObject(game.getOppenentPlayer(this.card.GetPlayer()));
		luaState.pushJavaObject(this.card);
		luaState.pushJavaObject(this);
		luaState.pushInteger(0);
		luaState.call(6, 1);
	
		if (!luaState.isBoolean(-1)) {
			System.out.println("Error:effect check cost return is not boolean");
		}
	
		boolean result = luaState.toBoolean(-1);
	
		luaState.pop(2);
	
		return result;
	}

	@Override
	public void cost() {
		if (this.cost == null) {
			return;
		}
		LuaState luaState = this.cost.getLuaState();
		// Prepare a function call
		luaState.getGlobal(this.card.getFileName()); // Push the function on the
		
		GameOperation game = GameOperation.getInstance();
		
		luaState.pushJavaObject(this.cost);
		luaState.pushJavaObject(game);
		luaState.pushJavaObject(this.card.GetPlayer());
		luaState.pushJavaObject(game.getOppenentPlayer(this.card.GetPlayer()));
		luaState.pushJavaObject(this.card);
		luaState.pushJavaObject(this);
		luaState.pushInteger(1);
		luaState.call(6, 0);
		luaState.pop(2);
	}
}
