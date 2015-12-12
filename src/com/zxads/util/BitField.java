package com.zxads.util;

public class BitField implements IBitField {

	int timings;
	
	public void AddTiming(int timing)
	{
		timings = timings | timing;
	}
	
	public void RemoveTiming(int timing)
	{
		timings = timings & ~timing;
	}
	
	public boolean IsTiming(int timing)
	{
		return (timings & timing) == timing;
	}

}
