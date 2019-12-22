package Curio.Tilemap.Bomb;

import org.newdawn.slick.Graphics;

import Curio.Tilemap.FireManager;
import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public class Napalm extends Bomb {
	public Transform transform;
	private TileMap level;
	private FireManager fireManager;
	
	private int playerDamage = 75;
	private int tileDamage = 1000;
	private int bombSize = 2;
	
	public Napalm(FireManager _fireManager, TileMap _level, Transform _transform, int _time) {
		super(_time, Constants.blueBombNapalm, _transform);

		transform = _transform;
		level = _level;
		fireManager = _fireManager;
	}

	@Override
	public void Effect(TileMap _level, Player dp) {
		for (int x = 0; x <= bombSize; x++) {
			level.applyDamage(transform.get_x() + x, transform.get_y(), tileDamage);

			if (dp.CellPosition.equals(transform.get_x() + x, transform.get_y())) {
				dp.applyDamage(playerDamage);
			}
		}

		for (int x = 0; x <= bombSize; x++) {
			level.applyDamage(transform.get_x() - x, transform.get_y(), tileDamage);

			if (dp.CellPosition.equals(transform.get_x() - x, transform.get_y())) {
				dp.applyDamage(playerDamage);
			}
		}

		for (int y = 0; y <= bombSize; y++) {
			level.applyDamage(transform.get_x(), transform.get_y() + y, tileDamage);

			if (dp.CellPosition.equals(transform.get_x(), transform.get_y() + y)) {
				dp.applyDamage(playerDamage);
			}
		}

		for (int y = 0; y <= bombSize; y++) {
			level.applyDamage(transform.get_x(), transform.get_y() - y, tileDamage);

			if (dp.CellPosition.equals(transform.get_x(), transform.get_y() - y)) {
				dp.applyDamage(playerDamage);
			}
		}
		fireManager.create(transform.get_x(), transform.get_y());
	}

	@Override
	public void effectRender(Graphics g) {
		// TODO Auto-generated method stub

	}
}
