package Curio.SessionManagers;

import java.util.ArrayList;

import Curio.Physics.DynamicObject;

public class DynamicObjectManager {
	public static ArrayList<DynamicObject> dynamicObjectList = new ArrayList<DynamicObject>();

	public DynamicObjectManager() {

	}

	public void update(int delta) {
		for (DynamicObject d : DynamicObject.dynamicObjectList) {
			d.updatePhysics(delta);
		}
	}

	public static DynamicObject add(DynamicObject dynamicObject) {
		dynamicObjectList.add(dynamicObject);
		return dynamicObject;
	}

	public static DynamicObject remove(DynamicObject dynamicObject) {
		dynamicObjectList.remove(dynamicObject);
		return dynamicObject;
	}
}
