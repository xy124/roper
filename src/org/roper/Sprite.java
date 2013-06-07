package org.roper;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	public Sprite() {
		pos = new Vec();
		img = null;
	}
	
	public Sprite(BufferedImage img, Vec pos) {
		this.pos = pos;
		this.img = img;
	}
	
	BufferedImage img;
	

	Vec pos;


	private byte rawByte[] = null;
	
	//TODO  add things like rotation... and frame
	
	
	public void load(String filename) {
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.err.println("couldn't read "+ filename);
		}
		
	}
	
	public BufferedImage getImg() {
		return img;
	}

	public int getWidth() {	
		if (img != null)
			return img.getWidth();
		return 0;
		
	}
	
	public int getHeight() {
		if (img != null)
			return img.getHeight();
		return 0;
	}
	
	
	//TODO: return true if x,y > getwidth usw...
	synchronized boolean isSolid(int x, int y) {
		if (y < 10 ||
				y > getHeight() - 10 ||
				x < 10 ||
				x > getWidth() - 10)
			return true;
		
		if (rawByte  == null) 
			createRawByte();
		
		int i = (x + y * img.getWidth()) * 4;		
		if (i > rawByte.length-1) 
			return true;
		return ( (rawByte[i] == 0) && (rawByte[i+1] == 0) && (rawByte[i+2] == 0) );
	}

	public boolean isSolid(Vec it) {		
		return isSolid((int) it.x, (int) it.y);
	}
	
	private synchronized void createRawByte() {
		//stolen by http://www.java-forum.org/mobile-geraete/87137-kriege-rgb-werte-bildern-getrgb.html
		int amount = img.getWidth()*img.getHeight()*4;
		System.out.println("Created "+amount+" RawBytes");
		rawByte = new byte[amount];
		
		int raw[] = new int[img.getWidth() * img.getHeight()];
		
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), raw, 0, img.getWidth());
        int n = 0;
        for(int i = 0; i < raw.length; i++)
        {
            int ARGB = raw[i];
            int a = (ARGB & 0xff000000) >> 24; // Transparenzwerte -> ALPHA CHANNEL
            int r = (ARGB & 0xff0000) >> 16;
            int g = (ARGB & 0xff00) >> 8;
            int b = ARGB & 0xff;
            rawByte[n] = (byte)b;
            rawByte[n + 1] = (byte)g;
            rawByte[n + 2] = (byte)r;
            rawByte[n + 3] = (byte)a;
            n += 4;
        }
        raw = null;
		
	}

	public Rect getRect() {
		Rect result = new Rect();
		result.height = getHeight();
		result.width  = getWidth();		
		
		return result;
	}

}

