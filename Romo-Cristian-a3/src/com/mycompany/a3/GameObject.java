package com.mycompany.a3;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;

public class GameObject implements IDrawable, ICollider{
	Random rand = new Random();
	/* VARIABLES */

	private int size;
	private int color;
	private Point location;
	

	/* CONSTRUCTOR */

	public GameObject() {
		//location = new Point(randomLocationX(), randomLocationY());
		location = new Point();
	}

	/* METHODS */

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getLocation() {
		return location.getX() + location.getY();
	}

	public double getLocationX() {
		return location.getX();
	}

	public double getLocationY() {
		return location.getY();
	}

	public void setLocation(float x, float y) {
		this.location.setX(x);
		this.location.setY(y);
	}
	
	public void setLocation(Point point) {
		location.setX(point.getX());
		location.setY(point.getY());		
	}
	
	/*public float randomLocationX() {
		int xLocationMax = GameWorld.getWidth();
		int xLocationMin = 0;
		float xLocationRandom = rand.nextInt(xLocationMax - xLocationMin) + xLocationMin;
		return xLocationRandom;
	}

	public float randomLocationY() {
		int yLocationMax = GameWorld.getHeight();
		int yLocationMin = 0;
		float yLocationRandom = rand.nextInt(yLocationMax - yLocationMin) +yLocationMin;
		return yLocationRandom;
	}*/
	
	public int getColor() {
		return color;
	}

	public void setColor(int colorR, int colorG, int colorB) {
		this.color = ColorUtil.rgb(colorR, colorG, colorB);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		//g.drawImage(theImage, pCmpRelPrnt.getX()+currentX, pCmpRelPrnt.getY()+currentY, size, size);
	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}

	public boolean collidesWith(ICollider otherObject) {
		return false;
	}
}
