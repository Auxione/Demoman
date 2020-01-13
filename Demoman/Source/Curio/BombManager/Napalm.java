package Curio.BombManager;

import org.newdawn.slick.Graphics;

import Curio.FluidMap;
import Curio.TileMap;
import Curio.FireManager.FireManager;
import Curio.ItemSystem.ItemMap;
import Curio.PlantSystem.PlantMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;
import Default.Player;

public class Napalm extends Bomb {
	private FireManager fireManager;
	private int damage = 15;
	private int bombSize = 2;

	public Napalm(CellCoordinate cellPosition, int time, FireManager fireManager) {
		super(time, Constants.blueBombNapalm, cellPosition);
		this.fireManager = fireManager;
	}

	@Override
	public void Effect(Player player, TileMap tileMap, ItemMap itemMap, PlantMap plantMap, FluidMap fluidMap) {
		player.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), damage);
		tileMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), damage);
		itemMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), damage);
		plantMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY(), damage);

		for (int x = 1; x <= bombSize; x++) {
			player.applyDamage(cellPosition.getCellX() + x, cellPosition.getCellY(), damage);
			tileMap.applyDamage(cellPosition.getCellX() + x, cellPosition.getCellY(), damage);
			itemMap.applyDamage(cellPosition.getCellX() + x, cellPosition.getCellY(), damage);
			plantMap.applyDamage(cellPosition.getCellX() + x, cellPosition.getCellY(), damage);

			player.applyDamage(cellPosition.getCellX() - x, cellPosition.getCellY(), damage);
			tileMap.applyDamage(cellPosition.getCellX() - x, cellPosition.getCellY(), damage);
			itemMap.applyDamage(cellPosition.getCellX() - x, cellPosition.getCellY(), damage);
			plantMap.applyDamage(cellPosition.getCellX() - x, cellPosition.getCellY(), damage);
		}
		for (int y = 1; y <= bombSize; y++) {
			player.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() + y, damage);
			tileMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() + y, damage);
			itemMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() + y, damage);
			plantMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() + y, damage);

			player.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() - y, damage);
			tileMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() - y, damage);
			itemMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() - y, damage);
			plantMap.applyDamage(cellPosition.getCellX(), cellPosition.getCellY() - y, damage);
		}
	}

	@Override
	public void effectRender(Graphics g) {
		// TODO Auto-generated method stub

	}
}
