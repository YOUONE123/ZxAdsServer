package com.zxads.util.lua;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.LuaValueProxy;
import com.zxads.statemachine.GameOperation;
import com.zxads.util.Effect;

public interface LuaEffectable {
	public void setType(int num);
	public void setCategory(int num);
	public void setRange(int num);
	public void setCode(int num);
	public void setValue(int num);
	public void setValue(LuaValueProxy lua);
	public void setCondition(LuaValueProxy lua);
	public void setOperation(LuaValueProxy lua);
	public void setReset(int num);
	public void setTarget(LuaValueProxy lua);
	public void setTargetRange(int targetRange);
	public void setCountLimit(int num);

}
