package Curio.ItemMap.Items;

import org.newdawn.slick.Image;

import Curio.ItemMap.Item;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.Player;

public class SausageSeed implements Item {

	private Image image = Constants.SausageSeed;
	
	private String name = "SausageSeed";
	private String description = "Who would think sausage can grew up on farmland?";

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
	
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public void apply(Player p, TileMap level, BombManager bm, PlantMap plantMap) {
		plantMap.put(p.CellPosition.get_x(), p.CellPosition.get_y(), 2);
	}

	@Override
	public boolean condition(Player p, TileMap level, BombManager bm, PlantMap plantMap) {
		return plantMap.canPlant(p, 2);
	}

}
