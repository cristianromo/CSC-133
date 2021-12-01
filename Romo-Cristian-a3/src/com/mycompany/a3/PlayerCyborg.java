package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class PlayerCyborg extends Cyborg implements IDrawable, ICollider {
	
	private static PlayerCyborg playerCyborg;

	private PlayerCyborg() {
		this.setLocation(100, 100); // location is fixed
		this.setColor(80, 200, 180); // teal
		this.setHeading(0); // starts going north
		this.setSpeed(0); // speed is set to 0 to start
		this.setDamageLevel(0);
		this.setSize(75); // always the same size
		this.setPlayerLife(2); // sets player life to 3
		this.setEnergyConsumption(1);
		this.setEnergyLevel(1000);
	}

	public static PlayerCyborg getCyborg() {
		if (playerCyborg == null) {
			playerCyborg = new PlayerCyborg();
		}
		return playerCyborg;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (pCmpRelPrnt.getX() + this.getLocationX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocationY());
		g.setColor(getColor());
		g.drawRect(x - getSize() / 2, y - getSize() / 2, getSize(), getSize(), 20);
	}

	public String toString() {
		return "Player Cyborg: " + super.toString();
	}

	@Override
	public void handleCollision(GameObject otherObject) {

	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		boolean collides = false;

		int thisCenterX = (int) this.getLocationX() + getSize() / 2;
		int thisCenterY = (int) this.getLocationY() + getSize() / 2;

		int otherCenterX = (int) ((GameObject) otherObject).getLocationX() + (getSize() / 2);
		int otherCenterY = (int) ((GameObject) otherObject).getLocationY() + (getSize() / 2);

		int distanceX = thisCenterX - otherCenterX;
		int distanceY = thisCenterY - otherCenterY;

		int distBetweenCentersSqr = (distanceX * distanceX + distanceY * distanceY);

		int thisRadius = getSize() / 2;
		int otherRadius = ((GameObject) otherObject).getSize() / 2;

		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);

		if (distBetweenCentersSqr <= radiiSqr) {
			collides = true;
		}
		return collides;
	}

}
