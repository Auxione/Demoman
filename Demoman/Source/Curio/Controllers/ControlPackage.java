package Curio.Controllers;

import java.io.Serializable;

import Curio.Network.Credentials;
import Curio.Utilities.Math.Rotation;
import Curio.Utilities.Math.Vector;

public class ControlPackage implements Serializable {
	public Credentials credentials = null;

	public Vector movementDirection = new Vector();
	public Rotation rotation = new Rotation();

	public boolean ActionNorth = false, ActionSouth = false, ActionWest = false, ActionEast = false,
			ActionUseItem = false, ActionUse = false, ActionBomb = false, ActionSwitchItem = false, ActionTake = false,
			ActionDrop = false;

	public ControlPackage setCredentials(Credentials credentials) {
		this.credentials = credentials;
		return this;
	}

	public ControlPackage() {
	}

	public ControlPackage resetActions() {
		ActionNorth = false;
		ActionSouth = false;
		ActionWest = false;
		ActionEast = false;
		ActionUseItem = false;
		ActionUse = false;
		ActionBomb = false;
		ActionSwitchItem = false;
		ActionTake = false;
		ActionDrop = false;
		return this;
	}
}
