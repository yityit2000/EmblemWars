package com.dvoragames.emblemwars2;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

/*
 * the input processor for gameplay (PlayScreen)
 */

public class GameInputProcessor implements InputProcessor {

	// reference to the game
	EmblemWars game;

	public GameInputProcessor(EmblemWars game) {
		this.game = game;
	}

//	@Override
//	public boolean keyDown (int keycode) {
//
//		//if escape or back key pressed go to main menu
//		if (keycode == (Keys.BACK) || keycode == (Keys.ESCAPE)){
//			game.setScreen(new MainMenuScreen(game));
//		}   
//
//		return false;
//	}

	@Override
	public boolean keyUp (int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		return false;
	}

	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
      return false;
   }

   @Override
   public boolean touchUp (int x, int y, int pointer, int button) {
	   
//	   if (EmblemWars.isSoldierIsSelected()){
//			EmblemWars.getSoldierSprite().setX(x);
//			EmblemWars.getSoldierSprite().setY(y);
//			EmblemWars.getSoldierRect().set(EmblemWars.getSoldierSprite().getX(),EmblemWars.getSoldierSprite().getY(),EmblemWars.getSoldierSprite().getWidth(),EmblemWars.getSoldierSprite().getHeight());
//			EmblemWars.setSoldierIsSelected(false);
//		}
//		
//		if (x > EmblemWars.getSoldierRect().getX() && x < (EmblemWars.getSoldierRect().getX() + EmblemWars.getSoldierRect().getWidth()) && y > EmblemWars.getSoldierRect().getY() && y < (EmblemWars.getSoldierRect().getY() + EmblemWars.getSoldierRect().getHeight())){
//			EmblemWars.setSoldierIsSelected(true);
//		}
//		System.out.println("poop");
	   
      return false;
   }

   @Override
   public boolean touchDragged (int x, int y, int pointer) {
      return false;
   }

   public boolean touchMoved (int x, int y) {
      return false;
   }

   @Override
   public boolean scrolled (int amount) {
      return false;
   }

   @Override
   public boolean mouseMoved(int screenX, int screenY) {
	   return false;
   }

@Override
public boolean keyDown(int keycode) {
	// TODO Auto-generated method stub
	return false;
}
}
