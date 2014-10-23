package com.sanchi.zoihelpers;

import com.badlogic.gdx.InputProcessor;
import com.sanchi.gameobjects.Zoi;

public class InputHandler implements InputProcessor {
	private Zoi myZoi;
	
	public InputHandler(Zoi zoi) {
		myZoi = zoi;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		myZoi.onClick();
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
