package Curio.ItemMap.Items;

import org.newdawn.slick.Image;

import Curio.ItemMap.Item;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public class DefaultBomb implements Item {
	public int value = 35;
	private Image image = Constants.blueBombNormal;
	Transform tr;

	private String name = "Bomb";
	private String description = "Deals " + value + " damage.";

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public void apply(Player dp, TileMap level, BombManager bm, PlantMap plantMap) {
		bm.create(dp.CellPosition, 1, 1500,value);

	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm, PlantMap plantMap) {
		// TODO Auto-generated method stub
		return bm.canPlace(dp.CellPosition);
	}

	private int health = 100;

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
