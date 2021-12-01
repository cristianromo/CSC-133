package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.strategy.AttackStrategy;
import com.mycompany.a3.strategy.RaceStrategy;

public class NonPlayerCyborg extends Cyborg implements IDrawable, ICollider {

	private String stratName;
	private IStrategy curStrategy;
	private Game g;

	public NonPlayerCyborg() {
		this.setLocation(rand.nextInt(GameWorld.getWidth()), rand.nextInt(GameWorld.getHeight())); // sets to around 100,100
		this.setColor(200, 200, 200);
		this.setSpeed(1);
		this.setSize(50);
		this.setMaximumSpeed(50);
		this.setHeading(rand.nextInt(360));
		this.setEnergyLevel(1000);
		this.setEnergyConsumption(0);
		this.setDamageLevel(0);
		this.setLastBaseReached(0);
	}
	
	@Override
	public void move(int time) {
		curStrategy.invokeStrategy();
		super.move(g.getTime());
	}

	public void setStrategy(IStrategy s) {
		curStrategy = s;
	}

	public void invokeStrategy() {
		curStrategy.invokeStrategy();
	}

	public IStrategy getStrategy() {
		return curStrategy;
	}

	public String getStrat() {
		return stratName;
	}

	public void setStrat(String strat) {
		this.stratName = strat;
	}

	public String toString() {
		return "Non Player Cyborg: " + super.toString() + " current strat = " + getStrat();
	}

	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (pCmpRelPrnt.getX() + this.getLocationX());
		int y = (int) (pCmpRelPrnt.getY() + this.getLocationY());
		g.setColor(getColor());
		g.drawRect(x - getSize() / 2, y - getSize() / 2, getSize(), getSize(), 15);
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
		//gw.cyborgCollidesCyborg();
	}
}
