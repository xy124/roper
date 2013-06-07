package org.roper;

public class Collidable {
	
	Vec pos;
	Vec dPos;
	
	World world;
	
	private Rect me;
	
	public Collidable() {
		world = null;
	}

	void init(World collidingWorld, Rect myRect) {
		me = myRect;
		world = collidingWorld;
		
	}
	
	synchronized void doPhysics() {
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
				Collision col = getCollision(tempVec, NdPos);


				boolean mayMove = false;

				if (col.vertical()) { //dPos.y > 0   <=> jumping 
						NdPos.y = 0.0f;
						dPos.y = 0.0f; //brake as I'm on the floor
				} else
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
				

	}
	
	Collision getCollision(Vec tempVec) {
		return getCollision(tempVec, null);
	}

	Collision getCollision(Vec where, Vec dWhere) {		
		//which borders to check?
		//...
		boolean right  = false, 
				left   = false,
				top    = false,
				bottom = false;
		boolean checkAll = (dWhere == null);
		if (dWhere == null)
			dWhere = new Vec();
		
		if (checkAll || (dWhere.x > 0.0f)) {
			right = collisionCheckLine(
					where.add(me.getWidth(), 0.0f),
					where.add(me.getWidth(), me.getHeight()) );
		}
		
		if (checkAll || (dWhere.x < 0.0f)) {
			left  = collisionCheckLine(
					where,
					where.add(0.0f, me.getHeight()) );
		}
		
		if (checkAll || (dWhere.y < 0.0f)) {
			top   = collisionCheckLine(
					where,
					where.add(me.getWidth(), 0.0f) );
		}
		
		if (checkAll || (dWhere.y > 0.0f)) {
			bottom = collisionCheckLine(
					where.add(0.0f, me.getHeight()),
					where.add(me.getWidth(), me.getHeight()) );
		}
		
		return new Collision(top, bottom, left, right);
	}

	private boolean collisionCheckLine(Vec start, Vec end) {
		Vec step = end.subtract(start);
		int times = (int) step.abs();
		step = step.normalize();
	
		start = start.add(step);
		
		for (int i = 1; i < times; i++) {	
			
			if (world.getBackground().isSolid(start))
				return true;
			start = start.add(step);
		}
				
		return false;
	}

	

}
