package Curio.SessionManagers.BombManager;

import org.newdawn.slick.Image;

import Curio.GameObject;
import Curio.Physics.Time;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.Utilities.Math.Transform;
import Default.Player;

public abstract class Bomb extends GameObject {
	public boolean isExploded = false;
	public boolean isTicking = false;

	private Time goal = new Time();
	private Time currentTime;

	public Bomb(WorldManager worldManager, Transform transform, Image bombImage, int time) {
		super.setCellSnapping(true);
		super.setObjectImage(bombImage);
		super.setTransform(transform);
		this.currentTime = worldManager.worldTime.getTime();
		this.goal = new Time(worldManager.worldTime.getTime());

		this.goal.addSecond(time);
	}

	public void update() {
		if (goal.getSeconds() > currentTime.getSeconds()) {
			isTicking = true;
		} 
		
		else if (goal.getSeconds() < currentTime.getSeconds()) {
			isTicking = false;
			isExploded = true;
		}
	}

	protected abstract void Effect(WorldManager worldManager, FireManager fireManager, PlantManager plantManager,
			ItemMap itemMap, Player player);
}
