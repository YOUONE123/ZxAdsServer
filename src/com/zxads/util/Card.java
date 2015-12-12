package com.zxads.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.naef.jnlua.LuaState;
import com.zxads.statemachine.GameOperation;
import com.zxads.util.lua.LuaEffectable;
import com.zxads.util.lua.LuaPrintln;
import com.zxads.util.lua.LuaStateEx;
import com.zxads.util.lua.LuaZxCardable;

public class Card{
	//card datas
	int cid;
	
	public int getCid() {
		return cid;
	}

	public int getType() {
		return type;
	}

	public int getColor() {
		return color;
	}

	public int getCost() {
		return cost;
	}

	public boolean isIgn() {
		return ign;
	}

	public int getTribe() {
		return tribe;
	}

	public int getPower() {
		return power;
	}

	public String getCname() {
		return cname;
	}

	public String getText() {
		return text;
	}

	public String getFlavor_text() {
		return flavor_text;
	}

	public boolean isSleep() {
		return sleep;
	}

	public void setSleep(boolean sleep) {
		this.sleep = sleep;
	}

	int type;
	
	int color;
	
	int cost;
	
	boolean ign;
	
	int tribe;
	
	int power;
	
	String cname;
	
	String text;
	
	String flavor_text;
	
	//duel datas 1
	
	Player player;

	//duel datas 2
	
	List<IEffect> effects;

	CardList oldCards;

	boolean isOldFlg;

	boolean sleep = false;

	GameOperation game;

	LuaStateEx luaState = null;

	String scriptCode = null;

	static String constantsPath = ".\\script\\constant.lua";
	static String constantsCode = null;

	int location;

	private ICardList zone;

	private int reason;
	
	public Card(int cid) throws SQLException, NullPointerException
	{
		//set instance
		this.isOldFlg = false;
		this.effects = new ArrayList<IEffect>();
		this.oldCards = new CardList();
		
		//get db data
		ResultSet rs = SqlHelper.getInstance().getCardResultSet(cid);
		this.cid = rs.getInt("cid");
		this.type = rs.getInt("type");
		this.color = rs.getInt("color");
		this.cost = rs.getInt("cost");
		this.ign = rs.getBoolean("ign");
		this.tribe = rs.getInt("tribe");
		this.power = rs.getInt("power");
		this.cname = rs.getString("cname");
		this.text = rs.getString("text");
		this.flavor_text = rs.getString("flavor_text");

		//get constants
		if (Card.constantsCode == null) {
			try {
				File file = new File(Card.constantsPath);
				if (file.exists()) {
					Card.constantsCode = "";
					BufferedReader br = new BufferedReader(new FileReader(file));
					for (String str = br.readLine(); str != null; str = br
							.readLine()) {
						Card.constantsCode += str + "\n";
					}
				} else {
					System.out.println("not found constants file");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Card(Card card) {

		card.isOldFlg = true;


		this.cid = card.cid;
		this.type = card.type;
		this.color = card.color;
		this.cost = card.cost;
		this.ign = card.ign;
		this.tribe = card.tribe;
		this.power = card.power;
		this.cname = card.cname;
		this.text = card.text;
		this.flavor_text = card.flavor_text;

		this.zone = card.zone;
		this.player = card.player;

		this.oldCards = new CardList(card.oldCards);

		this.oldCards.add(card);

		this.effects = new ArrayList<IEffect>();

		this.Initialize();

		GameOperation.getInstance().cards.remove(card);
		GameOperation.getInstance().cards.add(this);

	}

	public void clearReason() {
		this.reason = 0;
	}

	public String getCardId() {
		return this.cname;
	}
	

	public String getFileName() {
		return "c" + this.cid;
	}

	public String getCardName() {
		return this.cname;
	}

	public List<IEffect> getEffects() {
		return this.effects;
	}

	public int getLocation() {
		return this.location;
	}

	public LuaState getLuaState() {
		return this.luaState;
	}

	public String getName() {
		return this.cname;
	}

	GameOperation GetOperation() {
		if (this.game == null) {
			this.game = GameOperation.getInstance();
		}

		return this.game;
	}

	public Player GetPlayer() {
		return this.player;
	}

	public Card getPreviousCard() {
		return this.oldCards.size() > 0 ? this.oldCards.get(this.oldCards
				.size() - 1) : null;
	}

	public int getPreviousLocation() {
		Card card = this.getPreviousCard();
		return card != null ? card.getLocation() : 0;
	}

	public int getReason() {
		return this.reason;
	}

	public ICardList getZone() {
		return this.zone;
	}

	public void Initialize() {
		
		//effect initialize
		String scriptPath = ".\\script\\c" + this.cid + ".lua";
		File file = new File(scriptPath);
		if (file.exists()) {

			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				this.luaState = new LuaStateEx();
				this.scriptCode = "c" + this.cid + " = {}\n";
				for (String str = br.readLine(); str != null; str = br
						.readLine()) {
					this.scriptCode += str + "\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.luaState.load(Card.constantsCode + "\n" + this.scriptCode,
					"=simple");
			this.luaState.call(0, 0);

			// set lua util
			this.luaState.pushJavaObject(Effect.class);
			this.luaState.setGlobal("Effect");
			this.luaState.register(new LuaPrintln(System.out));

			// Prepare a function call
			this.luaState.getGlobal("c" + this.cid); // Push the function on
														// the stack
			this.luaState.getField(-1, "initialize_effect"); // Push the
																// function on
																// the stack
			this.luaState.pushJavaObject(this); // Push argument #1

			// Call
			this.luaState.call(1, 0); // 1 arguments, 0 return

			System.out.println(".\\script\\c" + this.cid + ".luaを読み込みました。");

		}
	}

	public boolean isOld() {
		return this.isOldFlg;
	}

	public boolean isReason(int REASON) {
		return (this.reason | REASON) == this.reason;
	}

	public void registerEffect(LuaEffectable luaEffect) {
		Effect effect = (Effect) luaEffect;
		effect.SetCard(this);
		this.effects.add(effect);
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public void SetPlayer(Player player) {
		this.player = player;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public void setZone(ICardList toZone) {
		this.zone = toZone;
	}

}
