package Curio.SessionManagers;

import java.util.ArrayList;

import Curio.Physics.DynamicObject;
import Curio.Physics.Collisions.Collision;
import Curio.Physics.Collisions.DynamicObjectCollision;
import Curio.Physics.Collisions.TilemapCollision;
import Curio.Physics.Interfaces.FrameUpdate;
import Curio.SessionManagers.WorldManager.WorldManager;

public class CollisionManager implements FrameUpdate {
	public static ArrayList<Collision> collisionList = new ArrayList<Collision>();
	private WorldManager worldManager;

	public CollisionManager(WorldManager worldManager) {
		this.worldManager = worldManager;
	}

	public TilemapCollision addTilemapCollision(DynamicObject dynamicObject) {
		TilemapCollision tc = new TilemapCollision(worldManager.tileMap).setDynamicObject(dynamicObject)
				.setShape(dynamicObject.shape).setState(true);
		collisionList.add(tc);
		return tc;
	}

	public DynamicObjectCollision addDynamicObjectCollision(DynamicObject dynamicObject) {
		DynamicObjectCollision doc = new DynamicObjectCollision(dynamicObject).setShape(dynamicObject.shape).setState(true);
		collisionList.add(doc);
		return doc;
	}

	public void removeCollision(Collision collision) {
		if (collisionList.contains(collision)) {
			collisionList.remove(collision);
		}
	}

	@Override
	public void frameUpdate() {
		for (Collision c : collisionList) {
			if (c instanceof FrameUpdate) {
				c.frameUpdate();
			}
		}
	}
}