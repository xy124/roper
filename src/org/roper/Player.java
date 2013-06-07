package org.roper;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Player extends Collidable implements IGameObject, KeyListener {
	final float MSPEED  = 5.0f;
	final float GRAVITY = 0.9f;
	final float JSPEED  = -17.0f;
		
	
	Sprite sprite;
	
	Rope rope;
	
	public Player() {
		super();				
		
		sprite = new Sprite();
		
		rope = new Rope();
	}
	
	
	public void init(Game parent) {
		pos.set(40.0f, 40.0f);
		
		sprite.load("share/bild2.jpg"); 
		
		super.init(parent.getWorld(), sprite.getRect());
		
		rope.init(new Vec(1,1), this, world);
		
		parent.addChild(this);
		
		parent.addChild(rope);
				
	}
	
	boolean isOnGround() {
		return getCollision(pos.add(0.0f,1.0f)).bottom;
	}

	
	
	@Override
	public void update(Graphics g) {
		
		//TODO: put in doPhysics?
		dPos.y += GRAVITY; 	
		
		doPhysics();
		
		sprite.pos = pos;
		
		//System.out.println("Time draw player:"+System.currentTimeMillis());
		
		g.drawImage(sprite.img, (int) sprite.pos.x, (int) sprite.pos.y, null);	
	}

	@Override
	public void quit() {
		
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
		
		if (evt.getKeyCode() == KeyEvent.VK_SPACE  && !rope.isActive()) {//rope!						
			rope.reset();
			rope.setActive(true);						
		}
		
		if (evt.getKeyCode() == KeyEvent.VK_D) {
			//delete ropes for debug reasons... TODO			
			rope.setActive(false);
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


