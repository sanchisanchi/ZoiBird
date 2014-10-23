package com.sanchi.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.sanchi.gameobjects.Zoi;
import com.sanchi.gameobjects.Grass;
import com.sanchi.gameobjects.Pipe;
import com.sanchi.gameobjects.ScrollHandler;
import com.sanchi.zoihelpers.AssetLoader;

public class GameRenderer {
	private OrthographicCamera cam;
	private GameWorld myWorld;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	private int midPointY;
	private int gameHeight;
	private Zoi zoi;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Pipe pipe1, pipe2, pipe3;
	private TextureRegion bg, grass;
	private Animation zoiAnimation;
	private TextureRegion zoiMid, zoiDown, zoiUp;
	private TextureRegion pipeUp, pipeDown, bar;

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, 204);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();
	}

	private void initGameObjects() {
		zoi = myWorld.getZoi();
		scroller = myWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		zoiAnimation = AssetLoader.zoiAnimation;
		zoiMid = AssetLoader.zoi;
		zoiDown = AssetLoader.zoiDown;
		zoiUp = AssetLoader.zoiUp;
		pipeUp = AssetLoader.pipeUp;
		pipeDown = AssetLoader.pipeDown;
		bar = AssetLoader.bar;
	}
	
	private void drawGrass() {
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }
	
	private void drawPipeHeads() {
        batcher.draw(pipeUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(pipeDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);
        batcher.draw(pipeUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(pipeDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);
        batcher.draw(pipeUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(pipeDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);
    }
	
	private void drawPipes() {
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));
    }
	
	public void render(float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		shapeRenderer.setColor(56 / 255.0f, 178 / 255.0f, 200 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, midPointY + 66);

		// Grass
		shapeRenderer.setColor(36 / 255.0f, 120 / 255.0f, 48 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 66, 136, 11);

		// Dirt
		shapeRenderer.setColor(159 / 255.0f, 79 / 255.0f, 10 / 255.0f, 1);
		shapeRenderer.rect(0, midPointY + 77, 136, 52);

		shapeRenderer.end();

		batcher.begin();
		batcher.disableBlending();
		batcher.draw(bg, 0, midPointY + 23, 136, 43);
		
		drawGrass();
		drawPipes();

		batcher.enableBlending();
		drawPipeHeads();

		if (zoi.shouldntFlap()) {
			batcher.draw(zoiMid, zoi.getX(), zoi.getY(),
					zoi.getWidth() / 2.0f, zoi.getHeight() / 2.0f,
					zoi.getWidth(), zoi.getHeight(), 1, 1, zoi.getRotation());
		} else {
			batcher.draw(zoiAnimation.getKeyFrame(runTime), zoi.getX(),
                    zoi.getY(), zoi.getWidth() / 2.0f,
                    zoi.getHeight() / 2.0f, zoi.getWidth(), zoi.getHeight(),
                    1, 1, zoi.getRotation());
		}

		batcher.end();
	}
}
