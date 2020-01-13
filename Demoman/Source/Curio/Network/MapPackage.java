package Curio.Network;

import java.io.Serializable;
public class MapPackage implements Serializable {
	public int[][][] tileArray = null;
	public int[][][] itemArray = null;
	public int[][][] logicArray = null;
	public int[][][] plantArray = null;

	public MapPackage() {
	}

	public MapPackage addTileMap(int[][][] tileArray) {
		this.tileArray = tileArray;
		return this;
	}

	public MapPackage addItemMap(int[][][] itemArray) {
		this.itemArray = itemArray;
		return this;
	}

	public MapPackage addPlantMap(int[][][] plantArray) {
		this.plantArray = plantArray;
		return this;
	}

	public MapPackage addLogicMap(int[][][] logicArray) {
		this.logicArray = logicArray;
		return this;
	}

}
