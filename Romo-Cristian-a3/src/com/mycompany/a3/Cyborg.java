package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public abstract class Cyborg extends Movable implements ISteerable {

	/* VARIABLES */

	private int steeringDirection = 0; // allowed to be changed from 5-40 (by 5)
	private float maximumSpeed = 50; // speed
	private int energyLevel; // health
	private int energyConsumption = 1;
	private int damageLevel; // max 5
	private int lastBaseReached = 0; // updates when loaction of cyborg == location of base
	private int playerLife; // 3 lives

	/* GETTERS & SETTERS */

	public int getDamageLevel() {
		return damageLevel;
	}

	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel - this.getEnergyConsumption();
	}

	public int getEnergyConsumption() {
		return energyConsumption;
	}

	public void setEnergyConsumption(int energyConsumption) {
		this.energyConsumption = (int) (this.getSpeed() / 5);
	}

	public int getPlayerLife() {
		return playerLife;
	}

	public void setPlayerLife(int playerLife) {
		this.playerLife = playerLife;
	}

	public int getSteeringDirection() {
		return steeringDirection;
	}

	public void setSteeringDirection(int steeringDirection) { // sets steering direction to 5-40 by 5
		if (steeringDirection < 40 || steeringDirection > -40)
			this.steeringDirection = steeringDirection;
		else
			this.steeringDirection = 40;
	}

	public int getLastBaseReached() {
		return lastBaseReached;
	}

	public void setLastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
	}

	public float getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(float f) {
		this.maximumSpeed = f;
	}

	@Override
	public void turnLeft() {
		if (this.getSteeringDirection() > -40) {
			this.setSteeringDirection(this.getSteeringDirection() - 5);
			
		}
		else
			System.out.println("Max turn reached");
	}

	// -40 < x < 40
	@Override
	public void turnRight() {
		if (this.getSteeringDirection() < 40) {
			this.setSteeringDirection(this.getSteeringDirection() + 5);
		}
		else
			System.out.println("Max turn reached");
	}

	public String toString() {
		return " loc =  " + this.getLocationX() + " , " + this.getLocationY() + " color = [" + ColorUtil.red(getColor())
				+ "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + "] heading = "
				+ this.getHeading() + " speed = " + this.getSpeed() + " size = " + this.getSize()
				+ " \n       maxSpeed = " + this.getMaximumSpeed() + " steeringDirection = "
				+ this.getSteeringDirection() + " energyLevel = " + this.getEnergyLevel() + " damageLevel = "
				+ this.getDamageLevel();
	}
	/*
	 * public static void main(String[] args) { Cyborg c = new Cyborg();
	 * c.setColor(20, 20, 20); System.out.println(c.getColor()); }
	 */
}
