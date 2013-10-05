package com.dvoragames.emblemwars2;

public class Unit {
	
	private static int health, attack, defence, range, xpos, ypos;
	private static boolean selected;
	private static String type;
	
	public Unit( String t, int x, int y, int h,int a,int d,int r,boolean s) {
		xpos = x;
		ypos = y;
		health = h;
		attack = a;
		defence = d;
		range = r;
		type  = t;
		selected = s;
	}

	public static boolean isSelected() {
		return selected;
	}

	public static void setSelected(boolean selected) {
		Unit.selected = selected;
	}

	public static int getXpos() {
		return xpos;
	}

	public static void setXpos(int xpos) {
		Unit.xpos = xpos;
	}

	public static int getYpos() {
		return ypos;
	}

	public static void setYpos(int ypos) {
		Unit.ypos = ypos;
	}

	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		Unit.type = type;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
}
