package com.sanchi.gameworld;

import com.sanchi.gameobjects.Zoi;
import com.sanchi.gameobjects.ScrollHandler;

public class GameWorld {
	private Zoi zoi;
	private ScrollHandler scroller;

	
	public GameWorld(int midPointY) {
		zoi = new Zoi(33, midPointY - 5, 17, 12);
		scroller = new ScrollHandler(midPointY + 66);
	}
	
	public void update(float delta) {
		zoi.update(delta);
		scroller.update(delta);
	}
	
	public Zoi getZoi() {
		return zoi;
	}
	
	public ScrollHandler getScroller() {
        return scroller;
    }
	
}
