package Curio;

import java.io.Serializable;

import org.newdawn.slick.Image;

import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

import static Curio.Functions.*;

public class GameObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean cellSnapState = false;

	protected Image gameObjectImage = null;
	protected Image objectAlphaMaskImage = null;

	public Transform transform;
	public CellCoordinate cellCoordinate;

	public GameObject() {
		transform = new Transform();
		cellCoordinate = new CellCoordinate();
	}

	public GameObject setCellSnapping(boolean cellSnapState) {
		this.cellSnapState = cellSnapState;
		return this;
	}

	public GameObject setTransform(Transform transform) {
		this.cellCoordinate = worldPostoCellPosition(transform.position);

		if (cellSnapState == false) {
			this.transform.position.x = transform.position.x;
			this.transform.position.y = transform.position.y;
		}

		else if (cellSnapState == true) {
			this.transform.position.x = cellCoordinate.getCellX() * Constants.CellSize;
			this.transform.position.y = cellCoordinate.getCellY() * Constants.CellSize;
		}
		this.transform.rotation.setAngle(transform.rotation.degrees());
		return this;
	}

	public GameObject setObjectImage(Image img) {
		this.gameObjectImage = img;
		return this;
	}

	public GameObject setAlphaMaskImage(Image img) {
		this.objectAlphaMaskImage = img;
		return this;
	}

	public Image getObjectImage() {
		return gameObjectImage;
	}

	public Image getAlphaMaskImage() {
		return objectAlphaMaskImage;
	}
}