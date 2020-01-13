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

public class Torch implements Item {
	private int health = 100;
	private Image image = Constants.torch;
	private String name = "Torch";
	private String description = "A torch made from stick";
	private int category = 5;

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public void apply(Player p, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		gOManager.CreateNewLightSource(new Transform(p.transform));

	}

	@Override
	public boolean condition(Player p, TileMap level, BombManager bm, PlantMap plantMap, GameObjectManager gOManager) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getItemCategory() {
		// TODO Auto-generated method stub
		return category;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

}
