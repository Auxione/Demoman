package Default;

import org.newdawn.slick.Input;

import Curio.Functions;

public class Controller {
	private int Mode = 0;
	// keys
	private int key_UP = Input.KEY_W;
	private int key_DOWN = Input.KEY_S;
	private int key_RIGHT = Input.KEY_D;
	private int key_LEFT = Input.KEY_A;
	private int key_USE = Input.KEY_E;
	private int key_BOMB = Input.KEY_Q;

	public boolean ActionNorth = false, ActionSouth = false, ActionWest = false, ActionEast = false, ActionUse = false,
			ActionBomb = false;

	public boolean KeyLock;

	public Controller(int om) {
		Mode = om;
		// enable player controls for the object
		if (Mode == 1) {
			KeyLock = true;
		} else {
			KeyLock = false;
		}
	}

	public void update() {
		// disable every key first
		// choose modes
		// controller disabled
		if (Mode == 0) {
			Disabled();
		}
		// player controls the object
		else if (Mode == 1) {
			PlayerControl();
		}
		// computer controls the object
		else if (Mode == 2) {
			AIControl();
		}
		// server controls the object
		else if (Mode == 3) {
			ServerControl();
		}
	}

	// ===========================================mode0===========================================
	int Mode0_current_time = 0;
	int Mode0_goal;

	private void Disabled() {
		Mode0_current_time = Functions.millis();
		if (Functions.millis() > Mode0_goal) {
			Mode0_goal = Mode0_current_time + 2500;
			System.out.println("Controller Disabled");
			Mode0_current_time = Functions.millis();
		}
	}

	// ===========================================mode1===========================================
	private void PlayerControl() {
	}

	public void Pressed() {
		if (KeyLock) {
			if (Main.input.isKeyDown(key_UP)) {
				ActionNorth = true;
			}
			if (Main.input.isKeyDown(key_DOWN)) {
				ActionSouth = true;
			}
			if (Main.input.isKeyDown(key_LEFT)) {
				ActionWest = true;
			}
			if (Main.input.isKeyDown(key_RIGHT)) {
				ActionEast = true;
			}
			if (Main.input.isKeyDown(key_BOMB)) {
				ActionBomb = true;
			}
			if (Main.input.isKeyDown(key_USE)) {
				ActionUse = true;
			}
		}
	}

//execute end of the loop to prevent actions stuck in true state
	public void ActionEnd() {
		if (KeyLock) {
			ActionNorth = false;
			ActionSouth = false;
			ActionWest = false;
			ActionEast = false;
			ActionUse = false;
			ActionBomb = false;
		}
	}

	// ===========================================mode2===========================================
	private void AIControl() {
	}

	// ===========================================mode3===========================================
	private void ServerControl() {
	}
}