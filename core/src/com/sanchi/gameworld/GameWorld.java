package com.sanchi.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.sanchi.gameobjects.ScrollHandler;
import com.sanchi.gameobjects.Zoi;
import com.sanchi.zoihelpers.AssetLoader;

public class GameWorld {
	private Zoi zoi;
	private ScrollHandler scroller;
	private Rectangle ground;
	private int score = 0;
	private GameState currentState;
	public int midPointY;

	public enum GameState {
		READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	public GameWorld(int midPointY) {
		currentState = GameState.READY;
		this.midPointY = midPointY;
		zoi = new Zoi(33, midPointY - 5, 17, 12);
		scroller = new ScrollHandler(this, midPointY + 66);
		ground = new Rectangle(0, midPointY + 66, 136, 11);
	}

	public void updateRunning(float delta) {
		if (delta > .15f) {
			delta = .15f;
		}

		zoi.update(delta);
		scroller.update(delta);

		if (scroller.collides(zoi) && zoi.isAlive()) {
			scroller.stop();
			zoi.die();
			AssetLoader.dead.play();
		}

		if (Intersector.overlaps(zoi.getBoundingCircle(), ground)) {
			scroller.stop();
			zoi.die();
			zoi.decelerate();
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}

	}

	public void update(float delta) {

		switch (currentState) {
		case READY:
			updateReady(delta);
			break;

		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}

	}

	private void updateReady(float delta) {
		// Do nothing for now
	}

	public Zoi getZoi() {
		return zoi;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void restart() {
		currentState = GameState.READY;
		score = 0;
		zoi.onRestart(midPointY - 5);
		scroller.onRestart();
		currentState = GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

}
