package com.zxads.statemachine;

import java.util.ArrayList;
import java.util.List;

import com.zxads.statemachine.phase.*;
import com.zxads.util.*;


public class RootGameState extends StateMachine
{
	public RootGameState()
	{
		super();

	}
	
	public void Operation()
	{
		CallFunc("EvOperation");
	}
	
	protected void EvStateEnter() {

		ChangeSubState(GameStartUp.class);
	}
	
	protected void EvStateExit() {
	}
}
