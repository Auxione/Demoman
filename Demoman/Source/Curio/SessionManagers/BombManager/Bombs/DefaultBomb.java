package Curio.SessionManagers.BombManager.Bombs;

import Curio.SessionManagers.BombManager.Bomb;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public class DefaultBomb extends Bomb {
	public boolean Exploded = false;
	private int damage;
	private int bombSize = 2;

	public DefaultBomb(WorldManager worldManager, Transform transform, int time, int damage) {
		super(worldManager, transform, Constants.blueBombNormal, time);
		this.damage = damage;
	}

	@Override
	public void Effect(WorldManager worldManager, FireManager fireManager, PlantManager plantManager, ItemMap itemMap,
			Player player) {

		player.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY(), damage);
		worldManager.tileMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY(), damage);
		itemMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY(), damage);
		plantManager.plantMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY(), damage);

		for (int x = 1; x <= bombSize; x++) {
			player.applyDamage(super.cellCoordinate.getCellX() + x, super.cellCoordinate.getCellY(), damage);
			worldManager.tileMap.applyDamage(super.cellCoordinate.getCellX() + x, super.cellCoordinate.getCellY(), damage);
			itemMap.applyDamage(super.cellCoordinate.getCellX() + x, super.cellCoordinate.getCellY(), damage);
			plantManager.plantMap.applyDamage(super.cellCoordinate.getCellX() + x, super.cellCoordinate.getCellY(), damage);

			player.applyDamage(super.cellCoordinate.getCellX() - x, super.cellCoordinate.getCellY(), damage);
			worldManager.tileMap.applyDamage(super.cellCoordinate.getCellX() - x, super.cellCoordinate.getCellY(), damage);
			itemMap.applyDamage(super.cellCoordinate.getCellX() - x, super.cellCoordinate.getCellY(), damage);
			plantManager.plantMap.applyDamage(super.cellCoordinate.getCellX() - x, super.cellCoordinate.getCellY(), damage);
		}

		for (int y = 1; y <= bombSize; y++) {
			player.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY() + y, damage);
			worldManager.tileMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY() + y, damage);
			itemMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY() + y, damage);
			plantManager.plantMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY() + y, damage);

			player.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY() - y, damage);
			worldManager.tileMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY() - y, damage);
			itemMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY() - y, damage);
			plantManager.plantMap.applyDamage(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY() - y, damage);
		}
	}
}
