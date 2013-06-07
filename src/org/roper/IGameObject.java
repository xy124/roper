package org.roper;

import java.awt.Graphics;

public interface IGameObject {

	public void update(Graphics g);
	public void quit();
	
	boolean killme = false; 
	//TODO if true object will be removed from parent list in next slope, 
	//maybe find a better solution...



	
}

