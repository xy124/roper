package org.roper;

import java.awt.Image;
import java.util.ArrayList;

public class World implements IGameObject {
	
	ArrayList<Sprite> sprites;

	@Override
	public void init() {
		
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



}
