package Curio.Tilemap;

import java.util.ArrayList;
import java.util.ListIterator;

import org.newdawn.slick.Graphics;

import Default.DynamicPlayer;

public class FireManager implements Runnable{
	private ArrayList<Fire> fireList;
	private Tilemap level;

	public FireManager(Tilemap _Level) {
		fireList = new ArrayList<Fire>();
		level = _Level;
	}

	public void update(DynamicPlayer dp) {
		ListIterator<Fire> f = fireList.listIterator();
		while (f.hasNext()) {
			Fire fire = f.next(); // must be called before you can call f.remove()
			fire.update(dp);
			
			if (fire.burnt == true) {
				f.remove();
				spread(f,fire);
			}
			
		}
	}

	void spread(ListIterator<Fire> f,Fire fire) {
		for (int x = -1; x <= 1; x++) {
			int cellx = fire.transform.get_x()+x;
			int celly = fire.transform.get_y();
			int tileid = level.get_Tile(cellx, celly);
			if (Tileset.canBurn(tileid) == true) {
				f.add(new Fire(level, cellx, celly));
			}
		}
		for (int y = -1; y <= 1; y++) {
			int cellx = fire.transform.get_x();
			int celly = fire.transform.get_y()+y;
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
				if (!f.transform.equals(x, y)) {
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
