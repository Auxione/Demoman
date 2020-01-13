package Curio.GameObject;

import Curio.Console;
import Curio.GameObject.Controllers.ControlPackage;
import Curio.Physics.DynamicObject;

public class ObjectController {
	private String controllerConsolePrefix = "ObjectController: ";
	private Console console = null;

	public ControlPackage controlPackage;
	private DynamicObject dynamicObject = null;

	public ObjectController(DynamicObject dynamicObject) {
		this.dynamicObject = dynamicObject;
		this.controlPackage = new ControlPackage();
	}

	public ObjectController setConsole(Console console) {
		this.console = console;
		return this;
	}

	public ObjectController setControlPackage(ControlPackage controlPackage) {
		this.controlPackage = controlPackage;
		return this;
	}

	public void updateStart() {
		if (dynamicObject != null) {
			controlDynamicObject();
		}
	}

	public void updateEnd() {
		this.controlPackage.ActionNorth = false;
		this.controlPackage.ActionSouth = false;
		this.controlPackage.ActionWest = false;
		this.controlPackage.ActionEast = false;
		this.controlPackage.ActionUse = false;
		this.controlPackage.ActionBomb = false;
		this.controlPackage.ActionSwitchItem = false;
		this.controlPackage.ActionTake = false;
		this.controlPackage.ActionDrop = false;
	}

	private void controlDynamicObject() {
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
		dynamicObject.addAcceleration(left + right, up + down);
	}

}