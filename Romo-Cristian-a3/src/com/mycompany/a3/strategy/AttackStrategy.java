package com.mycompany.a3.strategy;

import com.codename1.util.MathUtil;
import com.mycompany.a3.IStrategy;
import com.mycompany.a3.NonPlayerCyborg;
import com.mycompany.a3.PlayerCyborg;

public class AttackStrategy implements IStrategy {

	private PlayerCyborg playerC;
	private NonPlayerCyborg nonPlayer;

	public AttackStrategy(NonPlayerCyborg npc, PlayerCyborg player) {
		playerC = player;
		nonPlayer = npc;
	}

	@Override
	public void setStrategy() {
		double diffX = 0, diffY = 0;
		diffX = playerC.getLocationX() - nonPlayer.getLocationX();
		diffY = playerC.getLocationY() - nonPlayer.getLocationY();

		int headingAngle = (int) Math.toDegrees(MathUtil.atan(diffX / diffY));

		if (diffY == 0) {
			if (diffX < 0) {
				nonPlayer.setHeading(270);
				return;
			} else if (diffX > 0) {
				nonPlayer.setHeading(90);
				return;
			}
		}
		if (diffX == 0) {
			if (diffY > 0) {
				nonPlayer.setHeading(0);
				return;
			} else if (diffY < 0) {
				nonPlayer.setHeading(180);
				return;
			}
		}

		if (diffX > 0) {
			if (diffY > 0) {
				nonPlayer.setHeading(Math.abs(headingAngle));
				return;
			} else if (diffY < 0) {
				nonPlayer.setHeading(180 - headingAngle);
				return;
			}
		} else if (diffX < 0) {
			if (diffY > 0) {
				nonPlayer.setHeading(360 - headingAngle);
				return;
			} else if (diffY > 0) {
				nonPlayer.setHeading(180 + Math.abs(headingAngle));
			}
		}
			nonPlayer.setSpeed(playerC.getSpeed() + 1);
	}

	@Override
	public void invokeStrategy() {
		setStrategy();
	}

}
