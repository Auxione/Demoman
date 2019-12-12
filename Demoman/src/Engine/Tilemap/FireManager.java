package Engine.Tilemap;

import java.util.ArrayList;
import java.util.ListIterator;

import org.newdawn.slick.Graphics;

import Default.Constants;

public class FireManager {
	private ArrayList<Fire> fireList;
	private Tilemap level;

	public FireManager(Tilemap _Level) {
		fireList = new ArrayList<Fire>();
		level = _Level;
	}

	public void update() {
		ListIterator<Fire> f = fireList.listIterator();
		while (f.hasNext()) {
			Fire fire = f.next(); // must be called before you can call f.remove()
			fire.update();
			
			if (fire.burnt == true) {
				f.remove();
			}
		}
	}

	public void render(Graphics g) {
		for (Fire f : fireList) {
			f.render(g);
		}
	}

	public void create(int x, int y) {
		if (Constants.Tset.getFlammable(level.get_Tile(x, y))) {
			fireList.add(new Fire(level, x, y));
		}

	}
}
