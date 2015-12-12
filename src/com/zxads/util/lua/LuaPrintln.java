package com.zxads.util.lua;

import java.io.PrintStream;

import com.naef.jnlua.*;

public class LuaPrintln implements NamedJavaFunction{

	PrintStream stream;

	public LuaPrintln(PrintStream stream)
	{
		this.stream = stream;
	}

	public void setPrintStream(PrintStream stream)
	{
		this.stream = stream;
	}

	@Override
	public int invoke(LuaState paramLuaState) {
		String str = paramLuaState.toString(-1);
		stream.println(str);
		return 0;
	}

	@Override
	public String getName() {
		return "println";
	}

}
