package org.roper;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements IGameObject, KeyListener {
	Vec pos;
	Vec dPos;
	
	World world;
	
	Sprite sprite;
	
	public Player(World world) {
		sprite = null;
		this.world = world;
	}
	
	@Override
	public void init() {
		pos = new Vec(40, 40);
		dPos = new Vec();
		
		sprite = new Sprite();
		sprite.load("share/bild2.jpg"); 
		
		world.sprites.add(sprite); 
				
	}

	@Override
	public void update() {
		pos.add(dPos); 
		
		sprite.pos = pos;

	}

	@Override
	public void quit() {
		world.sprites.remove(sprite);

	}

	@Override
	public void keyPressed(KeyEvent evt) {
		final int MSPEED = 10;
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) 
			dPos.x = MSPEED;
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) 
			dPos.y = -MSPEED;
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		if ((evt.getKeyCode() == KeyEvent.VK_RIGHT) && (dPos.x > 0)) 
			dPos.x = 0;
		if ((evt.getKeyCode() == KeyEvent.VK_RIGHT) && (dPos.x < 0)) 
			dPos.x = 0;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}


