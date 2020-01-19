package Curio.SessionManagers.FireManager;

import java.util.ArrayList;
import java.util.ListIterator;

import org.newdawn.slick.Graphics;

import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.TileList;
import Curio.SessionManagers.WorldManager.WorldManager;
import Default.Player;

public class FireManager implements Runnable, Renderer {
	private ArrayList<Fire> fireList;
	private WorldManager worldManager;
	private PlantManager plantManager;

	public FireManager(WorldManager worldManager, PlantManager plantManager) {
		this.fireList = new ArrayList<Fire>();
		this.worldManager = worldManager;
		this.plantManager = plantManager;
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
			int tileid = worldManager.tileMap.getCell(cellx, celly, 0);
			if (TileList.getTile(tileid).isFlammable() == true) {
				f.add(new Fire(worldManager.tileMap, cellx, celly));
			}
		}
		for (int y = -1; y <= 1; y++) {
			int cellx = fire.cellPosition.getCellX();
			int celly = fire.cellPosition.getCellY() + y;
			int tileid = worldManager.tileMap.getCell(cellx, celly, 0);
			if (TileList.getTile(tileid).isFlammable() == true) {
				f.add(new Fire(worldManager.tileMap, cellx, celly));
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
			if (TileList.getTile(worldManager.tileMap.getCell(x, y, 0)).isFlammable() == true) {
				fireList.add(new Fire(worldManager.tileMap, x, y));
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
