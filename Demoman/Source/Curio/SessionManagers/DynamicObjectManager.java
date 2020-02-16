package Curio.SessionManagers;

import java.util.ArrayList;
import java.util.HashMap;

import Curio.Physics.DynamicObject;
import Curio.Physics.Collisions.TilemapCollision;
import Curio.Physics.Interfaces.FixedUpdate;
import Curio.Physics.Interfaces.FrameUpdate;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.Utilities.Shapes.Circle;

public class DynamicObjectManager implements FixedUpdate {
	public static ArrayList<DynamicObject> dynamicObjectList = new ArrayList<DynamicObject>();
	

	public DynamicObjectManager() {
	}

	public DynamicObject add(DynamicObject dynamicObject) {
		dynamicObjectList.add(dynamicObject);
		return dynamicObject;
	}

	public DynamicObject remove(DynamicObject dynamicObject) {
		dynamicObjectList.remove(dynamicObject);
		return dynamicObject;
	}

	@Override
	public void fixedUpdate(int delta) {
		for (DynamicObject d : dynamicObjectList) {
			if (d instanceof FixedUpdate) {
				d.fixedUpdate(delta);
			}
		}
	}
}
