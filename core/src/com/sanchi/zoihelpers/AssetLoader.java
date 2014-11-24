package com.sanchi.zoihelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture, logoTexture;
	public static Animation zoiAnimation;
	public static TextureRegion bg, grass, logo, zoiLogo;
	public static TextureRegion zoi, zoiDown, zoiUp;
	public static TextureRegion pipeUp, pipeDown, bar, playButtonUp, playButtonDown;
	public static Sound dead, flap, coin;
	public static BitmapFont font, shadow;
	public static Preferences prefs;
	
	public static void load() {
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);
        
        zoiLogo = new TextureRegion(texture, 0, 55, 135, 24);
        zoiLogo.flip(false, true);

		bg = new TextureRegion(texture, 0, 0, 136, 43);
		bg.flip(false, true);
		
		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);
		
		zoiDown = new TextureRegion(texture, 136, 0, 17, 12);
		zoiDown.flip(false, true);
		
		zoi = new TextureRegion(texture, 153, 0, 17, 12);
        zoi.flip(false, true);

        zoiUp = new TextureRegion(texture, 170, 0, 17, 12);
        zoiUp.flip(false, true);
        
        TextureRegion[] zois = {zoiDown, zoi, zoiUp};
        zoiAnimation = new Animation(0.06f, zois);
        zoiAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        pipeUp = new TextureRegion(texture, 192, 0, 24, 14);
        //Create by flipping existing pipeUp
        pipeDown = new TextureRegion(pipeUp);
        pipeDown.flip(false, true);
        
        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
        
        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);
        
        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("ZoiBird");

        // Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
	}
	
	// Receives an integer and maps it to the String highScore in prefs
	public static void setHighScore(int val) {
	    prefs.putInteger("highScore", val);
	    prefs.flush();
	}
	
	// Retrieves the current high score
	public static int getHighScore() {
	    return prefs.getInteger("highScore");
	}
	
	public static void dispose() {
		texture.dispose();
		dead.dispose();
		flap.dispose();
		coin.dispose();
		font.dispose();
		shadow.dispose();
	}
}
