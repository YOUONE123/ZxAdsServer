package com.zxads.statemachine;

import java.lang.reflect.*;
import java.util.*;

public class StateMachine
{
	boolean root;

	StateMachine parent;

	StateMachine currentSubState;

	HashMap<String,StateMachine> subStates;

	public StateMachine()
	{
		subStates = new HashMap<String,StateMachine>();

		root = true;

		LogEvent(this.getClass().getName() + ":EvStateEnter");
		this.EvStateEnter();
	}

	public StateMachine(Object obj)
	{
		subStates = new HashMap<String,StateMachine>();

		root = true;

		LogEvent(this.getClass().getName() + ":EvStateEnter");
		this.EvStateEnter(obj);
	}

	public StateMachine(StateMachine parent)
	{
		subStates = new HashMap<String,StateMachine>();

		this.parent = parent;

		root = false;
	}

	public void LogEvent(String msg)
	{
		System.out.println(msg);
	}

	public <T extends StateMachine> StateMachine ChangeSubState (String stateName, Class<T> stateClass)
	{
		if(!subStates.containsKey(stateName))
		{
			this.CreateSubState(stateName,stateClass);
		}

		if(currentSubState != null)
		{
			ExitCurrentSubState();
		}

		currentSubState = subStates.get(stateName);

		LogEvent(currentSubState.getClass().getName() + ":EvStateEnter");
		currentSubState.EvStateEnter();

		return currentSubState;
	}



	public <T extends StateMachine> StateMachine ChangeSubState (String stateName, Class<T> stateClass, Object obj)
	{
		if(!subStates.containsKey(stateName))
		{
			this.CreateSubState(stateName,stateClass);
		}

		if(currentSubState != null)
		{
			ExitCurrentSubState();
		}

		currentSubState = subStates.get(stateName);

		LogEvent(currentSubState.getClass().getName() + ":EvStateEnter");
		currentSubState.EvStateEnter(obj);

		return currentSubState;
	}


	public <T extends StateMachine> StateMachine ChangeState (String stateName, Class<T> stateClass) {
		if(parent == null)
		{
			parent = new StateMachine();
			parent.CreateSubState(stateName,StateMachine.class);
		}


		return parent.ChangeSubState(stateName,stateClass);
	}

	public <T extends StateMachine> StateMachine ChangeState (String stateName, Class<T> stateClass, Object obj) {
		if(parent == null)
		{
			parent = new StateMachine();
			parent.CreateSubState(stateName,StateMachine.class);
		}

		return parent.ChangeSubState(stateName,stateClass,obj);
	}



	public <T extends StateMachine> StateMachine ChangeSubState (Class<T> stateClass)
	{
		return this.ChangeSubState(stateClass.getName(), stateClass);
	}



	public <T extends StateMachine> StateMachine ChangeSubState (Class<T> stateClass, Object obj)
	{
		return this.ChangeSubState(stateClass.getName(), stateClass, obj);
	}


	public <T extends StateMachine> StateMachine ChangeState (Class<T> stateClass) {

		return this.ChangeState(stateClass.getName(),stateClass);
	}

	public <T extends StateMachine> StateMachine ChangeState (Class<T> stateClass, Object obj) {

		return this.ChangeState(stateClass.getName(),stateClass,obj);
	}

	private <T extends StateMachine> StateMachine CreateSubState(String stateName, Class<T> stateClass){

		try
		{
			StateMachine subStateMachine = stateClass.getConstructor(StateMachine.class).newInstance(this);

			subStates.put(stateName, subStateMachine);

			return subStateMachine;
		}
		catch(Exception e)
		{
			System.err.println("CreateSubStateError");
			return null;
		}

	}

	public void ExitCurrentSubState(){

		if(currentSubState != null)
		{
			currentSubState.ExitCurrentSubState();

			LogEvent(currentSubState.getClass().getName() + ":EvStateExit");
			currentSubState.EvStateExit();

			currentSubState = null;
		}
	}

	public void RemoveAllSubStates(){

		ExitCurrentSubState();

		this.subStates = null;
		this.subStates = new HashMap<String,StateMachine>();
	}

	public StateMachine GetParentStateMachine () {
		return parent;
	}

	public StateMachine GetBottomStateMachine()
	{
		StateMachine state = this;

		while(state.currentSubState != null)
		{
			state = state.currentSubState;
		}

		return state;
	}

	public StateMachine GetSubState (String stateName) {
		return subStates.get(stateName);
	}

	public StateMachine GetRootStateMachine () {

		if(this.parent == null)
		{
			return this;
		}

		StateMachine parent = this.parent;

		while(parent.parent != null)
		{
			parent = parent.parent;
		}

		return parent;
	}

	public StateMachine GetCurrentSubState () {
		return currentSubState;
	}

	// Protected methods (Override in subclasses)

	public <T> T CallFunc(String funcName, Object... args)
	{
		try
		{
			StateMachine parent = this.GetRootStateMachine();

			List<StateMachine> states = new ArrayList<StateMachine>();

			while(parent != null)
			{
				states.add(parent);
				parent = parent.GetCurrentSubState();
			}

			Collections.reverse(states);

			T result = null;

			for(StateMachine state : states)
			{
				List<Class<?>> argClases = new ArrayList<Class<?>>();

				for(Object obj : args)
				{
					argClases.add(obj.getClass());
				}

				Method method = null;

				try
				{
					method = state.getClass().getMethod(funcName, (Class<?>[])argClases.toArray(new Class<?>[argClases.size()]));

				}
				catch(java.lang.NoSuchMethodException e)
				{
					continue;
				}

				result = (T)method.invoke(state, args);

				break;

			}

			return result;
		}
		catch(Exception e)
		{
			System.err.println("CallFuncError:");
			e.printStackTrace();
			return null;
		}
	}

	protected void EvStateEnter() {
	}
	protected void EvStateEnter(Object obj) {
	}
	protected void EvStateExit() {
	}
}
