package org.roper;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements IGameObject {
	Image sprite;

	Vec pos;
	Vec dPos;
	
	World world;
	
	public Player(World world) {
		sprite = null;
		this.world = world;
	}
	
	@Override
	public void init() {
		pos = new Vec(40, 40);
		dPos = new Vec();
		
		try {
		    sprite = ImageIO.read(new File("share/bild2.jpg"));
		} catch (IOException e) {
		}
		
		world.sprites.add(new Sprite(sprite, pos)); 
		//hope pos is called by referenze, but it should :)
		
		
		
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


