package Curio.GameObject.Controllers;

import java.io.Serializable;

import Curio.Network.Credentials;

public class ControlPackage implements Serializable {
	public Credentials credentials = null;
	public boolean ActionNorth = false, ActionSouth = false, ActionWest = false, ActionEast = false, ActionUse = false,
			ActionBomb = false, ActionSwitchItem = false, ActionTake = false, ActionDrop = false;

	public ControlPackage setCredentials(Credentials credentials) {
		this.credentials = credentials;
		return this;
	}

	public ControlPackage() {
	}


}
