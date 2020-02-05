package Curio.SessionManagers.WorldObjectManager;

import Curio.Renderer.ObjectRenderer;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjects.ObjectTorch;
import Curio.Utilities.Math.Transform;

public class PlaceWorldObjects {
	private WorldManager worldManager;

	public PlaceWorldObjects(WorldManager worldManager) {
		this.worldManager = worldManager;
	}

	public ObjectTorch Torch(Transform transform, int burnTime) {
		ObjectTorch obj = new ObjectTorch(worldManager.worldTime, transform, burnTime);
		ObjectRenderer objr = new ObjectRenderer(obj);

		WorldObjectManager.worldObjects.add(obj);
		WorldObjectManager.worldObjectRenderer.add(objr);
		return obj;
	}
}
