package org.roper;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	//TODO  add things like rotation... and frame
	
	
	public void load(String filename) {
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.err.println("couldn't read "+ filename);
		}
		
	}
	
	public Image getImg() {
		return img;
	}

}
