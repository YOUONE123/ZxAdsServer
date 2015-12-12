package com.zxads.util.lua;

import java.io.PrintStream;

import com.naef.jnlua.*;

public class LuaStateEx extends LuaState{

	Class<?> valueClasses[] = {
			Integer.class
			,Boolean.class
			,String.class
			,JavaFunction.class
			,Long.class
			,Byte[].class
			,Double.class
			,Object.class};

	public void printStack(int index)
	{
		printStack(System.out,index);
	}

	public void printStack(PrintStream stream, int index)
	{
		int len = this.getTop();

		Object obj = null;
		for(Class<?> clazz : valueClasses)
		{
			if(this.isJavaObject(index, clazz))
			{
				obj = this.toJavaObject(index, clazz);
				break;
			}
		}
		if(obj != null)
		{
			stream.println(String.format("%03d", index) + "(" + String.format("%04d", -len + index - 1) + ")" + ": " + this.typeName(index) +  "\t" + obj.toString() + "\t" + obj.getClass().getName());
		}
		else
		{
			stream.println(String.format("%03d", index) + "(" + String.format("%04d", -len + index - 1) + ")" + ": " + this.typeName(index));
		}
	}

	public void printStack()
	{
		printStack(System.out);
	}
	public void printStack(PrintStream stream)
	{
		int len = this.getTop();

		if(len > 0)
		{
			for(int i = 1; i <= len; i++)
			{
				printStack(i);
			}
		}
		else
		{
			stream.println("stack size zero");
		}

	}
}
