package Curio;

import java.util.HashMap;

import Default.Constants;

public class TileList {
	private static HashMap<Integer, Tile> Tile = new HashMap<Integer, Tile>();

	public TileList() {
		Tile.put(0, new Tile().setName("empty").setSolid(false).setUnbreakable(true).setTileMaxHP(5000)
				.setFlammable(false).setTexture(Constants.empty).setCanPlant(false).setCanFlow(false));
		Tile.put(1, new Tile().setName("grass").setSolid(false).setUnbreakable(true).setTileMaxHP(5000)
				.setFlammable(true).setTexture(Constants.grass).setCanPlant(true).setCanFlow(true));
		Tile.put(2, new Tile().setName("wood").setSolid(false).setUnbreakable(true).setTileMaxHP(2000)
				.setFlammable(true).setTexture(Constants.wood).setCanPlant(false).setCanFlow(false));
		Tile.put(3, new Tile().setName("rock").setSolid(false).setUnbreakable(true).setTileMaxHP(3000)
				.setFlammable(false).setTexture(Constants.rock).setCanPlant(false).setCanFlow(false));
		Tile.put(4, new Tile().setName("Brickwall").setSolid(true).setUnbreakable(false).setTileMaxHP(4000)
				.setFlammable(false).setTexture(Constants.brick).setCanPlant(false).setCanFlow(false));
		Tile.put(5, new Tile().setName("metalframe").setSolid(true).setUnbreakable(true).setTileMaxHP(5000)
				.setFlammable(false).setTexture(Constants.metalframe).setCanPlant(false).setCanFlow(false));
		Tile.put(6, new Tile().setName("farmLand").setSolid(false).setUnbreakable(true).setTileMaxHP(5000)
				.setFlammable(false).setTexture(Constants.Farmland).setCanPlant(true).setCanFlow(true));
	}
	public static Tile getTile(int id) {
		return Tile.get(id);
	}
}
