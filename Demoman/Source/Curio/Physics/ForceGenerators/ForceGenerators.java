package Curio.Physics.ForceGenerators;

import Curio.Physics.DynamicObject;
import Curio.Utilities.Math.Vector;

public interface ForceGenerators{
	public Vector getForce(DynamicObject dynamicObject);
}
