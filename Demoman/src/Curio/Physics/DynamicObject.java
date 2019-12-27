package Curio.Physics;

import java.util.ArrayList;

import Curio.Tilemap.TileMap;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;

public class DynamicObject {
	public static ArrayList<DynamicObject> dynamicObjectList = new ArrayList<DynamicObject>();
	TileMap level;

	public Transform transform;
	public CellCoordinate CellPosition;

	public Vector Velocity;
	public Vector Acceleration;
	private float AccelOffset = 1000;

	float size;

	protected DynamicObject(TileMap _level, float positionX, float positionY) {
		level = _level;

		transform = new Transform(positionX, positionY, 0);
		Velocity = new Vector(0, 0);
		Acceleration = new Vector(0, 0);
		dynamicObjectList.add(this);

		CellPosition = new CellCoordinate();

	}

	protected void setSize(float size) {
		this.size = size;
	}

	public void updatePhysics(float deltaTime) {
		float time = deltaTime / 1000;
		
		Velocity.x += Acceleration.x * time;
		Velocity.y += Acceleration.y * time;
		
		transform.position.x += Velocity.x * time;
		transform.position.y += Velocity.y * time;

		updateCellPosition();
		updateWithfriction();
		//System.out.println(Velocity.x + " : " + Velocity.y);
	}

	private void updateWithfriction() {
		if (Velocity.magnitude() < 1f) {
			Velocity.multiply(0);
		} else if (Velocity.magnitude() > 1f) {
			Velocity.multiply(0.96f);
		}
	}

	void updateCellPosition() {
		CellPosition = level.worldPostoMapPos(transform);
	}

	public void addAcceleration(int x, int y) {
		Acceleration.x = x * AccelOffset;
		Acceleration.y = y * AccelOffset;
	}

	public void setPosition(int x, int y) {
		transform.position.x = x;
		transform.position.y = y;
	}
}