package org.roper;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Rope extends Collidable implements IGameObject {
	List<Vec> knicks;
	Player owner;
	
	int len;
	boolean isShooting;
	
	Vec dir;
	
	final private float shootSpeed = 20.0f;
	final private int maxRopeLen = 200;
	boolean killMe; //TODO: handle that better!
	private boolean active;
	
	public Rope() {
		killMe = false; //TODO: not needed anymore
		
		knicks = new ArrayList<Vec>();
		active = false;
	}



	public void init(Vec direction, Player owner, World world) {				
		this.owner = owner;
		
				
		this.dir = direction.normalize();
		
		active = false;
		
		super.init(world, new Rect(0,0));
				
		
		
	}
	
	/**
	 * should be called before every ropethrow! 
	 */
	void reset() {
		len = 0;
		isShooting = true;
		
		knicks.clear();		
						
		pos = owner.pos;
	}



	@Override
	public synchronized void update(Graphics g) {
		if (!active)
			return;
		
		if (isShooting) {
			//low hope that owner's dpos didn't change this update
			dPos = dir.multiply(shootSpeed).add(owner.dPos);						
			
			doPhysics();

			//one step further to be on solid with rope
			pos = pos.add(dPos.normalize());

			//next step wuold be a collission?
			
			len = (int) (owner.pos.subtract(pos).abs());
			if (world.background.isSolid(pos)) {
				isShooting = false;					
			} else {		
							
				if (len > maxRopeLen) {
					isShooting = false;

					killMe = true;
					active = false;
				}
			}
		} else
			dPos.set(0.0f, 0.0f);
		
		
		//draw it!
		
		//System.out.println("Time draw rope:"+System.currentTimeMillis());
		
		//low: maybe put this in extern paint function
		g.drawLine((int)owner.pos.x, (int)owner.pos.y, (int)pos.x, (int)pos.y);
		
		
	}


	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean b) {
		active = b;
		
	}
	
	
	
	
	
}
