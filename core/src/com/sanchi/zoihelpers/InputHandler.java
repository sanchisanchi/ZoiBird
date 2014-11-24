package com.sanchi.zoihelpers;

import com.badlogic.gdx.InputProcessor;
import com.sanchi.gameobjects.Zoi;
import com.sanchi.gameworld.GameWorld;

public class InputHandler implements InputProcessor {
	private Zoi myZoi;
	private GameWorld myWorld;
	
	public InputHandler(GameWorld myWorld) {
	    this.myWorld = myWorld;
	    myZoi = myWorld.getZoi();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isReady()) {
            myWorld.start();
        }
        
		myZoi.onClick();
		
		if (myWorld.isGameOver() || myWorld.isHighScore()) {
            myWorld.restart();
        }
		
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
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
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
