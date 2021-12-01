package com.mycompany.a3;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.commands.*;
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.ScoreView;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;

public class Game extends Form implements Runnable {
	private MapView mv;
	private ScoreView sv;
	private GameWorld gw;
	private static int time = 100;
	private boolean isSelected = false;
	UITimer timer = new UITimer(this);

	public Game() {
		// create collection
		// theCollection = new GameObjectCollection();
		// slide 36 DESIGN
		gw = new GameWorld(); // creates an observable
		mv = new MapView(gw); // create an observer that registers itself
		sv = new ScoreView(gw); // create another observer

		gw.addObserver(mv);
		gw.addObserver(sv); // register the observer

		setLayout(new BorderLayout());

		/* --------------------------CONTAINERS-------------------------- */

		// creates contianer for left side of screen
		Container leftContainer = new Container();
		leftContainer.setUIID("left_container");
		leftContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getStyle().setBgColor(ColorUtil.LTGRAY);
		leftContainer.getStyle().setBgTransparency(255);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.LTGRAY));
		// leftContainer.add(new Label ("commands"));

		// creates contianer for left side of screen
		Container rightContainer = new Container();
		rightContainer.setUIID("right_container");
		rightContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getStyle().setBgColor(ColorUtil.LTGRAY);
		rightContainer.getStyle().setBgTransparency(255);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.LTGRAY));
		// rightContainer.add(new Label ("commands"));

		// creates contianer for bottom side of screen
		Container botContainer = new Container(BoxLayout.xCenter());
		botContainer.setUIID("bot_container");
		botContainer.getStyle().setBgColor(ColorUtil.LTGRAY);
		botContainer.getStyle().setBgTransparency(255);

		Container centerContainer = new Container();
		centerContainer.getAllStyles().setBgTransparency(255);

		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		myToolbar.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		myToolbar.getStyle().setBgColor(ColorUtil.LTGRAY);
		myToolbar.getStyle().setBgTransparency(255);
		myToolbar.setTitle(" Sili-Challenge Game ");

		/* --------------------------BUTTONS-------------------------- */

		Button accelerate = new Button();
		accelerate.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		accelerate.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		accelerate.getSelectedStyle().setBgTransparency(180);
		accelerate.getAllStyles().setBgColor(ColorUtil.rgb(120, 155, 60));
		accelerate.getAllStyles().setBorder(Border.createDashedBorder(5));
		accelerate.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
		accelerate.getAllStyles().setBgTransparency(180);
		accelerate.getAllStyles().setPadding(TOP, 6);
		accelerate.getAllStyles().setPadding(BOTTOM, 6);
		// command
		AccelerateCommand accelCmd = new AccelerateCommand(gw);
		accelerate.setCommand(accelCmd);
		leftContainer.add(accelerate);

		Button brake = new Button("brake");
		brake.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		brake.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		brake.getSelectedStyle().setBgTransparency(180);
		brake.getAllStyles().setBgColor(ColorUtil.rgb(205, 25, 50));
		brake.getAllStyles().setBorder(Border.createDashedBorder(5));
		brake.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
		brake.getAllStyles().setBgTransparency(180);
		brake.getAllStyles().setPadding(TOP, 6);
		brake.getAllStyles().setPadding(BOTTOM, 6);
		brake.getAllStyles().setPadding(LEFT, 7);
		brake.getAllStyles().setPadding(RIGHT, 7);
		// command
		BrakeCommand brakeCmd = new BrakeCommand(gw);
		brake.setCommand(brakeCmd);
		rightContainer.add(brake);

		Button left = new Button("turn left");
		left.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		left.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		left.getSelectedStyle().setBgTransparency(180);
		left.getAllStyles().setBgColor(ColorUtil.rgb(250, 85, 10));
		left.getAllStyles().setBorder(Border.createDashedBorder(5));
		left.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
		left.getAllStyles().setBgTransparency(180);
		left.getAllStyles().setPadding(TOP, 6);
		left.getAllStyles().setPadding(BOTTOM, 6);
		// command
		LeftTurnCommand leftCmd = new LeftTurnCommand(gw);
		left.setCommand(leftCmd);
		leftContainer.add(left);

		Button right = new Button("turn right");
		right.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		right.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		right.getSelectedStyle().setBgTransparency(180);
		right.getAllStyles().setBgColor(ColorUtil.rgb(250, 85, 10));
		right.getAllStyles().setBorder(Border.createDashedBorder(5));
		right.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
		right.getAllStyles().setBgTransparency(180);
		right.getAllStyles().setPadding(TOP, 6);
		right.getAllStyles().setPadding(BOTTOM, 6);
		right.getAllStyles().setPadding(LEFT, 6);
		right.getAllStyles().setPadding(RIGHT, 6);
		// command
		RightTurnCommand rightCmd = new RightTurnCommand(gw);
		right.setCommand(rightCmd);
		rightContainer.add(right);

		Button changeS = new Button("change strategy");
		changeS.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		changeS.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		changeS.getSelectedStyle().setBgTransparency(180);
		changeS.getAllStyles().setBgColor(ColorUtil.rgb(15, 120, 220));
		changeS.getAllStyles().setBorder(Border.createDashedBorder(5));
		changeS.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE));
		changeS.getAllStyles().setBgTransparency(180);
		changeS.getAllStyles().setPadding(TOP, 4);
		changeS.getAllStyles().setPadding(BOTTOM, 4);
		// command
		StrategyCommand stratCmd = new StrategyCommand(gw);
		changeS.setCommand(stratCmd);
		leftContainer.add(changeS);

		/*
		 * Button collidesNPC = new Button("collides with NPC");
		 * collidesNPC.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		 * collidesNPC.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		 * collidesNPC.getSelectedStyle().setBgTransparency(180);
		 * collidesNPC.getAllStyles().setBgColor(ColorUtil.rgb(205, 115, 115));
		 * collidesNPC.getAllStyles().setBorder(Border.createDashedBorder(5));
		 * collidesNPC.getAllStyles()
		 * .setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD,
		 * Font.SIZE_MEDIUM)); collidesNPC.getAllStyles().setBgTransparency(180);
		 * collidesNPC.getAllStyles().setPadding(TOP, 4);
		 * collidesNPC.getAllStyles().setPadding(BOTTOM, 4); // command
		 * CollideNPCCommand NPCCmd = new CollideNPCCommand(gw);
		 * collidesNPC.setCommand(NPCCmd); botContainer.add(collidesNPC);
		 * 
		 * Button collidesBase = new Button("collides with Base");
		 * collidesBase.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		 * collidesBase.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		 * collidesBase.getSelectedStyle().setBgTransparency(180);
		 * collidesBase.getAllStyles().setBgColor(ColorUtil.rgb(235, 100, 130));
		 * collidesBase.getAllStyles().setBorder(Border.createDashedBorder(5));
		 * collidesBase.getAllStyles()
		 * .setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD,
		 * Font.SIZE_MEDIUM)); collidesBase.getAllStyles().setBgTransparency(180);
		 * collidesBase.getAllStyles().setPadding(TOP, 4);
		 * collidesBase.getAllStyles().setPadding(BOTTOM, 4); // command
		 * CollideBaseCommand BaseCmd = new CollideBaseCommand(gw);
		 * collidesBase.setCommand(BaseCmd); botContainer.add(collidesBase);
		 * 
		 * Button collidesES = new Button("collides with EnergyStation");
		 * collidesES.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		 * collidesES.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		 * collidesBase.getSelectedStyle().setBgTransparency(180);
		 * collidesES.getAllStyles().setBgColor(ColorUtil.rgb(230, 110, 165));
		 * collidesES.getAllStyles().setBorder(Border.createDashedBorder(5));
		 * collidesES.getAllStyles() .setFont(Font.createSystemFont(Font.FACE_MONOSPACE,
		 * Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		 * collidesES.getAllStyles().setBgTransparency(180);
		 * collidesES.getAllStyles().setPadding(TOP, 4);
		 * collidesES.getAllStyles().setPadding(BOTTOM, 4); // command CollideESCommand
		 * esCmd = new CollideESCommand(gw); collidesES.setCommand(esCmd);
		 * botContainer.add(collidesES);
		 * 
		 * Button collidesDrone = new Button("collides with Drone");
		 * collidesDrone.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		 * collidesDrone.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		 * collidesBase.getSelectedStyle().setBgTransparency(180);
		 * collidesDrone.getAllStyles().setBgColor(ColorUtil.rgb(180, 110, 185));
		 * collidesDrone.getAllStyles().setBorder(Border.createDashedBorder(5));
		 * collidesDrone.getAllStyles()
		 * .setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD,
		 * Font.SIZE_MEDIUM)); collidesDrone.getAllStyles().setBgTransparency(180);
		 * collidesDrone.getAllStyles().setPadding(TOP, 4);
		 * collidesDrone.getAllStyles().setPadding(BOTTOM, 4); // command
		 * CollideDroneCommand droneCmd = new CollideDroneCommand(gw);
		 * collidesDrone.setCommand(droneCmd); botContainer.add(collidesDrone);
		 */

		Button position = new Button("position");
		position.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		position.getAllStyles().setBgTransparency(180);
		position.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		position.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		position.getAllStyles().setBorder(Border.createDashedBorder(5));
		position.getAllStyles().setBgColor(ColorUtil.rgb(180, 110, 185));
		position.getAllStyles().setPadding(TOP, 4);
		position.getAllStyles().setPadding(BOTTOM, 4);
		PositionCommand positionCmd = new PositionCommand(this);
		position.setCommand(positionCmd);
		botContainer.add(position);

		Button pause = new Button("pause");
		pause.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		pause.getAllStyles().setBgTransparency(180);
		pause.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		pause.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		pause.getAllStyles().setBorder(Border.createDashedBorder(5));
		pause.getAllStyles().setBgColor(ColorUtil.rgb(105, 100, 185));
		pause.getAllStyles().setPadding(TOP, 4);
		pause.getAllStyles().setPadding(BOTTOM, 4);
		PauseCommand pauseCmd = new PauseCommand(this);
		pause.setCommand(pauseCmd);
		botContainer.add(pause);

		// tick.getUnselectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		// tick.getSelectedStyle().setFgColor(ColorUtil.BLACK);
		// tick.getSelectedStyle().setBgTransparency(180);
		// tick.getAllStyles().setBgColor(ColorUtil.rgb(105, 100, 185));
		// tick.getAllStyles().setBorder(Border.createDashedBorder(5));
		// tick.getAllStyles().setFont(Font.createSystemFont(Font.FACE_MONOSPACE,
		// Font.STYLE_BOLD, Font.SIZE_MEDIUM));
		// tick.getAllStyles().setBgTransparency(180);
		// tick.getAllStyles().setPadding(TOP, 4);
		// tick.getAllStyles().setPadding(BOTTOM, 4);
		// command
		// TickCommand tickCmd = new TickCommand(gw);
		// tick.setCommand(tickCmd);

		/* --------------------- SIDE MENU --------------------- */
		CheckBox soundBox = new CheckBox();
		SoundCommand soundCmd = new SoundCommand(gw);
		soundBox.setSelected(false);
		soundBox.setCommand(soundCmd);
		myToolbar.addComponentToSideMenu(soundBox);

		myToolbar.addCommandToSideMenu(accelCmd);

		ExitCommand exitCmd = new ExitCommand(gw);
		myToolbar.addCommandToSideMenu(exitCmd);

		AboutCommand abtCmd = new AboutCommand(gw);
		myToolbar.addCommandToSideMenu(abtCmd);

		HelpCommand helpCmd = new HelpCommand(gw);
		myToolbar.addCommandToRightBar(helpCmd);

		/* --------------------- KEY BINDINGS --------------------- */
		addKeyListener('a', accelCmd);
		addKeyListener('b', brakeCmd);
		addKeyListener('l', leftCmd);
		addKeyListener('r', rightCmd);
		// addKeyListener('e', esCmd);
		// addKeyListener('g', droneCmd);
		// addKeyListener('t', tickCmd);
		MapCommand mapCmd = new MapCommand(gw);
		addKeyListener('m', mapCmd);

		add(BorderLayout.NORTH, sv);
		add(BorderLayout.CENTER, mv);
		add(BorderLayout.SOUTH, botContainer);
		add(BorderLayout.EAST, rightContainer);
		add(BorderLayout.WEST, leftContainer);

		if (gw.isPaused() == true) {
			position.setEnabled(true);
			accelerate.setEnabled(false);
			brake.setEnabled(false);
			left.setEnabled(false);
			right.setEnabled(false);
			changeS.setEnabled(false);
		} else {
			position.setEnabled(false);
			accelerate.setEnabled(true);
			brake.setEnabled(true);
			left.setEnabled(true);
			right.setEnabled(true);
			changeS.setEnabled(true);
		}

		this.show();
		gw.setDimension((int) mv.getWidth(), (int) mv.getHeight());

		mv.setWidth(mv.getWidth());
		mv.setHeight(mv.getHeight());

		gw.createSounds();
		gw.init();

		timer.schedule(getTime(), true, this);

	}

	@Override
	public void run() {
		gw.tick(getTime());
		mv.repaint();
	}

	public void pause() {
		if (isSelected == true) {
			gw.setPaused(true);
			timer.cancel();
			isSelected = false;
			gw.setSound(false);
		} else {
			timer.schedule(getTime(), true, this);
			isSelected = true;
			gw.setPaused(false);
		}
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		Game.time = time;
	}

	/*
	 * @SuppressWarnings("rawtypes") private void play() { Label myLabel = new
	 * Label("Enter a Command:"); this.addComponent(myLabel); final TextField
	 * myTextField = new TextField(); this.addComponent(myTextField); this.show();
	 * myTextField.addActionListener(new ActionListener() {
	 * 
	 * public void actionPerformed(ActionEvent evt) { String sCommand =
	 * myTextField.getText().toString(); myTextField.clear(); if (sCommand.length()
	 * != 0) switch (sCommand.charAt(0)) { case 'a': gw.accelerate(); confirm = 0;
	 * break; case 'b': gw.brake(); confirm = 0; break; case 'l': gw.turnLeft();
	 * confirm = 0; break; case 'r': gw.turnRight(); confirm = 0; break; case 'c':
	 * gw.cyborgCollidesCyborg(); confirm = 0; break; case '1': case '2': case '3':
	 * case '4': case '5': case '6': case '7': case '8': case '9':
	 * gw.cyborgCollidesBase(sCommand); confirm = 0; break; case 'e':
	 * gw.cyborgCollidesEnergy(); confirm = 0; break; case 'g':
	 * gw.droneCollidesCyborg(); confirm = 0; break; case 't': gw.tick(); confirm =
	 * 0; break; case 'd': gw.display(); confirm = 0; break; case 'm': gw.map();
	 * confirm = 0; break; case 'x': gw.exit(); confirm = 1; break; case 'y': if
	 * (confirm == 1) gw.confirm(); break; case 'n': if (confirm == 1)
	 * gw.dontConfirm(); break; default:
	 * System.out.println("Please enter a valid character or 'x' to exit"); } //
	 * switch } // actionPerformed } // new ActionListener() ); // addActionListener
	 * } // play
	 */
}
