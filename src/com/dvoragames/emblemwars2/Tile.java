package com.dvoragames.emblemwars2;

import java.awt.Rectangle;

public class Tile {
	
	public int x,y;
	public int width,height;
	public int moveable;
	
	public int getMoveable() {
		return moveable;
	}

	public void setMoveable(int moveable) {
		this.moveable = moveable;
	}

	// constructor for the Tile class. Right now it only has
	// an x and a y coordinate. 
	public Tile(int tileX,int tileY){
		x = tileX;
		y = tileY;
		width = 64;
		height = 64;
		moveable = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
