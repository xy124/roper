package org.roper;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Player implements IGameObject, KeyListener {
	final float MSPEED  = 5.0f;
	final float GRAVITY = 1.0f;
	final float JSPEED  = -15.0f;
	
	
	Vec pos;
	Vec dPos;
	
	World world;
	
	Sprite sprite;
	
	private Game parent;
	
	public Player(World world) {
		sprite = null;
		this.world = world;
		parent = null;
	}
	
	@Override
	public void init() {
		pos = new Vec(40, 40);
		dPos = new Vec();
		
		
		sprite = new Sprite();
		sprite.load("share/bild2.jpg"); 
		
		world.sprites.add(sprite); 
				
	}
	
	void setParent(Game p) {
		parent = p;
	}
	
	boolean isOnGround() {
		return isCollission(pos.add(0.0f,1.0f));
	}

	@Override
	public void update() {
		dPos.y += GRAVITY; 	
		
		Vec finalPos = pos.add(dPos);
		
		Vec NdPos = dPos.normalize();
		
		Vec tempVec = pos.add(NdPos);
		
		boolean colliding = false;
		boolean notThereYet = true;
		while (notThereYet) {						
			if (!pos.equals(finalPos, 1.0f)) 
				if  (!isCollission(tempVec)) {
					pos = tempVec;		
					tempVec = pos.add(NdPos);
				} else {
					notThereYet = false;
					colliding = true;
				}			
			else 
				notThereYet = false;	
		}
		
		//if (colliding) 
			//pos = pos.add(NdPos.multiply(-3));
		

		sprite.pos = pos;
	}

	private boolean isCollission(Vec pos) {
		//check bordercollission:
		return ( (pos.x+sprite.getWidth() >= parent.getWidth()) || (pos.x <= 0)
				|| (pos.y+sprite.getHeight() >= parent.getHeight()) || (pos.y <= 0) );
		
		
	}

	@Override
	public void quit() {
		world.sprites.remove(sprite);

	}

	@Override
	public void keyPressed(KeyEvent evt) {
		
		if (evt.getKeyCode() == KeyEvent.VK_RIGHT) 
			dPos.x = MSPEED;
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) 
			dPos.x = -MSPEED;
		
		if ((evt.getKeyCode() == KeyEvent.VK_UP) && isOnGround() )
			dPos.y = JSPEED;
		
		
		
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


