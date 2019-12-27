package Curio.Tilemap;

import java.util.ArrayList;
import java.util.ListIterator;

import org.newdawn.slick.Graphics;

import Curio.Tileset;
import Default.Player;

public class FireManager implements Runnable {
	private ArrayList<Fire> fireList;
	private TileMap level;

	public FireManager(TileMap _Level) {
		fireList = new ArrayList<Fire>();
		level = _Level;
	}

	public void update(Player dp) {
		ListIterator<Fire> f = fireList.listIterator();
		while (f.hasNext()) {
			Fire fire = f.next(); // must be called before you can call f.remove()
			fire.update(dp);

			if (fire.burnt == true) {
				f.remove();
				spread(f, fire);
			}

		}
	}

	void spread(ListIterator<Fire> f, Fire fire) {
		for (int x = -1; x <= 1; x++) {
			int cellx = fire.cellPosition.getCellX() + x;
			int celly = fire.cellPosition.getCellY();
			int tileid = level.get_Tile(cellx, celly);
			if (Tileset.canBurn(tileid) == true) {
				f.add(new Fire(level, cellx, celly));
			}
		}
		for (int y = -1; y <= 1; y++) {
			int cellx = fire.cellPosition.getCellX();
			int celly = fire.cellPosition.getCellY() + y;
			int tileid = level.get_Tile(cellx, celly);
			if (Tileset.canBurn(tileid) == true) {
				f.add(new Fire(level, cellx, celly));
			}
		}
	}

	public void render(Graphics g) {
		for (Fire f : fireList) {
			f.render(g);
		}
	}

	public void create(int x, int y) {
		boolean canPlace = false;
		if (fireList.isEmpty() == false) {
			for (Fire f : fireList) {
				if (!f.cellPosition.equals(x, y, 0)) {
					canPlace = true;
					break;
				}
			}
		} else {
			canPlace = true;
		}
		if (canPlace == true) {
			if (Tileset.canBurn(level.get_Tile(x, y)) == true) {
				fireList.add(new Fire(level, x, y));
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
