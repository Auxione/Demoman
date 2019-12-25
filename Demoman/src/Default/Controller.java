package Default;

import org.newdawn.slick.Input;

import Curio.Functions;
import Curio.HUD.ConsoleDisplay;
import Curio.Physics.DynamicObject;

public class Controller {
	private int Mode = 0;
	// keys
	private int key_UP = Input.KEY_W;
	private int key_DOWN = Input.KEY_S;
	private int key_RIGHT = Input.KEY_D;
	private int key_LEFT = Input.KEY_A;

	private int key_USE = Input.KEY_SPACE;
	private int key_SWITCHITEM = Input.KEY_TAB;
	private int key_DROP = Input.KEY_G;
	private int key_TAKE = Input.KEY_E;

	private int key_BOMB = Input.KEY_Q;

	public boolean ActionNorth = false, ActionSouth = false, ActionWest = false, ActionEast = false, ActionUse = false,
			ActionBomb = false, ActionSwitchItem = false, ActionTake = false, ActionDrop = false;

	public boolean KeyLock;
	DynamicObject obj;
	private ConsoleDisplay console;

	public Controller(int Mode, DynamicObject obj, ConsoleDisplay console) {
		this.Mode = Mode;
		this.obj = obj;
		this.console = console;

		// enable player controls for the object
		if (Mode == 1) {
			KeyLock = true;
		} else {
			KeyLock = false;
		}
		String cmd = "Controller: Initialized and assigned.";
		console.Add(0, cmd);
	}

	public Controller(int Mode, DynamicObject obj) {
		this.Mode = Mode;
		this.obj = obj;
		this.console = null;

		// enable player controls for the object
		if (Mode == 1) {
			KeyLock = true;
		} else {
			KeyLock = false;
		}
		if (console != null) {
			String cmd = "Controller: Initialized and assigned.";
			console.Add(0, cmd);
		}
	}

	public void update() {
		// disable every key first
		// choose modes
		// controller disabled
		if (Mode == 0) {
			Disabled();
		}
		// user controls the object
		else if (Mode == 1) {
			UserControl();
		}
		// computer controls the object
		else if (Mode == 2) {
			AIControl();
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
	private void UserControl() {
		int right = 0;
		int left = 0;
		int up = 0;
		int down = 0;
		
		if (ActionNorth == true) {
			up = -1;
		}
		if (ActionSouth == true) {
			down = +1;
		}

		if (ActionEast == true) {
			left = +1;
		}
		if (ActionWest == true) {
			right = -1;
		}
		obj.addAcceleration(left+right,up+down);
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
			ActionBomb = Main.input.isKeyPressed(key_BOMB);
			ActionUse = Main.input.isKeyPressed(key_USE);
			ActionSwitchItem = Main.input.isKeyPressed(key_SWITCHITEM);
			ActionTake = Main.input.isKeyPressed(key_TAKE);
			ActionDrop = Main.input.isKeyPressed(key_DROP);
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
			ActionSwitchItem = false;
			ActionTake = false;
			ActionDrop = false;
		}
	}

	// ===========================================mode2===========================================
	private void AIControl() {
	}
}