package Curio.Tilemap.Bomb;

import org.newdawn.slick.Graphics;

import Curio.Tilemap.TileMap;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Player;

public class Default extends Bomb {
	public Transform transform;

	public boolean Exploded = false;
	private int playerDamage = 75;
	private int tileDamage = 5000;
	private int bombSize = 2;

	public Default(TileMap _level, Transform _transform, int _time) {
		super(_time, Constants.blueBombNormal, _transform);
		transform = _transform;

	}

	@Override
	public void effectRender(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Effect(TileMap level, Player dp) {
		for (int x = 0; x <= bombSize; x++) {
			level.applyDamage(transform.get_x() + x, transform.get_y(), tileDamage);
		}

		for (int x = 0; x <= bombSize; x++) {
			if (dp.CellPosition.equals(transform.get_x() + x, transform.get_y())) {
				dp.applyDamage(playerDamage);
				break;
			}
		}

		for (int x = 0; x <= bombSize; x++) {
			level.applyDamage(transform.get_x() - x, transform.get_y(), tileDamage);
		}
		for (int x = 0; x <= bombSize; x++) {
			if (dp.CellPosition.equals(transform.get_x() - x, transform.get_y())) {
				dp.applyDamage(playerDamage);
				break;
			}
		}

		for (int y = 0; y <= bombSize; y++) {
			level.applyDamage(transform.get_x(), transform.get_y() + y, tileDamage);
		}
		for (int y = 0; y <= bombSize; y++) {
			if (dp.CellPosition.equals(transform.get_x(), transform.get_y() + y)) {
				dp.applyDamage(playerDamage);
				break;
			}
		}

		for (int y = 0; y <= bombSize; y++) {
			level.applyDamage(transform.get_x(), transform.get_y() - y, tileDamage);
		}
		for (int y = 0; y <= bombSize; y++) {
			if (dp.CellPosition.equals(transform.get_x(), transform.get_y() - y)) {
				dp.applyDamage(playerDamage);
				break;
			}
		}
	}
}
