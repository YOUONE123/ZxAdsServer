package com.zxads.statemachine;


public class Tanoshi extends StateMachine
{
	public Tanoshi() {
	}
	
	public Tanoshi(Object obj)
	{
		super(obj);
	}

	public Tanoshi(StateMachine parent)
	{
		super(parent);
	}
	public void printKigen()
	{
		System.out.println(str);
		ChangeState(TestMachine.class);
	}

	String str;
	public void EvOperation(){
		EvStateEnter("Tanoshi");
	}
	protected void EvStateEnter(Object obj) {
		str = (String)obj;
	}
	protected void EvStateExit() {
	}
}
	