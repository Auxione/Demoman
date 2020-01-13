package Curio.BombManager;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import Curio.FluidMap;
import Curio.TileMap;
import Curio.FireManager.FireManager;
import Curio.ItemSystem.ItemMap;
import Curio.PlantSystem.PlantMap;
import Curio.Renderer.Renderer;
import Curio.Utilities.CellCoordinate;
import Default.Player;

public class BombManager implements Renderer{
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();

	private TileMap tileMap;
	private FireManager fireManager;
	private ItemMap itemMap;
	private PlantMap plantMap;
	private FluidMap fluidMap;

	public BombManager(FireManager fireManager, TileMap tileMap, ItemMap itemMap, PlantMap plantMap,
			FluidMap fluidMap) {
		this.tileMap = tileMap;
		this.itemMap = itemMap;
		this.plantMap = plantMap;
		this.fluidMap = fluidMap;
		this.fireManager = fireManager;
	}

	public void update(Player player) {
		Iterator<Bomb> b = bombList.iterator();

		while (b.hasNext()) {
			Bomb bomb = b.next(); // must be called before you can call f.remove()
			bomb.update();
			if (bomb.Exploded == true) {
				bomb.Effect(player, tileMap, itemMap, plantMap, fluidMap);

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

	public void create(CellCoordinate cpos, int type, int time,int damage) {
		CellCoordinate bombpos = new CellCoordinate(cpos.getCellX(), cpos.getCellY());
		if (type == 1) {
			bombList.add(new Default(bombpos, time,damage));
		} else if (type == 2) {
			bombList.add(new Napalm(bombpos, time,fireManager));
		}

	}
}
