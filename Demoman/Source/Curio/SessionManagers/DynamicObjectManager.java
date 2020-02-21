package Curio.SessionManagers;

import java.util.ArrayList;

import Curio.Physics.DynamicObject;
import Curio.Physics.Interfaces.FixedUpdate;


public class DynamicObjectManager implements FixedUpdate {
	public static ArrayList<DynamicObject> dynamicObjectList;
	

	public DynamicObjectManager() {
		dynamicObjectList = new ArrayList<DynamicObject>();
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
