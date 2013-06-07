package org.roper;

public class Rect {
	int width = 0;
	int height = 0;
	
	
	public Rect(int h, int w) {
		height = h;
		width = w;
	}
	public Rect() {
		
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
		
}
