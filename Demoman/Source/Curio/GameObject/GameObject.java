package Curio.GameObject;

import java.io.Serializable;

import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import static Curio.Functions.*;

public class GameObject implements Serializable {
	private static final long serialVersionUID = 1L;

	public int ID;
	public Transform transform;
	public CellCoordinate cellCoordinate;

	public GameObject() {
		transform = new Transform();
		cellCoordinate = new CellCoordinate();
	}

	public void setTransform(Transform transform) {
		this.transform.position.x = transform.position.x;
		this.transform.position.y = transform.position.y;
		this.transform.position.z = transform.position.z;
		this.cellCoordinate = worldPostoCellPosition(transform);
	}
}
