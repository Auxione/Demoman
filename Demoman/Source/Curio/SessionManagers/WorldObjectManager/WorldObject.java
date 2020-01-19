package Curio.SessionManagers.WorldObjectManager;

import Curio.GameObject;

public abstract class WorldObject extends GameObject{

	public abstract void updateNight();

	public abstract void updateDayTime();
	
	public abstract String getName();
}
