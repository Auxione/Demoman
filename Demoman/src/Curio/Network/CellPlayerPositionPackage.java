package Curio.Network;

import java.io.Serializable;

import Curio.Utilities.Math.Transform;

public class CellPlayerPositionPackage implements Serializable {
	public Credentials credentials;
	public Transform position;

	public CellPlayerPositionPackage(Credentials credentials, Transform position) {
		this.credentials = credentials;
		this.position = position;
	}
}
