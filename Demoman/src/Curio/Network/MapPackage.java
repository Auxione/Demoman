package Curio.Network;

import java.io.Serializable;

import Curio.Tilemap.TileMap;

public class MapPackage implements Serializable {
	public int[][] tilemap;

	public MapPackage(TileMap tilemap) {
		this.tilemap = tilemap.get_Tilemap();
	}
}
