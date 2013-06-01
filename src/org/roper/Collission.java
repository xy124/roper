package org.roper;

public class Collission {
	boolean horizontal() {
		return (left || right);
	}
	boolean vertical() {
		return (top || bottom);
	}
	
	boolean top = false;
	boolean bottom = false;
	boolean left = false;
	boolean right = false;
	;
	
}
