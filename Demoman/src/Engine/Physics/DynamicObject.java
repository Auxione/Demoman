package Engine.Physics;

import java.util.ArrayList;

import Default.Constants;
import Engine.Functions;
import Engine.Tilemap.Tilemap;
import Engine.Utilities.Transform;
import Engine.Utilities.Vector;

public class DynamicObject {
	public static ArrayList<DynamicObject> dynamicObjectList = new ArrayList<DynamicObject>();
	Tilemap level;

	public Vector Position;
	public Transform CellPosition;

	private Vector Velocity;
	private Vector Acceleration;

	protected DynamicObject(Tilemap _level, float positionX, float positionY) {
		level = _level;

		Position = new Vector(positionX, positionY);
		Velocity = new Vector(0, 0);
		Acceleration = new Vector(0, 0);
		dynamicObjectList.add(this);

		CellPosition = new Transform();
	}

	public void updatePhysics(float deltaTime) {
		float time = deltaTime / 1000;
		Velocity.x = (float) (Velocity.x + 0.5 * Acceleration.x * (time * time));
		Velocity.y = (float) (Velocity.y + 0.5 * Acceleration.y * (time * time));

		Position.x = (float) (Position.x + Velocity.x * (time));
		Position.y = (float) (Position.y + Velocity.y * (time));

		updateCellPosition();
	}

	void updateCellPosition() {
		int cellx = (int) Functions.map(Position.x, 0, level.get_MaxCellX() * Constants.CellSize, 0,
				level.get_MaxCellX());
		int celly = (int) Functions.map(Position.y, 0, level.get_MaxCellY() * Constants.CellSize, 0,
				level.get_MaxCellY());
		CellPosition.set_x(cellx);
		CellPosition.set_y(celly);
	}

	protected void move(Vector f) {
		Acceleration.equals(f);
	}

	protected void move(int x, int y) {
		Acceleration.x = x;
		Acceleration.y = y;
	}
}