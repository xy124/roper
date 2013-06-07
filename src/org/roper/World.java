package org.roper;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class World implements IGameObject {
	
	Sprite background;
	
	
	public World() {
		//all these will be drawn in game's paint()		
		
		background = new Sprite();
	}
	

	public void init() {		
		background.load("share/bild.jpg");	
	}

	@Override
	public void update(Graphics g) {
		//Hint: worldimg is drawn from game to put it in background!
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	public Sprite getBackground() {		
		return background;
	}

}
