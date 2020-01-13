package Curio.ItemSystem.Items;

import org.newdawn.slick.Image;

import Curio.TileMap;
import Curio.BombManager.BombManager;
import Curio.GameObject.GameObjectManager;
import Curio.ItemSystem.Item;
import Curio.PlantSystem.PlantMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public class DefaultBomb implements Item {
	public int value = 35;
	private Image image = Constants.blueBombNormal;
	Transform tr;

	private String name = "Bomb";
	private String description = "Deals " + value + " damage.";
	private int category = 3;

	@Override
	public int getItemCategory() {
		// TODO Auto-generated method stub
		return category;
	}

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
	public void apply(Player dp, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		bm.create(dp.cellCoordinate, 1, 100, value);

	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		// TODO Auto-generated method stub
		return bm.canPlace(dp.cellCoordinate);
	}

	private int health = 100;

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
