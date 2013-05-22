package org.roper;

import java.awt.Image;
import java.util.ArrayList;

public class World implements IGameObject {
	
	Sprite background;
	ArrayList<Sprite> sprites;
	
	public World() {
		//all these will be drawn in game's paint()
		sprites = new ArrayList<Sprite>();
		
		background = new Sprite();
	}
	

	@Override
	public void init() {		
		background.load("share/bild.jpg");
		
		
		
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
