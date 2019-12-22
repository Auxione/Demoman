package Curio.Physics;

import java.util.ArrayList;

import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;
import Curio.Utilities.Math.Vector;

public class DynamicObject {
	public static ArrayList<DynamicObject> dynamicObjectList = new ArrayList<DynamicObject>();
	TileMap level;

	public Vector Position;
	public Transform CellPosition;

	public Vector Velocity;
	public Vector Acceleration;
	private float AccelOffset = 1000;

	float size;

	protected DynamicObject(TileMap _level, float positionX, float positionY, float size) {
		level = _level;

		Position = new Vector(positionX, positionY);
		Velocity = new Vector(0, 0);
		Acceleration = new Vector(0, 0);
		dynamicObjectList.add(this);

		CellPosition = new Transform();
		this.size = size;
	}

	public void updatePhysics(float deltaTime) {
		float time = deltaTime / 1000;

		Velocity.x += Acceleration.x * time;
		Velocity.y += Acceleration.y * time;

		Position.x += Velocity.x * time;
		Position.y += Velocity.y * time;

		updateCellPosition();
		updateWithfriction();
	}

	private void updateWithfriction() {
		Velocity.multiply((float) 0.9);
		if (Velocity.magnitude() < 0.1) {
			Velocity.multiply(0);
		}
	}

	void updateCellPosition() {
		CellPosition = level.worldPostoMapPos(Position);
	}

	protected void move(int x, int y) {
		Acceleration.x = x * AccelOffset;
		Acceleration.y = y * AccelOffset;
	}
}