package Curio.Controllers;

import org.newdawn.slick.Input;

import Curio.Viewport;
import Curio.Utilities.Math.Vector;

public class UserInput {
	private ControlPackage controlPackage = new ControlPackage();
	private Vector mousePosition = new Vector();
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

	public UserInput(Input input) {
		this.input = input;
	}

	public void updateStart() {
		this.mousePosition.x = input.getMouseX();
		this.mousePosition.y = input.getMouseY();
		if (KeyLock == true) {
			if (input.isKeyDown(key_UP)) {
				this.controlPackage.ActionNorth = true;
			}
			if (input.isKeyDown(key_DOWN)) {
				this.controlPackage.ActionSouth = true;
			}
			if (input.isKeyDown(key_LEFT)) {
				this.controlPackage.ActionWest = true;
			}
			if (input.isKeyDown(key_RIGHT)) {
				this.controlPackage.ActionEast = true;
			}
			if (input.isKeyDown(key_USE)) {
				this.controlPackage.ActionUse = true;
			}

			this.controlPackage.ActionUseItem = input.isKeyPressed(key_USEITEM);
			this.controlPackage.ActionSwitchItem = input.isKeyPressed(key_SWITCHITEM);
			this.controlPackage.ActionTake = input.isKeyPressed(key_TAKE);
			this.controlPackage.ActionDrop = input.isKeyPressed(key_DROP);
		}

		Vector p = new Vector(Viewport.ScreenMiddle);
		Vector m = new Vector(Viewport.ScreenToWorldPosition(mousePosition));

		Vector c = new Vector(p.sub(m));

		this.controlPackage.rotation.setAngle(c.getAngleInDegreesMAPPED());

	}

	public ControlPackage getPackage() {
		// TODO Auto-generated method stub
		return controlPackage;
	}

	public void keyPressed(int key, char chr) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(int key, char chr) {
		// TODO Auto-generated method stub

	}
}
