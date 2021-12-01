package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class Base extends Fixed implements IDrawable, ICollider {

	private static int counter = 1;
	private int sequenceNumber = 1;

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setColor() {

	}

	public Base() {
		if (counter == 1) {
			this.setLocation(200, 400); // location is always fixed
		} else if (counter == 2) {
			this.setLocation(900, 200);
		} else if (counter == 3) {
			this.setLocation(400, 800);
		} else if (counter == 4) {
			this.setLocation(1100, 800);
		}
		this.setColor(230, 100, 80); // set color to a reddish color
		this.setSize(100); // all bases are same size
		sequenceNumber = counter++;
	}

	public String toString() {
		return "Base: " + "loc = " + this.getLocationX() + " , " + this.getLocationY() + " color = ["
				+ ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor())
				+ "] size = " + this.getSize() + " seqNum = " + this.getSequenceNumber();
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (pCmpRelPrnt.getX() + this.getLocationX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocationY());
		g.setColor(getColor());
		int[] x1 = { x - this.getSize() / 2, x + 100 - this.getSize() / 2, x + 50 - this.getSize() / 2 };
		int[] y1 = { y - this.getSize() / 2, y - this.getSize() / 2, y + 95 - this.getSize() / 2 };
		g.drawPolygon(x1, y1, 3);
		g.fillPolygon(x1, y1, 3);

		g.setColor(ColorUtil.BLACK);
		Font f = g.getFont();
		String toWrite = Integer.toString(this.getSequenceNumber());

		int width = f.stringWidth(toWrite);
		int height = f.getHeight();
		g.drawString(toWrite, x - width / 2, y - height / 2);
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
		//gw.cyborgCollidesBase(this.getSequenceNumber());
	}
}
