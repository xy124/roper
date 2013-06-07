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
		parent = null;
		
		pos = new Vec();
		dPos = new Vec();
		sprite = new Sprite();
	}
	
	
	public void init(Game parent) {
		pos.set(40.0f, 40.0f);
		
		sprite.load("share/bild2.jpg"); 
		
		this.parent = parent;
		
		super.init(parent.getWorld(), sprite.getRect());
		
		world.sprites.add(sprite);
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
		
		
		if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) 
			System.exit(0);
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) 
			dPos.x = MSPEED;
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) 
			dPos.x = -MSPEED;
		
		if ((evt.getKeyCode() == KeyEvent.VK_UP) && isOnGround() ) 
			dPos.y = JSPEED;
		
		if ((evt.getKeyCode() == KeyEvent.VK_SPACE) ) {//rope!
			Rope r = new Rope();
			r.init(new Vec(1,1), this, world);
			world.ropes.add(r);
			parent.addChild(r);
			
		}
		
		
		//TODO: for debug only: del all ropes...
		if ((evt.getKeyCode() == KeyEvent.VK_D) ) {//rope!
			for (Rope r: world.ropes){
				parent.removeChild(r);
			
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


