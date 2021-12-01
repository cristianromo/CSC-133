package com.mycompany.a3.strategy;

import com.codename1.util.MathUtil;
import com.mycompany.a3.Base;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IIterator;
import com.mycompany.a3.IStrategy;
import com.mycompany.a3.NonPlayerCyborg;

public class RaceStrategy implements IStrategy {

	private NonPlayerCyborg npc;
	private GameWorld gw;

	public RaceStrategy(NonPlayerCyborg npCyborg, GameWorld gwc) {
		this.npc = npCyborg;
		this.gw = gwc;
	}
	public RaceStrategy() {
		//npc = npCyborg;
		//gw = gwc;
	}


	@Override
	public void setStrategy() {
		double bX = 0, bY = 0;
		double diffX = 0, diffY = 0;
		int nextBase = npc.getLastBaseReached() + 1;
		IIterator theCollection = GameWorld.getGameObjectCollection().getIterator();
		Object currentObj;
		while (theCollection.hasNext()) {
			currentObj = theCollection.getNext();
			if (currentObj instanceof Base) {
				if (((Base) currentObj).getSequenceNumber() == nextBase) {
					bX = ((Base) currentObj).getLocationX();
					bY = ((Base) currentObj).getLocationX();
				}
			}
		}
		diffX = bX - npc.getLocationX();
		diffY = bY - npc.getLocationY();

		int headingAngle = (int) Math.toDegrees(MathUtil.atan(diffX / diffY));

		if (diffY == 0) {
			if (diffX < 0) {
				npc.setHeading(270);
				return;
			} else if (diffX > 0) {
				npc.setHeading(90);
				return;
			}
		}
		if (diffX == 0) {
			if (diffY > 0) {
				npc.setHeading(0);
				return;
			} else if (diffY < 0) {
				npc.setHeading(180);
				return;
			}
		}

		if (diffX > 0) {
			if (diffY > 0) {
				npc.setHeading(Math.abs(headingAngle));
				return;
			} else if (diffY < 0) {
				npc.setHeading(180 - headingAngle);
				return;
			}
		} else if (diffX < 0) {
			if (diffY > 0) {
				npc.setHeading(360 - headingAngle);
				return;
			} else if (diffY < 0) {
				npc.setHeading(180 + Math.abs(headingAngle));
				return;
			}
		}
		npc.setLastBaseReached(npc.getLastBaseReached() + 1);
	}
	/*
	 * if (gw.getGameObjectCollection().elementAt(i) instanceof Cyborg) {
	 * while(gameObjects.hasNext()) { curr } } for (int i = 0; i <
	 * getGameObjectCollection().getSize(); i++) { // goes through each object in
	 * worldVector if (getGameObjectCollection().elementAt(i) instanceof
	 * PlayerCyborg) { npc.setSpeed(player.getSpeed() + 5); if
	 * (player.getLastBaseReached() == 1) { if (base.getSequenceNumber() == 2)
	 * npc.setHeading(Math.toDegrees((MathUtil.atan( (base.getLocationY() -
	 * npc.getLocationY()) / (base.getLocationX() - npc.getLocationX()))))); } else
	 * if (player.getLastBaseReached() == 2) { if (base.getSequenceNumber() == 3)
	 * npc.setHeading(Math.toDegrees((MathUtil.atan( (base.getLocationY() -
	 * npc.getLocationY()) / (base.getLocationX() - npc.getLocationX()))))); } else
	 * if (player.getLastBaseReached() == 3) { if (base.getSequenceNumber() == 4)
	 * npc.setHeading(Math.toDegrees((MathUtil.atan( (base.getLocationY() -
	 * npc.getLocationY()) / (base.getLocationX() - npc.getLocationX()))))); }
	 */

	@Override
	public void invokeStrategy() {
		setStrategy();
	}

}