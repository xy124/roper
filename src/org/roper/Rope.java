package org.roper;

import java.util.ArrayList;
import java.util.List;

public class Rope implements IGameObject {
	List<Vec> knicks;
	Player owner;
	Vec end;
	World world;
	int len;
	boolean isShooting;
	
	Vec dir;
	
	final private float shootSpeed = 20.0f;
	final private int maxRopeLen = 200;
	boolean killMe; //TODO: handle that better!
	
	public Rope() {
		killMe = false;
		knicks = new ArrayList<Vec>();
	}



	public void init(Vec direction, Player owner, World world) {				
		this.owner = owner;
		len = 0;
		isShooting = true;
		this.world = world;
		this.dir = direction.normalize();
		
		end = owner.pos;		
	}



	@Override
	public void update() {
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
		
		
	}



	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
