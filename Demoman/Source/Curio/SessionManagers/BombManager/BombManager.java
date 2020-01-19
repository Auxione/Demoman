package Curio.SessionManagers.BombManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.newdawn.slick.Graphics;

import Curio.Renderer.BombRenderer;
import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.AnimationRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.BombManager.Bombs.DefaultBomb;
import Curio.SessionManagers.BombManager.Bombs.NapalmBomb;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.Utilities.Math.Transform;
import Default.Player;

public class BombManager implements Renderer, AnimationRenderer, AlphaMaskRenderer {
	private ArrayList<Bomb> bombList = new ArrayList<Bomb>();
	private HashMap<Bomb, BombRenderer> bombRendererList = new HashMap<Bomb, BombRenderer>();

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
			if (bomb.isExploded == true) {
				bomb.Effect(worldManager, fireManager, plantManager, itemMap, player);
				if (bombRendererList.get(bomb).ExplosionAnimation.isStopped() == true) {
					bombList.remove(b);
					bombRendererList.remove(b);
				}
			}
		}
	}

	public void create(Transform transform, int type, int time, int damage) {
		if (type == 1) {
			Bomb b = new DefaultBomb(worldManager, new Transform(transform), time, damage);
			BombRenderer br = new BombRenderer(b);

			bombList.add(b);
			bombRendererList.put(b, br);
		}
		
		else if (type == 2) {
			Bomb b = new NapalmBomb(worldManager, new Transform(transform), time, damage);
			BombRenderer br = new BombRenderer(b);

			bombList.add(b);
			bombRendererList.put(b, br);
		}
	}

	public void render(Graphics g) {
		for (Entry<Bomb, BombRenderer> br : bombRendererList.entrySet()) {
			br.getValue().render(g);
		}
	}

	@Override
	public void renderAnimation(Graphics g) {
		for (Entry<Bomb, BombRenderer> br : bombRendererList.entrySet()) {
			br.getValue().renderAnimation(g);
		}
	}

	@Override
	public void renderAlphaMask(Graphics g) {
		for (Entry<Bomb, BombRenderer> br : bombRendererList.entrySet()) {
			br.getValue().renderAlphaMask(g);
		}
	}

	public boolean canPlace(Transform transform) {
		if (bombList.isEmpty() == false) {
			for (Bomb bomb : bombList) {
				if (!bomb.transform.position.equals(transform.position)) {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

}
