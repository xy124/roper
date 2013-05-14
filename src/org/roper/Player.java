package org.roper;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements IGameObject {
	Image sprite;

	Vec pos;
	Vec dPos;
	
	public Player() {
		sprite = null;
	}
	
	@Override
	public void init() {
		pos = new Vec(40, 40);
		dPos = new Vec();
		
		try {
		    sprite = ImageIO.read(new File("share/bild.jpg"));
		} catch (IOException e) {
		}
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


