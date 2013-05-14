package org.roper;

import java.awt.Image;

public class Sprite {
	public Sprite() {
		pos = new Vec();
		img = null;
	}
	
	public Sprite(Image img, Vec pos) {
		this.pos = pos;
		this.img = img;
	}
	
	Image img;
	Vec pos;
	//TODO  add things like rotation...
}
