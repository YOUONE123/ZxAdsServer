package com.zxads.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public interface IChooseMessage {
	
	public static boolean CHOOSE_YES = true;
	public static boolean CHOOSE_NO = false;

	public boolean chooseYesNo(String title, String description);

	public boolean chooseYesNo(String description);

	public int chooseMessage(String choose[]);

	public int chooseMessage(String choose1, String choose2);

	public int chooseMessage(String title, String description, String[] choices);

	public int chooseMessage(String description, String[] choices);

	public IEffect chooseEffect(String title, String description, IEffect... effects);

	public IEffect chooseEffect(String description, IEffect... effects);

	public IEffect chooseEffect(IEffect... effects);

	public Card chooseCard(String title, String description, Card... cards);

	public Card chooseCard(String description, Card... cards);
	
	public Card chooseCard(Card... cards);

}
