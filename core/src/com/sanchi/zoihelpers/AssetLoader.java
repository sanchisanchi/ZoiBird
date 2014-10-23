package com.sanchi.zoihelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture;
	public static TextureRegion bg, grass;
	public static Animation zoiAnimation;
	public static TextureRegion zoi, zoiDown, zoiUp;
	public static TextureRegion pipeUp, pipeDown, bar;
	
	public static void load() {
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
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
	}
	
	public static void dispose() {
		texture.dispose();
	}
}
