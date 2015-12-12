package com.zxads.statemachine;


public class TestMachine extends StateMachine
{
	public TestMachine() {
	}
	
	public TestMachine(Object obj)
	{
		super(obj);
	}

	public TestMachine(StateMachine parent)
	{
		super(parent);
	}

	public void EvOperation(){
		System.out.println(this.getClass().getName()  + ":EvStateEnter()");
	}
	protected void EvStateEnter(Object obj) {
		System.out.println(this.getClass().getName()  + ":EvStateEnter(obj)");
	}
	
	protected void EvStateExit() {
		System.out.println(this.getClass().getName()  + ":EvStateExit");
	}
	protected void EvStateDidChange (Object context) {
	}
}
