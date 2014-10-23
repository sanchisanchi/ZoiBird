package com.sanchi.zoibird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.sanchi.screens.GameScreen;
import com.sanchi.zoihelpers.AssetLoader;

public class ZoiGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("ZoiGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}
	
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
}
