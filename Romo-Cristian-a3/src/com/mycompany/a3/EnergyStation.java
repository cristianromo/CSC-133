package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class EnergyStation extends Fixed implements IDrawable, ICollider {

	private int energyCapacity;
	@SuppressWarnings("unused")
	private int energySize;
	
	

	public EnergyStation() {
		this.setLocation(rand.nextInt(GameWorld.getWidth()), rand.nextInt(GameWorld.getHeight())); // location is random
		this.setColor(70, 200, 70); // green
		this.setEnergyCapacity(10 + rand.nextInt(40)); // sets size and energy capacity to a random value 10-50
		this.setSize(this.energyCapacity * 2);
	}

	public int getEnergyCapacity() {
		return energyCapacity;
	}

	public void setEnergyCapacity(int energyCapacity) {
		this.energyCapacity = energyCapacity;
	}

	public int getEnergySize() {
		return energySize = energyCapacity;
	}

	public void setEnergySize(int energySize) {
		this.energySize = energySize;
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (pCmpRelPrnt.getX() + this.getLocationX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocationY());
		int startAng = 360, arcAng = 360;
		
		g.setColor(getColor());
		g.drawArc(x - getSize() / 2, y - getSize() / 2, getSize(), getSize(), startAng, arcAng);
		g.fillArc(x - getSize() / 2, y - getSize() / 2, getSize(), getSize(), startAng, arcAng);
		g.setColor(ColorUtil.BLACK);
		
		Font f = g.getFont();
		String toWrite = Integer.toString(this.getEnergyCapacity());

		int width = f.stringWidth(toWrite);
		int height = f.getHeight();
		
		g.drawString(toWrite, x - width / 2, y - height / 2);
	}

	public String toString() {
		return "Energy Station: " + "loc = " + this.getLocationX() + " , " + this.getLocationY() + " color = ["
				+ ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor())
				+ "] size = " + this.getEnergySize() + " capcity = " + this.getEnergyCapacity();
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
		//gw.cyborgCollidesEnergy();
	}

}
