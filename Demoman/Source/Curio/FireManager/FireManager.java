package Curio.FireManager;

import java.util.ArrayList;
import java.util.ListIterator;

import org.newdawn.slick.Graphics;

import Curio.TileList;
import Curio.TileMap;
import Curio.Renderer.Renderer;
import Default.Player;

public class FireManager implements Runnable, Renderer {
	private ArrayList<Fire> fireList;
	private TileMap tileMap;

	public FireManager(TileMap _Level) {
		this.fireList = new ArrayList<Fire>();
		this.tileMap = tileMap;
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
			int tileid = tileMap.getTile(cellx, celly, 0);
			if (TileList.getTile(tileid).isFlammable() == true) {
				f.add(new Fire(tileMap, cellx, celly));
			}
		}
		for (int y = -1; y <= 1; y++) {
			int cellx = fire.cellPosition.getCellX();
			int celly = fire.cellPosition.getCellY() + y;
			int tileid = tileMap.getTile(cellx, celly, 0);
			if (TileList.getTile(tileid).isFlammable() == true) {
				f.add(new Fire(tileMap, cellx, celly));
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
			if (TileList.getTile(tileMap.getTile(x, y, 0)).isFlammable() == true) {
				fireList.add(new Fire(tileMap, x, y));
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
