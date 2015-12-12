package com.zxads.util.lua;

import com.naef.jnlua.*;

public interface LuaZxCardable {

	public LuaState getLuaState();
	public String getCardName();
	public String getCardId();
	void registerEffect(LuaEffectable effect);
}
