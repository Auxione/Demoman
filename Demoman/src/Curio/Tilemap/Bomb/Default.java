package Curio.Tilemap.Bomb;

import org.newdawn.slick.Graphics;

import Curio.Tilemap.Tilemap;
import Curio.Utilities.Transform;
import Default.Constants;
import Default.DynamicPlayer;

public class Default extends Bomb {
	public Transform transform;
	
	public boolean Exploded = false;
	private int playerDamage = 75;
	private int tileDamage = 5000;
	
	public Default(Tilemap _level, Transform _transform, int _time) {
		super(_time, Constants.blueBombNormal, _transform);

		transform = _transform;
		
	}

	@Override
	public void effectRender(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerEffect(DynamicPlayer dp) {
		if (dp.CellPosition.equals(transform) == true) {
			dp.applyDamage(playerDamage);
		}
	}

	@Override
	public void tileEffect(Tilemap level) {
	level.applyDamage(transform.get_x(), transform.get_y(), tileDamage);
	}
}
