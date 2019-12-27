package Curio.Network;

import java.io.Serializable;

import Curio.Utilities.Math.Transform;

public class PlayerPositionPackage implements Serializable {
	public Credentials credentials;
	public Transform position;

	public PlayerPositionPackage(Credentials credentials, Transform position) {
		this.credentials = credentials;
		this.position = position;
	}
}
