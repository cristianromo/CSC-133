package com.mycompany.a3.views;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.a3.GameWorld;

public class ScoreView extends Container implements Observer {

	private Label timeValueLabel;
	private Label livesValueLabel;
	private Label lastBaseValueLabel;
	private Label energyValueLabel;
	private Label damageValueLabel;
	private Label soundValueLabel;

	public ScoreView() {

	}

	public ScoreView(GameWorld gw) {
		// text label
		Label timeTextLabel = new Label("Time:");
		Label livesTextLabel = new Label("Lives Left:");
		Label lastBaseTextLabel = new Label("Last Base Reached:");
		Label energyTextLabel = new Label("Player Energy Level:");
		Label damageTextLabel = new Label("Player Damage Level:");
		Label soundTextLabel = new Label("Sound:");
		// format text
		timeTextLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM));
		livesTextLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM));
		lastBaseTextLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM));
		energyTextLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM));
		damageTextLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM));
		soundTextLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_UNDERLINED, Font.SIZE_MEDIUM));

		// value label
		timeValueLabel = new Label("XXX");
		livesValueLabel = new Label("XX");
		lastBaseValueLabel = new Label("XX");
		energyValueLabel = new Label("XXXX");
		damageValueLabel = new Label("XX");
		soundValueLabel = new Label("XXXX");

		// formating font so it fits in window, and changing color of text
		timeValueLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
		timeValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(245, 65, 110));
		livesValueLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
		livesValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(245, 65, 110));
		lastBaseValueLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
		lastBaseValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(245, 65, 110));
		energyValueLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
		energyValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(245, 65, 110));
		damageValueLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
		damageValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(245, 65, 110));
		soundValueLabel.getAllStyles()
				.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
		soundValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(245, 65, 110));

		Container topContainer = new Container();

		topContainer.add(timeTextLabel);
		topContainer.add(timeValueLabel);
		topContainer.add(livesTextLabel);
		topContainer.add(livesValueLabel);
		topContainer.add(lastBaseTextLabel);
		topContainer.add(lastBaseValueLabel);
		topContainer.add(energyTextLabel);
		topContainer.add(energyValueLabel);
		topContainer.add(damageTextLabel);
		topContainer.add(damageValueLabel);
		topContainer.add(soundTextLabel);
		topContainer.add(soundValueLabel);

		this.setLayout(new FlowLayout(CENTER));

		this.add(topContainer);
	}

	@Override
	public void update(Observable observable, Object data) {
		GameWorld go = (GameWorld) observable;
		//GameWorld gw = (GameWorld) data;

		timeValueLabel.setText(Integer.toString(go.getClock()));
		livesValueLabel.setText(Integer.toString(go.getPlayerLives()));
		lastBaseValueLabel.setText(Integer.toString(go.getLastBaseReached()));
		energyValueLabel.setText(Integer.toString(go.getEnergyLevel()));
		damageValueLabel.setText(Integer.toString(go.getDamageLevel()));

		if (go.getSound() == true) {
			soundValueLabel.setText("ON");
		} else {
			soundValueLabel.setText("OFF");
		}
		repaint();
	}
}
