package org.roper;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class Player extends Collidable implements IGameObject, KeyListener {
	final float MSPEED  = 5.0f;
	final float GRAVITY = 0.9f;
	final float JSPEED  = -17.0f;
		
	
	Sprite sprite;
	
	private Game parent;
	
	public Player() {
		super();		
		sprite = null;
		parent = null;
	}
	
	
	@Override
	public void init() { //TODO; use constructor to set world... clean this up... only one init!
		pos = new Vec(40, 40);
		dPos = new Vec();
		sprite = new Sprite();
		sprite.load("share/bild2.jpg"); 
	}
	
	
	public void init(World pworld) {
		init();
		
		
		
		super.init(pworld, sprite.getRect());
		
		
		world.sprites.add(sprite);
		
		
		
				
	}
	
	void setParent(Game p) {
		parent = p;
	}
	
	boolean isOnGround() {
		return getCollision(pos.add(0.0f,1.0f)).bottom;
	}

	
	
	@Override
	public void update() {
		
		//TODO: put in doPhysics?
		dPos.y += GRAVITY; 	
		
		doPhysics();
		
		sprite.pos = pos;
	}

	@Override
	public void quit() {
		world.sprites.remove(sprite);
	}

	
	//TODO bug : if u press 2 keys at once it doesnt work :(
	//workaround: http://stackoverflow.com/questions/2702203/keyboard-input-for-a-game-in-java
	
	@Override
	public void keyPressed(KeyEvent evt) {
		
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) 
			dPos.x = MSPEED;
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) 
			dPos.x = -MSPEED;
		
		if ((evt.getKeyCode() == KeyEvent.VK_UP) && isOnGround() ) 
			dPos.y = JSPEED;
		
		if ((evt.getKeyCode() == KeyEvent.VK_SPACE) ) {//rope!
			Rope r = new Rope(new Vec(1.0f, 1.0f), this, world);
			world.ropes.add(r);
			parent.addChild(r);
			
		}
		
		
		//TODO: for debug only: del all ropes...
		if ((evt.getKeyCode() == KeyEvent.VK_D) ) {//rope!
			for (Rope r: world.ropes){
				parent.removeChild(r);
				r = null;
			}
			world.ropes.clear();
			
		}
			
		
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		if ((evt.getKeyCode() == KeyEvent.VK_RIGHT) && (dPos.x > 0)) 
			dPos.x = 0;
		if ((evt.getKeyCode() == KeyEvent.VK_LEFT) && (dPos.x < 0)) 
			dPos.x = 0;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	
	
}


