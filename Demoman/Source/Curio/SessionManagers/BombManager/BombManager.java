package Curio.SessionManagers.BombManager;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import Curio.ItemSystem.ItemMap;
import Curio.Renderer.Renderer;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.Utilities.CellCoordinate;
import Default.Player;

public class BombManager implements Renderer {
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();

	private ItemMap itemMap;

	private FireManager fireManager;
	private PlantManager plantManager;
	private WorldManager worldManager;

	public BombManager(WorldManager worldManager, FireManager fireManager, PlantManager plantManager, ItemMap itemMap) {
		this.worldManager = worldManager;
		this.fireManager = fireManager;
		this.plantManager = plantManager;
		this.itemMap = itemMap;
	}

	public void update(Player player) {
		Iterator<Bomb> b = bombList.iterator();

		while (b.hasNext()) {
			Bomb bomb = b.next(); // must be called before you can call f.remove()
			bomb.update();
			if (bomb.Exploded == true) {
				bomb.Effect(worldManager, fireManager, plantManager, itemMap, player);

				b.remove();
			}
		}
	}

	public void render(Graphics g) {
		for (Bomb bomb : bombList) {
			bomb.render(g);
		}
	}

	public boolean canPlace(CellCoordinate cpos) {
		if (bombList.isEmpty() == false) {
			for (Bomb b : bombList) {
				if (!b.cellPosition.equals(cpos)) {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	public void create(CellCoordinate cpos, int type, int time, int damage) {
		CellCoordinate bombpos = new CellCoordinate(cpos.getCellX(), cpos.getCellY());
		if (type == 1) {
			bombList.add(new Default(bombpos, time, damage));
		} else if (type == 2) {
			bombList.add(new Napalm(bombpos, time));
		}

	}
}
