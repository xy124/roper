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
		return getCollision(pos.add(0.0f,1.0f)).bottom;
	}

	@Override
	public void update() {
		
		
		dPos.y += GRAVITY; 	
		
		//hint: to change precision divide k_end by p and multiply  NdPos by p
		
		Vec NdPos = dPos.normalize();
		
		//k * NdPos =: tempVec
		int k = 0;
		int k_end = (int) (dPos.abs()+1.0f);
				
		
		Vec tempVec;
		
		
		
		boolean notThereYet = true;
		while (notThereYet) {
			tempVec = pos.add(NdPos);
			k++;						
			if (k == k_end) {
				notThereYet = false;
				
			} else {
				 Collision col = getCollision(tempVec);
			
				
				boolean mayMove = false;
				
				if (col.bottom && (dPos.y > 0)) //dPos.y > 0   <=> jumping 
					NdPos.y = 0.0f;						
				else
					mayMove = true;
				
			
				if (col.horizontal()) 					
					NdPos.x = 0.0f;					
				else
					mayMove = true;
					 
			
				if (mayMove) 
					pos = pos.add(NdPos);
				else
					notThereYet = false;		
				
			} 
					
			
		
		}
		
		//if (colliding) 
			//pos = pos.add(NdPos.multiply(-3));
		

		sprite.pos = pos;
	}

	private Collision getCollision(Vec tempVec) {
		Collision result = new Collision();
		result.top = tempVec.y < 10;
		result.bottom = tempVec.y+sprite.getHeight() > parent.getHeight()-10;
		result.left = tempVec.x < 10;
		result.right = tempVec.x+sprite.getWidth() > parent.getWidth()-10;
		return result;
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


