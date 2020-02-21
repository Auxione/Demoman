package Curio.Controllers.Input;

import org.newdawn.slick.Input;

import Curio.Viewport;
import Curio.Controllers.ControlPackage;
import Curio.Physics.Interfaces.FLUpdate;
import Curio.Utilities.Math.Vector;

public class UserKeyboardInput implements FLUpdate {
	private ControlPackage controlPackage = new ControlPackage();
	// keys
	private int key_UP = Input.KEY_W;
	private int key_DOWN = Input.KEY_S;
	private int key_RIGHT = Input.KEY_D;
	private int key_LEFT = Input.KEY_A;

	private int key_USE = Input.KEY_E;
	private int key_USEITEM = Input.KEY_SPACE;
	private int key_SWITCHITEM = Input.KEY_TAB;

	private int key_DROP = Input.KEY_G;
	private int key_TAKE = Input.KEY_C;

	public boolean KeyLock = true;
	private Input input;

	public UserKeyboardInput(Input input) {
		this.input = input;
	}


	public void createLookDirection() {
		Vector p = new Vector(Viewport.ScreenMiddle);
		Vector m = new Vector(Viewport.ScreenToWorldPosition(new Vector(input.getMouseX(), input.getMouseY())));

		this.controlPackage.rotation.setAngle(p.sub(m).getAngleInDegreesMAPPED());
	}

	public void createMovementDirection() {
		int right = 0;
		int left = 0;
		int up = 0;
		int down = 0;

		if (controlPackage.ActionNorth == true) {
			up = -1;
		}
		if (controlPackage.ActionSouth == true) {
			down = +1;
		}
		if (controlPackage.ActionEast == true) {
			left = +1;
		}
		if (controlPackage.ActionWest == true) {
			right = -1;
		}

		this.controlPackage.movementDirection.set(left + right, up + down, 0);
	}

	public ControlPackage getPackage() {
		// TODO Auto-generated method stub
		return controlPackage;
	}

	@Override
	public void FirstUpdate() {
		if (KeyLock == true) {
			createLookDirection();
			createMovementDirection();
			this.controlPackage.ActionNorth = input.isKeyDown(key_UP);
			this.controlPackage.ActionSouth = input.isKeyDown(key_DOWN);
			this.controlPackage.ActionWest = input.isKeyDown(key_LEFT);
			this.controlPackage.ActionEast = input.isKeyDown(key_RIGHT);

			this.controlPackage.ActionUse = input.isKeyDown(key_USE);
			this.controlPackage.ActionUseItem = input.isKeyPressed(key_USEITEM);
			this.controlPackage.ActionSwitchItem = input.isKeyPressed(key_SWITCHITEM);
			this.controlPackage.ActionTake = input.isKeyPressed(key_TAKE);
			this.controlPackage.ActionDrop = input.isKeyPressed(key_DROP);
		}
	}

	@Override
	public void LastUpdate() {
	}
}
