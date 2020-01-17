package Curio.SessionManagers.BombManager;

import org.newdawn.slick.Graphics;
import Curio.ItemSystem.ItemMap;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.Utilities.CellCoordinate;
import Default.Constants;
import Default.Player;

public class Napalm extends Bomb {
	private int damage = 15;
	private int bombSize = 2;

	public Napalm(CellCoordinate cellPosition, int time) {
		super(time, Constants.blueBombNapalm, cellPosition);
	}

	@Override
	public void Effect(WorldManager worldManager, FireManager fireManager, PlantManager plantManager,
			ItemMap itemMap, Player player) {
		player.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), damage);
		worldManager.tileMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), damage);
		itemMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), damage);
		plantManager.plantMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), damage);

		for (int x = 1; x <= bombSize; x++) {
			player.applyDamage(cellPosition.getCellX() + x, cellPosition.getCellY(), damage);
			worldManager.tileMap.applyDamage(cellPosition.getCellX() + x, cellPosition.getCellY(), damage);
			itemMap.applyDamage(cellPosition.getCellX() + x, cellPosition.getCellY(), damage);
			plantManager.plantMap.applyDamage(cellPosition.getCellX() + x, cellPosition.getCellY(), damage);

			player.applyDamage(cellPosition.getCellX() - x, cellPosition.getCellY(), damage);
			worldManager.tileMap.applyDamage(cellPosition.getCellX() - x, cellPosition.getCellY(), damage);
			itemMap.applyDamage(cellPosition.getCellX() - x, cellPosition.getCellY(), damage);
			plantManager.plantMap.applyDamage(cellPosition.getCellX() - x, cellPosition.getCellY(), damage);
		}
		for (int y = 1; y <= bombSize; y++) {
			player.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() + y, damage);
			worldManager.tileMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() + y, damage);
			itemMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() + y, damage);
			plantManager.plantMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() + y, damage);

			player.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() - y, damage);
			worldManager.tileMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() - y, damage);
			itemMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() - y, damage);
			plantManager.plantMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() - y, damage);
		}
	}

	@Override
	public void effectRender(Graphics g) {
		// TODO Auto-generated method stub

	}
}
