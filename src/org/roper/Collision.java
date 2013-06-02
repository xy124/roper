package org.roper;

public class Collision {
	boolean top, bottom, left, right;
	
	public Collision() {
		top = false;
		bottom = false;
		left = false;
		right = false;
	}
	
	public Collision(boolean top, boolean bottom, boolean left, boolean right) {		
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	public Collision(Collision it) {
		this.top = it.top;
		this.bottom = it.bottom;
		this.left = it.left;
		this.right = it.right;
	}
	
	boolean horizontal() {
		return (left || right);
	}
	
	boolean vertical() {
		return (top || bottom);
	}
	
	Collision add(Collision rhs) {
		Collision result = new Collision(); 
		result.top    = this.top    || rhs.top;
		result.bottom = this.bottom || rhs.bottom;
		result.left   = this.left   || rhs.left;
		result.right  = this.right  || rhs.right;
		return result;		
	}
	
}
