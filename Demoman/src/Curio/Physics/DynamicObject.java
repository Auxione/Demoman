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

	protected DynamicObject(TileMap _level, float positionX, float positionY) {
		level = _level;

		Position = new Vector(positionX, positionY);
		Velocity = new Vector(0, 0);
		Acceleration = new Vector(0, 0);
		dynamicObjectList.add(this);

		CellPosition = new Transform();

	}

	protected void setSize(float size) {
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
		CellPosition = level.worldPostoMapPos(Position);
	}

	public void addAcceleration(int x, int y) {
		Acceleration.x = x * AccelOffset;
		Acceleration.y = y * AccelOffset;
	}

	public void setPosition(int x, int y) {
		Position.x = x;
		Position.x = y;
	}
}