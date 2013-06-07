package org.roper;

import java.awt.Image;
import java.util.ArrayList;

public class World implements IGameObject {
	
	Sprite background;
	ArrayList<Sprite> sprites;
	ArrayList<Rope> ropes;
	
	public World() {
		//all these will be drawn in game's paint()
		sprites = new ArrayList<Sprite>();
		ropes = new ArrayList<Rope>();
		
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

	public Sprite getBackground() {		
		return background;
	}

}
