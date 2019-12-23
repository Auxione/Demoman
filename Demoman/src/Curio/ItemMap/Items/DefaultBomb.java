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
	@Override
	
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}
	@Override
	public void apply(Player dp, TileMap level, BombManager bm,PlantMap plantMap) {
		bm.create(dp.CellPosition, 1, 1500);
		
	}
	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm,PlantMap plantMap) {
		// TODO Auto-generated method stub
		return bm.canPlace(dp.CellPosition);
	}

}
