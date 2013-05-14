package org.roper;

import java.awt.Image;

public class Player implements IGameObject {
	Image sprite;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		visible = true;
		ebengebaut=true;
		size = (int) (Math.random()*3);
		left=((int) (Math.random()*2))*(width)-width;
		top=(int) (Math.random());			
						
		direction=Math.random()*Math.PI;
		if (left>0) {//rückwärts!
			direction -= Math.PI;  
		}
		//System.out.println(direction);
		speed=(int) (Math.random()*7+5);
		//System.out.println(speed);
		//System.out.println("size:"+size);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}
	
	//TODO mnach das anders!!!
	public Image ani;		
	public int left, top, width, height;
	public int size;
	public int speed;		
	public double direction;		
	boolean visible;
	boolean ebengebaut;
	

	
	
	public boolean punktinhuhn(int b, int a) {
		if ((top<a)&&((top+height)>a) && (left<b)&&((left+width)>b)) {
			return true;		
		} else {
			return false;
		}
	}
}


