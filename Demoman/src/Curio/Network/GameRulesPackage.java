package Curio.Network;

import java.io.Serializable;

import Curio.ItemMap.ItemMap;
import Curio.Tilemap.TileMap;

public class GameRulesPackage implements Serializable {
	public int mapSizeX;
	public int mapSizeY;

	public GameRulesPackage(int mapSizeX, int mapSizeY) {
		this.mapSizeX = mapSizeX;
		this.mapSizeY = mapSizeY;
	}
}
