package Curio.Physics.Collisions;

import java.util.LinkedList;
import java.util.Queue;

import Curio.Physics.DynamicObject;
import Curio.Utilities.Shapes.Shape;

public class DynamicObjectCollision implements Collision {
	private boolean active = true;
	private DynamicObject dynamicObject;
	private Shape shape;
	private Queue<DynamicObject> otherObjects = new LinkedList<>();

	public DynamicObjectCollision(DynamicObject dynamicObject) {
		this.dynamicObject = dynamicObject;
	}

	public DynamicObjectCollision setState(Boolean active) {
		this.active = active;
		return this;
	}

	@Override
	public void frameUpdate() {
		if (active) {
			// search for nearby object then put it in queue
			// process the collisions in queue one by one

		}
	}

	public DynamicObjectCollision setShape(Shape shape) {
		this.shape = shape;
		return this;
	}

}
