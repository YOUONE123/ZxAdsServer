package com.zxads.statemachine;




public class Heizen extends StateMachine
{
	public Heizen() {
	}
	
	public Heizen(Object obj)
	{
		super(obj);
	}

	public Heizen(StateMachine parent)
	{
		super(parent);
	}

	public void printKigen()
	{
		System.out.println(str);
		ChangeState(Tanoshi.class);
	}

	String str;
	public void EvOperation(){
		EvStateEnter("Heizen");
	}
	protected void EvStateEnter(Object obj) {
		str = (String)obj;
	}
	
	protected void EvStateExit() {
	}
}
