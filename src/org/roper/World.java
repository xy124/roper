package org.roper;

import java.awt.Image;
import java.util.ArrayList;

public class World implements IGameObject {
	
	Sprite background;
	ArrayList<Sprite> sprites;
	

	@Override
	public void init() {
		background = new Sprite();
		background.load("share/bild.jpg");
		
		///all these will be drawn in game's paint()
		sprites = new ArrayList<Sprite>();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	public Object getBackground() {		
		return background;
	}



}
