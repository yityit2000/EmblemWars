package com.dvoragames.emblemwars2;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animations implements ApplicationListener{
	private static final int        FRAME_COLS = 20;         
    private static final int        FRAME_ROWS = 1; 
    
    static Animation               moveTileAnimation;          
    Texture                         moveTileSheet;  
	TextureRegion[]                 moveTileFrames;             
    static TextureRegion           moveTileCurrentFrame;  
    
    float stateTime;  
    
    public void create(){
		moveTileSheet = new Texture(
				Gdx.files.internal("data/MovementTileSpritesheet.png"));
		TextureRegion[][] tmp = TextureRegion.split(moveTileSheet,
				moveTileSheet.getWidth() / FRAME_COLS,
				moveTileSheet.getHeight() / FRAME_ROWS);
		moveTileFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				moveTileFrames[index++] = tmp[i][j];
			}
		}
		moveTileAnimation = new Animation(0.025f, moveTileFrames);
    }
    
    public static Animation getMoveTileAnimation() {
		return moveTileAnimation;
	}

	public void setMoveTileAnimation(Animation moveTileAnimation) {
		this.moveTileAnimation = moveTileAnimation;
	}

	public TextureRegion[] getMoveTileFrames() {
		return moveTileFrames;
	}

	public void setMoveTileFrames(TextureRegion[] moveTileFrames) {
		this.moveTileFrames = moveTileFrames;
	}

	public static TextureRegion getMoveTileCurrentFrame() {
		return moveTileCurrentFrame;
	}

	public static void setMoveTileCurrentFrame(TextureRegion moveTileCurrentFrame) {
		Animations.moveTileCurrentFrame = moveTileCurrentFrame;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
