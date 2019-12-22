package Curio.ItemMap.Items;

import org.newdawn.slick.Image;

import Curio.ItemMap.Item;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public class NapalmBomb implements Item {
	public int value = 35;
	private Image image = Constants.blueBombNapalm;
	Transform tr;
	
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}
	
	public void apply(Player dp, TileMap level, BombManager bm) {
		bm.create(dp.CellPosition, 2, 1500);

	}

	@Override
	public boolean condition(Player dp, TileMap level, BombManager bm) {
		// TODO Auto-generated method stub
		return bm.canPlace(dp.CellPosition);
	}

}
