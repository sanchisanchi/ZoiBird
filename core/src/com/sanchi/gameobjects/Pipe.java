package com.sanchi.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable {
	private Random r;
	private Rectangle headUp, headDown, barUp, barDown;
	public static final int VERTICAL_GAP = 45;
	public static final int HEAD_WIDTH = 24;
	public static final int HEAD_HEIGHT = 11;
	private float groundY;
	private boolean isScored = false;
	
	public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		headUp = new Rectangle();
	    headDown = new Rectangle();
	    barUp = new Rectangle();
	    barDown = new Rectangle();
	    this.groundY = groundY;
	}
	
	@Override
    public void update(float delta) {
        // Call the update method in the superclass (Scrollable)
        super.update(delta);

        // The set() method allows you to set the top left corner's x, y
        // coordinates, along with the width and height of the rectangle
        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                groundY - (position.y + height + VERTICAL_GAP));

        // Our bar head width is 24. The bar is only 22 pixels wide. So the head
        // must be shifted by 1 pixel to the left (so that the head is centered
        // with respect to its bar).
        
        // This shift is equivalent to: (HEAD_WIDTH - width) / 2
        headUp.set(position.x - (HEAD_WIDTH - width) / 2, position.y + height
                - HEAD_HEIGHT, HEAD_WIDTH, HEAD_HEIGHT);
        headDown.set(position.x - (HEAD_WIDTH - width) / 2, barDown.y,
        		HEAD_WIDTH, HEAD_HEIGHT);

    }
	
	public void reset(float newX) {
		super.reset(newX);
		height = r.nextInt(90) + 15;
		isScored = false;
	}
	
	public boolean collides(Zoi zoi) {
        if (position.x < zoi.getX() + zoi.getWidth()) {
            return (Intersector.overlaps(zoi.getBoundingCircle(), barUp)
                    || Intersector.overlaps(zoi.getBoundingCircle(), barDown)
                    || Intersector.overlaps(zoi.getBoundingCircle(), headUp) || Intersector
                        .overlaps(zoi.getBoundingCircle(), headDown));
        }
        return false;
    }
	
	public void onRestart(float x, float scrollSpeed) {
	    velocity.x = scrollSpeed;
	    reset(x);
	}
	
	public Rectangle getHeadUp() {
        return headUp;
    }

    public Rectangle getHeadDown() {
        return headDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }
    
    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean b) {
        isScored = b;
    }

}
