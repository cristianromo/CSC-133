package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Drone extends Movable implements IDrawable, ICollider {

	private GameWorld gw;

	public Drone() {
		this.setLocation(rand.nextInt(GameWorld.getWidth()), rand.nextInt(GameWorld.getHeight()));
		this.setColor(112, 128, 144); // slate grey
		this.setHeading(rand.nextInt(360)); // set to a random value
		this.setSpeed(1); // set speed to a small reasonable value
		this.setSize(60);
	}

	public String toString() {
		return "Drone:" + " loc = " + this.getLocationX() + " , " + this.getLocationY() + " color = ["
				+ ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor())
				+ "] heading = " + this.getHeading() + " speed = " + this.getSpeed();
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (pCmpRelPrnt.getX() + this.getLocationX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocationY());
		g.setColor(getColor());
		int [] x1 = {x,x+80,x+40};
		int [] y1 = {y,y,y+75};
		g.drawPolygon(x1, y1, 3);
		g.fillPolygon(x1, y1, 3);
	}
	
	@Override
	public boolean collidesWith(ICollider otherObject) 
	{
		boolean collides = false;

        int thisCenterX = (int) this.getLocationX() + getSize() / 2;
        int thisCenterY = (int) this.getLocationY() + getSize() / 2;

        int otherCenterX = (int) ((GameObject)otherObject).getLocationX() + (getSize() / 2);
        int otherCenterY = (int) ((GameObject)otherObject).getLocationY() + (getSize() / 2);


        int distanceX = thisCenterX - otherCenterX;
        int distanceY = thisCenterY - otherCenterY;

        int distBetweenCentersSqr = (distanceX * distanceX + distanceY * distanceY);

        int thisRadius  = getSize() / 2;
        int otherRadius = ((GameObject)otherObject).getSize() / 2;

        int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);

        if(distBetweenCentersSqr <= radiiSqr) 
        {
            collides = true;
        }
        return collides;
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
		//gw.droneCollidesCyborg();
	}

}
