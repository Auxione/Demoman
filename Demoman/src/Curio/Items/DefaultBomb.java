package Curio.Items;

import org.newdawn.slick.Image;

import Curio.Tilemap.Tilemap;
import Curio.Tilemap.Bomb.BombManager;
import Curio.Utilities.Transform;
import Default.Constants;
import Default.DynamicPlayer;

public class DefaultBomb extends Item {
	public int value = 35;
	private Image image = Constants.blueBombNormal;
	Transform tr;
	@Override
	
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}
	@Override
	public void apply(DynamicPlayer dp, Tilemap level, BombManager bm) {
		bm.create(dp.CellPosition, 1, 1500);
		
	}
	@Override
	public boolean condition(DynamicPlayer dp, Tilemap level, BombManager bm) {
		// TODO Auto-generated method stub
		return bm.canPlace(dp.CellPosition);
	}

}
