package com.mycompany.a3.views;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IDrawable;
import com.mycompany.a3.IIterator;

public class MapView extends Container implements Observer {
	
	public MapView(GameWorld gw) {
		this.getStyle().setBorder(Border.createLineBorder(10, ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		Container centerContainer = new Container();
		this.add(BorderLayout.CENTER, centerContainer);
	}

	public void update(Observable observable, Object data) {
		this.repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		IIterator theIterator = GameWorld.getGameObjectCollection().getIterator();
		Point pCmpRelPrnt = new Point(getX(), getY());
		while(theIterator.hasNext()) {
			GameObject obj = (GameObject) theIterator.getNext();
			((IDrawable) obj).draw(g, pCmpRelPrnt);
		}
	}
	/*
	 * currentObj = (GameObject) theIterator.getNext(); if (currentObj instanceof
	 * Movable) { int x = (int) ((GameObject) currentObj).getLocationX(); int y =
	 * (int) ((GameObject) currentObj).getLocationY(); int rightWall =
	 * this.getWidth() + this.getX(); int leftWall = this.getX(); int bottomWall =
	 * this.getY() + this.getHeight(); int topWall = this.getY(); if(x<=leftWall ||
	 * x>=rightWall) { if(x<=0) { ((GameObject)currentObj).setLocation(new
	 * Point(this.getWidth(), y)); } if(x>=rightWall) {
	 * ((GameObject)currentObj).setLocation(new Point(0, y)); } } if (y <= topWall
	 * || y >= bottomWall) { if (y <= 0) { ((GameObject) currentObj).setLocation(new
	 * Point(x, bottomWall)); } if ((y + this.getY()) >= bottomWall) { ((GameObject)
	 * currentObj).setLocation(new Point(x, 0)); } } } if (currentObj instanceof
	 * IDrawable) { ((IDrawable) currentObj).draw(g, pCmpRelPrnt); } }
	 */
}