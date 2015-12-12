package com.zxads.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChooseMessageCui implements IChooseMessage {

	@Override
	public int chooseMessage(String choose[]) {
		return this.chooseMessage("選択してください", "以下から選択", choose);
	}

	@Override
	public int chooseMessage(String choose1, String choose2) {
		return this.chooseMessage("選択してください", "以下から選択", new String[] { choose1,
				choose2 });
	}

	@Override
	public int chooseMessage(String title, String description, String[] choices) {
		try {
			InputStreamReader is = new InputStreamReader(System.in); // （１）
			BufferedReader br = new BufferedReader(is);

			int chooseNum = -1;

			while (chooseNum == -1) {
				System.out.println("----" + description + "----");
				for (int i = 0; i < choices.length; i++) {
					System.out.println(i + ":" + choices[i]);
				}
				System.out.println("-------------------------");

				String str = br.readLine();
				try {
					int num = Integer.parseInt(str);

					if (num >= 0 && num < choices.length) {
						chooseNum = num;
					} else {
						System.out.println("指定された数値じゃありません。");
					}
				} catch (NumberFormatException e) {
					System.out.println("数値じゃありません。");
				}

			}

			return chooseNum;
		} catch (Exception e) {
			System.err.println("ChooseMessageError");
			return 0;
		}
	}

	@Override
	public int chooseMessage(String description, String[] choices) {
		return this.chooseMessage("選択してください", description, choices);
	}


	@Override
	public IEffect chooseEffect(String title, String description, IEffect... effects) {
		List<String> choices = new ArrayList<String>();
		for (int i = 0; i < effects.length; i++) {
			choices.add(effects[i].getCard().getName());
		}

		int chooseNum = this.chooseMessage(title, description,
				choices.toArray(new String[choices.size()]));

		return effects[chooseNum];
	}

	@Override
	public IEffect chooseEffect(String description, IEffect... effects) {
		return this.chooseEffect("エフェクトの選択", description,effects);
	}
	@Override
	public IEffect chooseEffect(IEffect... effects) {
		return this.chooseEffect("エフェクトの選択", "以下からエフェクトを選択してください",effects);
	}


	@Override
	public Card chooseCard(String title, String description, Card... cards) {

		List<String> choices = new ArrayList<String>();
		for (int i = 0; i < cards.length; i++) {
			choices.add(cards[i].getName());
		}

		int chooseNum = this.chooseMessage(title, description,
				choices.toArray(new String[choices.size()]));

		return cards[chooseNum];
	}

	@Override
	public Card chooseCard(String description, Card... cards) {
		return this.chooseCard("カードを選択",description,cards);
	}

	@Override
	public Card chooseCard(Card... cards) {
		return this.chooseCard("カードを選択","以下からカードを選択してください。",cards);
	}

	@Override
	public boolean chooseYesNo(String title, String description) {
		return this.chooseMessage(title, description,new String[]{"YES","NO"}) == 0;
	}

	@Override
	public boolean chooseYesNo(String description) {
		return this.chooseYesNo("YesかNoで選択",description);
	}
}
