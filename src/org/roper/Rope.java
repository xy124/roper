package org.roper;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Rope implements IGameObject {
	List<Vec> knicks;
	Player owner;
	Vec end, start;
	World world;
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
		
		
		this.world = world;
		this.dir = direction.normalize();
		
		active = false;
		
	}
	
	/**
	 * should be called before every ropethrow! 
	 */
	void reset() {
		len = 0;
		isShooting = true;
		
		knicks.clear();		
		
		start = owner.pos; //TODO: is no call by ref...
		
		end = owner.pos;	
	}



	@Override
	public synchronized void update(Graphics g) {
		if (!active)
			return;
		
		if (isShooting) {
			boolean notThereYet = true;
			int k = 1;
			while (notThereYet) {
				if (world.background.isSolid(end)) {
					isShooting = false;
					notThereYet = false;
				} else {					
					end = end.add(dir);
					k++;
					len++;
					if (k > shootSpeed)
						notThereYet = false;
					if (len > maxRopeLen) {
						isShooting = false;
						notThereYet = false;
						killMe = true;
					}
				}
			}
		} else
			; //TODO do what???
		
		//draw it!
		
		//System.out.println("Time draw rope:"+System.currentTimeMillis());
		
		//low: maybe put this in extern paint function
		g.drawLine((int)owner.pos.x, (int)owner.pos.y, (int)end.x, (int)end.y);
		
		
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
