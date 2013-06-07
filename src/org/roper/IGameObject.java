package org.roper;

public interface IGameObject {

	public void update();
	public void quit();
	
	boolean killme = false; 
	//if true object will be removed from parent list in next slope

	
}

