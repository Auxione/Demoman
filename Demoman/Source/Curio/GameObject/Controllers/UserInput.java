package Curio.GameObject.Controllers;

import org.newdawn.slick.Input;

public class UserInput {
	private ControlPackage controlPackage = new ControlPackage();

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

	public boolean KeyLock = true;
	private Input input;

	public UserInput(Input input) {
		this.input = input;
	}

	public void updateStart() {
		if (KeyLock == true) {
			if (input.isKeyDown(key_UP)) {
				controlPackage.ActionNorth = true;
			}
			if (input.isKeyDown(key_DOWN)) {
				controlPackage.ActionSouth = true;
			}
			if (input.isKeyDown(key_LEFT)) {
				controlPackage.ActionWest = true;
			}
			if (input.isKeyDown(key_RIGHT)) {
				controlPackage.ActionEast = true;
			}
			controlPackage.ActionBomb = input.isKeyPressed(key_BOMB);
			controlPackage.ActionUse = input.isKeyPressed(key_USE);
			controlPackage.ActionSwitchItem = input.isKeyPressed(key_SWITCHITEM);
			controlPackage.ActionTake = input.isKeyPressed(key_TAKE);
			controlPackage.ActionDrop = input.isKeyPressed(key_DROP);
		}
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
