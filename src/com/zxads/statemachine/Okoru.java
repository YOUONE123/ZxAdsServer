package com.zxads.statemachine;




public class Okoru extends StateMachine
{

	public Okoru() {
	}
	

	
	public Okoru(Object obj)
	{
		super(obj);
	}

	public Okoru(StateMachine parent)
	{
		super(parent);
	}

	public void printKigen()
	{
		System.out.println(str);
		ChangeState(Heizen.class);
	}

	String str = "";
	public void EvOperation(){
		if(str=="")
		{
			EvStateEnter("Okoru");
		}
		
	}
	protected void EvStateEnter(Object obj) {
		str = (String)obj;
	}
	protected void EvStateExit() {
	}
}
