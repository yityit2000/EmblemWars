package com.dvoragames.emblemwars2;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.materials.BlendingAttribute;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class EmblemWars implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private static Sprite soldierSprite;
	private Sprite levelSprite;
	private GameInputProcessor inputProcessor;
	
	// This will be used to keep track of the different game states
	// gameState = 1 : Player turn
	// gameState = 2 : Enemy turn
	// gameState = 3 : Game Paused
	private int gameState = 1;
	
	private static final int        FRAME_COLS = 20;         
    private static final int        FRAME_ROWS = 1;         
    
    Animation                       moveTileAnimation;          
    Texture                         moveTileSheet;              
    TextureRegion[]                 moveTileFrames;             
    TextureRegion                   moveTileCurrentFrame;           
    
    float stateTime;                                        
	
	private BitmapFont font;
	
	private Button button;

	private ShapeRenderer shapeRenderer;

	private EmblemWars game;

	private static boolean soldierIsSelected = false;
	private static boolean displayTurnPrompt = false;

	// private TiledMap map;
	// private OrthogonalTiledMapRenderer renderer;
	private Texture soldierTexture;
	private Texture levelSampleTexture;
	private Texture movementTileTexture;

	private Tile[][] tiles;
	
	private Unit[] playerUnits;
	private Unit[] enemyUnits;

	// used to store the tile that the soldier is on
	private int tI = 5;
	private int tJ = 4;

	// used to generate movement range
	int r;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		// create the movementTile animation
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
		stateTime = 0f;

		batch = new SpriteBatch();

		soldierTexture = new Texture(
				Gdx.files.internal("data/soldier 64_64.png"));
		// soldierTexture = new
		// Texture(Gdx.files.internal("data/soldierSprite.png"));
		soldierTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		movementTileTexture = new Texture(
				Gdx.files.internal("data/MovementTile.png"));
		movementTileTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		levelSampleTexture = new Texture(
				Gdx.files.internal("data/level-sample.png"));
		levelSampleTexture
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		levelSprite = new Sprite(levelSampleTexture);
		levelSprite.setPosition(0, 0);

		// map = new TmxMapLoader().load("data/level-sample.tmx");
		// renderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);

		soldierSprite = new Sprite(soldierTexture);
		soldierSprite.setPosition(320, 256);
		
		font = new BitmapFont();

		shapeRenderer = new ShapeRenderer();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 832, 512);
		// camera.update();

		tiles = new Tile[13][8];
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 8; j++) {

				// creates a grid of tiles using the Tile object
				tiles[i][j] = new Tile(64 * (i), 64 * (j));
			}
		}
		
		playerUnits = new Unit[8];
		
		playerUnits[1] = new Unit("Soldier",320,256,13,5,4,3,false);
		
//		for (int i = 0; i < playerUnits.length; i++){
//			playerUnits[i] = new Unit("Soldier",320,256,13,5,4,3);
//		}

		GameInputProcessor inputProcessor = new GameInputProcessor(game);
		Gdx.input.setInputProcessor(inputProcessor);

	}

	public class GameInputProcessor implements InputProcessor {

		// reference to the game
		EmblemWars game;

		public GameInputProcessor(EmblemWars game) {
			this.game = game;
		}

		@Override
		public boolean keyUp(int keycode) {
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			return false;
		}

		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchUp(int x, int y, int pointer, int button) {

			Vector3 touchpoint = new Vector3();
			camera.unproject(touchpoint.set(x, y, 0));

			if (gameState == 1) { // Player Turn
				if (touchpoint.x > soldierSprite.getX()
						&& touchpoint.x < (soldierSprite.getX() + soldierSprite
								.getWidth())
						&& touchpoint.y > soldierSprite.getY()
						&& touchpoint.y < (soldierSprite.getY() + soldierSprite
								.getHeight()) && soldierIsSelected == true) {
					soldierIsSelected = false;
					displayTurnPrompt = false;

					for (int i = 0; i < tiles.length; i++) {
						for (int j = 0; j < tiles[1].length; j++) {
							tiles[i][j].setMoveable(0);
						}
					}
				} else if (soldierIsSelected) {

					moveToClosestTile(touchpoint.x, touchpoint.y, soldierSprite);
					displayTurnPrompt = true;
					// soldierIsSelected = false;

				} else if (touchpoint.x > soldierSprite.getX()
						&& touchpoint.x < (soldierSprite.getX() + soldierSprite
								.getWidth())
						&& touchpoint.y > soldierSprite.getY()
						&& touchpoint.y < (soldierSprite.getY() + soldierSprite
								.getHeight()) && soldierIsSelected == false) {
					soldierIsSelected = true;
					tiles[tI][tJ].setMoveable(1);
					createMovementRange(3);
				} else {
					soldierIsSelected = false;
				}
			}else if (gameState == 2) { // Enemy Turn
				//eventually, holding your finger or mouse down will speed up the enemy turn
			}else if (gameState == 3) { // Pause
				//include controls for the paused game
			}

			return false;
		}

		@Override
		public boolean touchDragged(int x, int y, int pointer) {
			return false;
		}

		public boolean touchMoved(int x, int y) {
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			return false;
		}

		@Override
		public boolean keyDown(int keycode) {
			return false;
		}
	}

	public void moveToClosestTile(float x, float y, Sprite sprite) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[1].length; j++) {

				// checks if x and y coordinates are within any of the tiles
				// (loops through all tiles)
				if (x > tiles[i][j].getX()
						&& x < tiles[i][j].getX() + tiles[i][j].getWidth()
						&& y > tiles[i][j].getY()
						&& y < tiles[i][j].getY() + tiles[i][j].getHeight()) {

					// sets the sprite coordinates to the tile position (not the
					// click/touch position)
					if (tiles[i][j].getMoveable() == 1) {
						sprite.setX(tiles[i][j].getX());
						sprite.setY(tiles[i][j].getY());
						tI = i;
						tJ = j;
						System.out.println(i + ", " + j);
					}
				}
			}
		}
	}

	// makes the Moveable variable 1 to tiles surrounding the given tile
	public void makeNeighborsMoveable(int i, int j) {
		try {
			tiles[i][j + 1].setMoveable(1);
		} catch (ArrayIndexOutOfBoundsException asdf) {
		}
		try {
			tiles[i][j - 1].setMoveable(1);
		} catch (ArrayIndexOutOfBoundsException asdf) {
		}
		try {
			tiles[i - 1][j].setMoveable(1);
		} catch (ArrayIndexOutOfBoundsException asdf) {
		}
		try {
			tiles[i + 1][j].setMoveable(1);
		} catch (ArrayIndexOutOfBoundsException asdf) {
		}
	}

	public void createMovementRange(int range) {
		r = 1;
		while (r < range) {
			for (int i = tI - r; i < tI + r + 1; i++) {
				makeNeighborsMoveable(i, tJ);
			}

			for (int j = tJ - r; j < tJ + r + 1; j++) {
				makeNeighborsMoveable(tI, j);
			}
			r++;
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
		soldierTexture.dispose();
		levelSampleTexture.dispose();
	}

	@Override
	public void render() {

		// Draw graphics to the screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();                       // #15
        moveTileCurrentFrame = moveTileAnimation.getKeyFrame(stateTime / 2, true);

		// renderer.setView(camera);
		// renderer.render();

		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// Draw the tilemap
		levelSprite.draw(batch);
		
		if (soldierIsSelected) {

			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[1].length; j++) {
					if (tiles[i][j].getMoveable() > 0) {
						batch.draw(moveTileCurrentFrame, tiles[i][j].getX(), tiles[i][j].getY(), 
								tiles[i][j].getWidth(), tiles[i][j].getHeight());
					}
				}
			}
		}
		// Draw the soldier
		soldierSprite.draw(batch);
		if (displayTurnPrompt) {
			font.draw(batch, "Wait", soldierSprite.getX() - 32, soldierSprite.getY() + 32);	
		}
		batch.end();

		// Draw tile outlines
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.BLACK);
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 8; j++) {
				shapeRenderer.box(tiles[i][j].getX(), tiles[i][j].getY(), 0,
						tiles[i][j].getWidth(), tiles[i][j].getHeight(), 0);
			}
		}
		shapeRenderer.end();

		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.CYAN);
		if (soldierIsSelected) {
			for (int i = 0; i < tiles.length; i++) {
				for (int j = 0; j < tiles[1].length; j++) {
					if (tiles[i][j].getMoveable() > 0) {
						shapeRenderer.rect(tiles[i][j].getX(),
								tiles[i][j].getY(), tiles[i][j].getWidth(),
								tiles[i][j].getHeight());
					}
				}
			}
		}
		shapeRenderer.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
