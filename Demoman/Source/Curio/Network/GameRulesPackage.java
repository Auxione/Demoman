package Curio.Network;

import java.io.Serializable;

public class GameRulesPackage implements Serializable {
	public int mapSizeX;
	public int mapSizeY;
	public int InventoryCellSize;
	public int InventoryCellCount;
	public int itemMapCellCount;

	public GameRulesPackage() {
	}

	public GameRulesPackage setTileMapRules(int mapSizeX, int mapSizeY) {
		this.mapSizeX = mapSizeX;
		this.mapSizeY = mapSizeY;
		return this;
	}

	public GameRulesPackage setItemMapRules(int itemMapCellCount) {
		this.itemMapCellCount = itemMapCellCount;
		return this;
	}

	public GameRulesPackage setInventoryRules(int InventoryCellSize, int InventoryCellCount) {
		this.InventoryCellSize = InventoryCellSize;
		this.InventoryCellCount = InventoryCellCount;
		return this;
	}
}
