package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.mycompany.a3.sounds.BGSound;
import com.mycompany.a3.sounds.Sound;
import com.mycompany.a3.strategy.AttackStrategy;
import com.mycompany.a3.strategy.RaceStrategy;

public class GameWorld extends Observable {

	private static GameObjectCollection gameObjectCollection;
	private PlayerCyborg playerCyborg = PlayerCyborg.getCyborg();
	private int clock = 0;
	private boolean sound = false;
	private Sound cyborgCollision, esCollision, loseLife;
	private BGSound background;
	private static int height = 1000; // set to a random value just so it is not initialized to 0
	private static int width = 1000; // later both get set to the actual mapview value
	private boolean isPaused;

	private ArrayList<GameObject> collisionVector = new ArrayList<GameObject>();

	/*
	 * private Vector<GameObject> theWorldVector = new Vector<GameObject>(); Base b1
	 * = new Base(); Base b2 = new Base(); Base b3 = new Base(); Base b4 = new
	 * Base(); Cyborg cyborg = new PlayerCyborg(); Drone drone1 = new Drone(); Drone
	 * drone2 = new Drone(); EnergyStation eg1 = new EnergyStation(); EnergyStation
	 * eg2 = new EnergyStation();
	 */

	public GameWorld() {
		// create the collection
		setGameObjectCollection(new GameObjectCollection());
	}

	public void init() {
		// add some objects to the collection
		// gameObjectCollection.add(new GameObject("Base1"));

		getGameObjectCollection().add(new Base());
		getGameObjectCollection().add(new Base());
		getGameObjectCollection().add(new Base());
		getGameObjectCollection().add(new Base());
		getGameObjectCollection().add(playerCyborg);

		NonPlayerCyborg cyborg1 = new NonPlayerCyborg();
		cyborg1.setStrategy(new AttackStrategy(cyborg1, playerCyborg));

		getGameObjectCollection().add(cyborg1);

		NonPlayerCyborg cyborg2 = new NonPlayerCyborg();

		cyborg2.setStrategy(new RaceStrategy(cyborg2, this));
		getGameObjectCollection().add(cyborg2);

		NonPlayerCyborg cyborg3 = new NonPlayerCyborg();

		cyborg3.setStrategy(new AttackStrategy(cyborg3, playerCyborg));
		getGameObjectCollection().add(cyborg3);

		cyborg1.setStrat("Attack");
		cyborg2.setStrat("Race");
		cyborg3.setStrat("Attack");

		getGameObjectCollection().add(new Drone());
		getGameObjectCollection().add(new Drone());
		getGameObjectCollection().add(new EnergyStation());
		getGameObjectCollection().add(new EnergyStation());
	}

	// m
	public void displayCollection() {
		for (int i = 0; i < getGameObjectCollection().getObjects().size(); i++) {
			System.out.println(getGameObjectCollection().getObjects().elementAt(i));
		}
	}

	/*
	 * Method init() is responsible for creating the initial state of the world.
	 * This should include adding to the game world at least the following: a
	 * minimum of four Base objects, positioned and sized as you choose and numbered
	 * sequentially defining the track (you may add more than four initial bases if
	 * you like - maximum number of bases you can add is nine); one Cyborg,
	 * initially positioned at the base #1 with initial heading and steering
	 * direction of zero, initial positive non-zero speed, and initial size as you
	 * choose; at least two Drone objects, randomly positioned with a
	 * randomly-generated heading and speed; and at least two EnergyStation objects
	 * with random location and with random sizes.
	 */

	/*
	 * public void init() { theWorldVector.add(b1); theWorldVector.add(b2);
	 * theWorldVector.add(b3); theWorldVector.add(b4); theWorldVector.add(cyborg);
	 * theWorldVector.add(drone1); theWorldVector.add(drone2);
	 * theWorldVector.add(eg1); theWorldVector.add(eg2); }
	 */

	// a
	public void accelerate() {
		if (playerCyborg instanceof PlayerCyborg) {
			if (playerCyborg.getSpeed() < 50) {
				playerCyborg.setSpeed((int) playerCyborg.getSpeed() + 5); // sets speed and compounds it
				playerCyborg.setEnergyConsumption(playerCyborg.getEnergyConsumption() + 1);// adds to energy consumption
				System.out.println("Speed increased");
			} else
				System.out.println("Max speed reached");
			// setChanged();
		}
		setChanged();
		notifyObservers(this);
	}

	// b
	public void brake() {
		if (playerCyborg instanceof PlayerCyborg) {
			if (playerCyborg.getSpeed() > 0) {
				playerCyborg.setSpeed((int) playerCyborg.getSpeed() - 5); // sets speed to slow down
				playerCyborg.setEnergyConsumption(playerCyborg.getEnergyConsumption() - 1); // sets energy consumption
																							// to match
				System.out.println("Speed decreased");
			} else if (playerCyborg.getSpeed() <= 0) {
				System.out.println("Speed at 0, unable to decrease");
			}
			// speed
		}
		setChanged();
		notifyObservers(this);
	}

	// l
	public void turnLeft() {
		if (playerCyborg instanceof Cyborg) {
			playerCyborg.turnLeft();
			System.out.println("Turning left");
		}
		setChanged();
		notifyObservers(this);
	}

	// r
	public void turnRight() {
		if (playerCyborg instanceof Cyborg) {
			playerCyborg.turnRight();
			System.out.println("Turning right");
		}
		setChanged();
		notifyObservers(this);
	}

	// c ** DONE **
	public void cyborgCollidesCyborg() {
		IIterator theIterator = getGameObjectCollection().getIterator();
		GameObject currentObj = new GameObject();
		while (theIterator.hasNext()) {
			currentObj = (GameObject) theIterator.getNext(); // goes through each object in worldVector
			if (currentObj instanceof PlayerCyborg) { // checks to see if there is a cyborg
				playerCyborg.setDamageLevel(playerCyborg.getDamageLevel() + 1); // increases damage level
				playerCyborg.setSpeed(playerCyborg.getSpeed() - 10); // speed starts at 50 and goes to 0, decreases 10
																		// at a time
				playerCyborg.setColor(ColorUtil.red(playerCyborg.getColor()) + 28,
						ColorUtil.green(playerCyborg.getColor()) + 10, ColorUtil.blue(playerCyborg.getColor()) - 8); // changes
																														// color
																														// lighter
																														// and
																														// lighter
				break;
			}
			System.out.println("Cyborg collided with NonPLayer Cyborg");
		}
		if (sound) {
			cyborgCollision.play();
		}
		reset();
		setChanged();
		notifyObservers(this);
	}

	/*
	 * tell the game world that this collision has occurred. The effect of moving
	 * over a base is to check to see whether the number x is exactly one greater
	 * than the base indicated by lastBaseReached field of the cyborg and if so to
	 * record in the cyborg the fact that the cyborg has now reached the next
	 * sequential base on the track (update the lastBaseReached field of the
	 * cyborg).
	 */
	// #1-9
	public void cyborgCollidesBase(int nextBase) {
		// int s = Integer.parseInt(size);
		IIterator theIterator = getGameObjectCollection().getIterator();
		GameObject currentObj = new GameObject();
		while (theIterator.hasNext()) {
			currentObj = (GameObject) theIterator.getNext(); // goes through each object in worldVector
			if (currentObj instanceof Base) { // checks if it is a base
				if (nextBase == playerCyborg.getLastBaseReached() + 1) { // checks the input int to see if
					// it equals what the next base is
					if (playerCyborg.getLastBaseReached() + 1 == ((Base) currentObj).getSequenceNumber()) { // checks if
																											// there is
																											// a
																											// base that
																											// is
																											// next
						playerCyborg.setLastBaseReached(playerCyborg.getLastBaseReached() + 1);
						System.out.println("Cyborg reached with base " + ((Base) currentObj).getSequenceNumber());
						break;
					}
				}
			}
		}
		reset();
		setChanged();
		notifyObservers(this);
	}
	// }

	/*
	 * The effect of colliding an energy station is to increase the cyborg’s energy
	 * level by the capacity of the energy station (in this version of the
	 * assignment, pick a non-empty energy station randomly), reduce the capacity of
	 * the energy station to zero, fade the color of the energy station (e.g.,
	 * change it to light green), and add a new energy station with
	 * randomly-specified size and location into the game.
	 */

	// e **DONE**
	public void cyborgCollidesEnergy() {
		IIterator theIterator = getGameObjectCollection().getIterator();
		GameObject currentObj = new GameObject();
		while (theIterator.hasNext()) {
			currentObj = (GameObject) theIterator.getNext(); // goes through each object in worldVector
			if (currentObj instanceof EnergyStation) { // checks if the object is an
														// energyStation
				if ((((EnergyStation) currentObj).getEnergyCapacity()) != 0) { // checks if
																				// the
					// energyStation is not
					// 0
					playerCyborg.setEnergyLevel(
							playerCyborg.getEnergyLevel() + (((EnergyStation) currentObj).getEnergyCapacity()));
					((EnergyStation) currentObj).setEnergyCapacity(0); // sets energy to 0
					((EnergyStation) currentObj).setColor(ColorUtil.red(((EnergyStation) currentObj).getColor()) + 120,
							ColorUtil.green(((EnergyStation) currentObj).getColor()) + 55,
							ColorUtil.blue(((EnergyStation) currentObj).getColor()) + 120); // sets
					// color
					// lighter
					break; // will stop after first iteration, only making change to 1 energyStation
				}
			}
		}
		if (sound) {
			esCollision.play();
		}
		EnergyStation randEG = new EnergyStation();
		getGameObjectCollection().add(randEG); // adds another energy station and will always have a random location
		System.out.println("Cyborg collided with an Energy Station");
		reset();
		setChanged();
		notifyObservers(this);
	}

	/*
	 * INCREASE DAMAGE LEVEL TO CYBORG FADE THE COLOR OF THE CYBORG AND (IF
	 * NECESSARY) REDUCES THE SPEED OF THE CYBORG AND NO CHANGE TO THE DRONE, DOESNT
	 * MATTER WHICH DRONE IS PICKED
	 */
	// g ** DONE **
	public void droneCollidesCyborg() {
		IIterator theIterator = getGameObjectCollection().getIterator();
		GameObject currentObj = new GameObject();
		while (theIterator.hasNext()) {
			currentObj = (GameObject) theIterator.getNext(); // goes through each object in worldVector
			if (currentObj instanceof Drone) { // checks to see if there is a drone
				playerCyborg.setDamageLevel(playerCyborg.getDamageLevel() + 1); // increased damage level to cyborg
				playerCyborg.setColor(ColorUtil.red(playerCyborg.getColor()) + 28,
						ColorUtil.green(playerCyborg.getColor()) + 10, ColorUtil.blue(playerCyborg.getColor()) - 8); // gets
																														// ligher
																														// in
				// color
				playerCyborg.setMaximumSpeed(playerCyborg.getMaximumSpeed() - 10); // speed starts at 50 and goes to 0,
																					// decreases 10
				// at a time
				if (playerCyborg.getSpeed() == 0 || playerCyborg.getDamageLevel() == 5) { // checks to see if speed is 0
					reset(); // resets cyborg and loses a life
				}
				break;
			}
		}
		reset();
		System.out.println("Drone collided with a Cyborg");
		setChanged();
		notifyObservers(this);
	}

	/*
	 * tick 1) If the player cyborg moves (e.g., did not run out of energy or does
	 * not have the maximum damage or zero speed), then the cyborg’s heading should
	 * be incremented or decremented by the cyborg’s steeringDirection (that is, the
	 * steering wheel turns the cyborg) 2) Drones also update their heading as
	 * indicated above 3) all moveable objects are told to update their positions
	 * according to their current heading and speed, 4) the cyborg’s energy level is
	 * reduced by the amount indicated by its energyConsumptionRate 5) the elapsed
	 * time “game clock” is incremented by one (the game clock for this assignment
	 * is simply a variable which increments with each tick)
	 * 
	 */

	// t
	public void tick(int time) {

		IIterator theIterator = getGameObjectCollection().getIterator();
		GameObject currentObj = new GameObject();
		while (theIterator.hasNext()) {
			currentObj = (GameObject) theIterator.getNext();
			if (currentObj instanceof PlayerCyborg) {
				if (((PlayerCyborg) currentObj).getEnergyConsumption() != 0
						|| ((PlayerCyborg) currentObj).getDamageLevel() != 5
						|| ((PlayerCyborg) currentObj).getSpeed() != 0) {
					((PlayerCyborg) currentObj)
							.setHeading(playerCyborg.getHeading() + playerCyborg.getSteeringDirection());
				}
			}
			if (currentObj instanceof Drone) {
				if (((Drone) currentObj).getSpeed() != 0) {
					((Drone) currentObj).setHeading(((Drone) currentObj).getHeading());
				}
			}
			if (currentObj instanceof Movable) {
				Movable mObj = (Movable) currentObj;
				mObj.move(time);
			}

			if (currentObj instanceof PlayerCyborg) {
				if (((PlayerCyborg) currentObj).getEnergyConsumption() != 0
						|| ((PlayerCyborg) currentObj).getDamageLevel() != 5
						|| ((PlayerCyborg) currentObj).getSpeed() != 0) {
					if (((PlayerCyborg) currentObj).getEnergyLevel() > 0)
						((PlayerCyborg) currentObj)
								.setEnergyLevel(playerCyborg.getEnergyLevel() - playerCyborg.getEnergyConsumption());
				} else {
					reset();
					System.out.println("Energy level is 0, unable to move");
				}
			}
		}
		IIterator collisionIt = gameObjectCollection.getIterator();
		while (collisionIt.hasNext()) {
			ICollider collidingObj = (ICollider) collisionIt.getNext();
			if (playerCyborg.collidesWith(collidingObj)) {
				if (!collisionVector.contains((GameObject) collidingObj)) {
					collisionVector.add((GameObject) collidingObj);
					if (collidingObj instanceof Drone) {
						((Drone) collidingObj).collidesWith(playerCyborg);
						droneCollidesCyborg();
					}
					if (collidingObj instanceof EnergyStation) {
						cyborgCollidesEnergy();
						gameObjectCollection.remove((GameObject) collidingObj);
					}
					if (collidingObj instanceof NonPlayerCyborg) {
						cyborgCollidesCyborg();
					}
					if (collidingObj instanceof Base) {
						int i = ((Base) collidingObj).getSequenceNumber();
						if (i + 1 == playerCyborg.getLastBaseReached() + 1) {
							if (i == playerCyborg.getLastBaseReached() + 1)
								cyborgCollidesBase(playerCyborg.getLastBaseReached() + 1);
						}
					}

				} else {
					collisionVector.remove((GameObject) collidingObj);
				}
			}
		}

		if (sound == true)
			background.run();
		else if (isPaused == true || sound == false)
			background.pause();

		setClock(getClock() + 1);
		reset();
		System.out.println("Clock incremented");
		setChanged();
		notifyObservers(this);
	}

	// m
	public void map() {
		int m = getGameObjectCollection().getSize();
		for (int i = 0; i < m; i++) {
			System.out.println(getGameObjectCollection().elementAt(i));
		}
	}

	/*
	 * DISPLAYS # OF LIVES, CURRENT CLOCK VALUE, HIGHEST BASE NUMBER THE CYBORG
	 * REACHED, CYBORG CURRENT ENERGY LEVEL, CYBORG CURRENT DAMAGE LEVEL
	 */
	// d
	public void display() {
		System.out.println("Current Lives : " + playerCyborg.getPlayerLife() + " Time : " + this.getClock()
				+ " Last Base Reached : " + playerCyborg.getLastBaseReached() + " Energy Level : "
				+ playerCyborg.getEnergyLevel() + " Damage Level: " + playerCyborg.getDamageLevel());
	}

	/*
	 * DONT NEED EXTIS // x public void exit() {
	 * System.out.println("Do you want to exit? Y/N"); }
	 * 
	 * // y public void confirm() { System.exit(0); }
	 * 
	 * // n public void dontConfirm() { System.out.println("Returning to game"); }
	 */
	public void changeSoundOnOff() {
		if (getSound() == true)
			setSound(false);
		else
			setSound(true);
		setChanged();
		notifyObservers(this);
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public boolean getSound() {
		return sound;
	}

	public int getClock() {
		return clock;
	}

	public void setClock(int clock) {
		this.clock = clock;
	}

	public int getPlayerLives() {
		return playerCyborg.getPlayerLife();
	}

	public int getLastBaseReached() {
		return playerCyborg.getLastBaseReached();
	}

	public int getEnergyLevel() {
		return playerCyborg.getEnergyLevel();
	}

	public int getDamageLevel() {
		return playerCyborg.getDamageLevel();
	}

	public static GameObjectCollection getGameObjectCollection() {
		return gameObjectCollection;
	}

	public void setGameObjectCollection(GameObjectCollection gameObjectCollection) {
		GameWorld.gameObjectCollection = gameObjectCollection;
	}

	// changingstrat v2
	public void changeStrategy() {
		IIterator theIterator = getGameObjectCollection().getIterator();
		GameObject currentObj = new GameObject();
		while (theIterator.hasNext()) {
			currentObj = (GameObject) theIterator.getNext();
			if (currentObj instanceof NonPlayerCyborg) {
				if (((NonPlayerCyborg) currentObj).getStrategy() instanceof RaceStrategy) {
					((NonPlayerCyborg) currentObj)
							.setStrategy(new AttackStrategy(((NonPlayerCyborg) currentObj), playerCyborg));
					((NonPlayerCyborg) currentObj).setStrat("Attack");
				} else if (((NonPlayerCyborg) currentObj).getStrategy() instanceof AttackStrategy) {
					((NonPlayerCyborg) currentObj).setStrategy(new RaceStrategy(((NonPlayerCyborg) currentObj), this));
					((NonPlayerCyborg) currentObj).setStrat("Racer");
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	public void reset() {
		if (playerCyborg.getEnergyLevel() <= 0 || playerCyborg.getDamageLevel() == 5) {
			if (this.getPlayerLives() != 0) {
				playerCyborg.setPlayerLife(playerCyborg.getPlayerLife() - 1);
				if (sound) {
					loseLife.play(); // loselife is a sound file
				}
				playerCyborg.setLastBaseReached(1);
				playerCyborg.setDamageLevel(0); // resets damage level
				playerCyborg.setEnergyLevel(101); // resets energy level
				playerCyborg.setLocation(100, 100); // resets to starting location
				playerCyborg.setColor(80, 200, 180); // sets color back to default
				playerCyborg.setSpeed(0); // sets speed to 0 at start
				playerCyborg.setEnergyConsumption(1);
				playerCyborg.setHeading(0);
			} else if (playerCyborg.getPlayerLife() == 0) {
				playerCyborg.setLocation(-100, -100); // sets location off screen
				gameOver();
			}
		}
	}

	private void gameOver() {
		Dialog.show("Game Over!", "", "Ok", null);
		Display.getInstance().exitApplication();
	}

	public void createSounds() {
		cyborgCollision = new Sound("cyborgcollide.wav");
		esCollision = new Sound("escollide.wav");
		loseLife = new Sound("loselife.wav");
		background = new BGSound("bg.mp3");

	}

	public void setDimension(int w, int h) {
		GameWorld.width = w;
		GameWorld.height = h;
	}

	public static int getHeight() {
		return height;
	}

	public static int getWidth() {
		return width;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean pause) {
		this.isPaused = pause;
	}

	// changingstrat v1
	/*
	 * public void updateCharacters() { for (int i = 0; i <
	 * gameObjectCollection.getSize(); i++) { // if cyborg moves, then update energy
	 * level if (gameObjectCollection.elementAt(i) instanceof NonPlayerCyborg) { if
	 * (cyborg1.isStrat() == true) {
	 * 
	 * cyborg1.setStrategy(new RaceStrategy()); cyborg2.setStrategy(new
	 * RaceStrategy()); cyborg3.setStrategy(new RaceStrategy());
	 * 
	 * cyborg1.setStrat("Racer"); cyborg2.setStrat("Racer");
	 * cyborg3.setStrat("Racer");
	 * 
	 * cyborg1.invokeStrategy(); cyborg2.invokeStrategy(); cyborg3.invokeStrategy();
	 * 
	 * cyborg1.setStrat(true); cyborg2.setStrat(true); cyborg3.setStrat(true);
	 * 
	 * } else if (cyborg1.isStrat() == false) { cyborg1.setStrategy(new
	 * AttackStrategy()); cyborg2.setStrategy(new AttackStrategy());
	 * cyborg3.setStrategy(new AttackStrategy());
	 * 
	 * cyborg1.setStrat("Attacker"); cyborg2.setStrat("Attacker");
	 * cyborg3.setStrat("Attacker");
	 * 
	 * cyborg1.invokeStrategy(); cyborg2.invokeStrategy(); cyborg3.invokeStrategy();
	 * 
	 * cyborg1.setStrat(false); cyborg2.setStrat(false); cyborg3.setStrat(false);
	 * 
	 * } } } }
	 */

}
