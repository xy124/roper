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

	public Vec(Vec pos) {
		this.x = pos.x;
		this.y = pos.y;
	}

	public Vec add(Vec dPos) {
		Vec res = new Vec(this);
		res.x += dPos.x;
		res.y += dPos.y;
		
		return res;
		
	}
	
	public float abs() {
		return (float) Math.pow((x*x+y*y), 0.5);
	}

	public Vec normalize() {
		float n = abs(); 
		Vec res = new Vec(x/n, y/n);
		return res;
	}

	public boolean equals(Vec p, float tollerance) {	
		return (p.x+tollerance > x) && (p.x-tollerance < x)
				&& (p.y+tollerance > y) && (p.y-tollerance < y);
	}

	public Vec add(float dx, float dy) {
		Vec res = new Vec(this);
		res.x += dx;
		res.y += dy;
		return res;
	}

	public void reset() {
		x = 0;
		y = 0;
		
	}

	public Vec multiply(float f) {
		Vec res = new Vec(this);
		res.x *= f;
		res.y *= f;
		return res;		
	}

	public Vec subtract(Vec rhs) {
		Vec res = new Vec(this);
		res.x -= rhs.x;
		res.y -= rhs.y;
		return res;		
	}
	
	public Vec subtract(float dx, float dy) {
		Vec res = new Vec(this);
		res.x -= dx;
		res.y -= dy;
		return res;
	}

	
	
}
