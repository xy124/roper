package org.roper;

public class Collission {
	boolean horizontal = false;
	boolean vertical = false;
	
	Collission add(Collission rhs) {
		Collission result = new Collission();
		result.horizontal = this.horizontal | rhs.horizontal;
		result.vertical   = this.vertical   | rhs.vertical;
		return result;
	}
}
