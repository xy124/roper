package org.roper;

public class Vec {
	float x, y;
	
	public Vec(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec() {
		x = 0;
		y = 0;
	}

	public void add(Vec dPos) {
		x += dPos.x;
		y += dPos.y;
		
	}
	
}
