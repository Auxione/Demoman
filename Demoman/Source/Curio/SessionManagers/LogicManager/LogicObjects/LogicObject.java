package Curio.SessionManagers.LogicManager.LogicObjects;

import org.newdawn.slick.Image;

import Curio.GameObject;
import Curio.Utilities.Math.Transform;

public abstract class LogicObject extends GameObject {
	public LogicObject(Image image, Transform transform) {
		super();
		super.setCellSnapping(true).setObjectImage(image).setTransform(transform);
	}

	public abstract String getName();
	public abstract String getCustomInfo();
	public abstract boolean getState();
	
}
