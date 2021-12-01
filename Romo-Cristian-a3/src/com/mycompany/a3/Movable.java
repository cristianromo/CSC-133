package com.mycompany.a3;

public abstract class Movable extends GameObject {

	private int heading;
	private float speed;

	public void move(int elapsedTime) {
		int theta = 90 - heading;
		double deltaX = Math.round(Math.cos(Math.toRadians(theta)) * speed* elapsedTime/100) ; //* (elapsedTime*100)/1000
		double deltaY = Math.round(Math.sin(Math.toRadians(theta)) * speed* elapsedTime/100) ; //* (elapsedTime*100)/1000	

		if ((deltaX + getLocationX() >  GameWorld.getWidth())  || (this.getSize() + deltaX + getLocationX() < 0)){
			deltaX = -deltaX;
			setHeading(360-getHeading());
		}

		if ((deltaY + getLocationY()> GameWorld.getHeight()) || (this.getSize() + deltaY + getLocationY() < 0)){
			deltaY = -deltaY;
			setHeading(180-getHeading());
		}
		
		if(getLocationX()==0){
			deltaX = -deltaX;
			setHeading(360-getHeading());
		}
		
		if(getLocationY()==0){
			deltaY = -deltaY;
			setHeading(180-getHeading());
		}
		
		this.setLocation((float) this.getLocationX() + (float) deltaX, (float) this.getLocationY() + (float) deltaY);
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		if(heading <= 0 || heading >= 360) {
			this.heading = Math.abs(heading % 360);
		}
		else
			this.heading=heading;
	}
	
	public void setHeading(double heading) {
		this.heading = (int) heading;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float f) {
		this.speed = f;
	}

}
