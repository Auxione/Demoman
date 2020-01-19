package Curio.SessionManagers.LogicManager.LogicObjects;

import org.newdawn.slick.Image;

import Curio.GameObject;
import Curio.Utilities.Math.Transform;

public class LogicObject extends GameObject {
	public LogicObject(Image image, Transform transform) {
		super();
		super.setCellSnapping(true).setObjectImage(image).setTransform(transform);
	}
}
