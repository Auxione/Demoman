package Curio.ItemMap.Items;

import org.newdawn.slick.Image;

import Curio.ItemMap.Item;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public class NapalmBomb implements Item {
	public int value = 35;
	private Image image = Constants.blueBombNapalm;
	Transform tr;
	
	private String name = "NapalmBomb";
	private String description = "Classic bomb with Napalm warhead.";

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
	
	public void apply(Player dp, TileMap level, BombManager bm,PlantMap plantMap) {
		bm.create(dp.CellPosition, 2, 1500);

	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm,PlantMap plantMap) {
		// TODO Auto-generated method stub
		return bm.canPlace(dp.CellPosition);
	}

}
