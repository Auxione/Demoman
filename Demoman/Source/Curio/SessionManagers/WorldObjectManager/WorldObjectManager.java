package Curio.SessionManagers.WorldObjectManager;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;

import Curio.Renderer.ObjectRenderer;
import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjects.ObjectTorch;
import Curio.Utilities.Math.Transform;

public class WorldObjectManager implements Renderer, AlphaMaskRenderer {
	public static ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>();
	private ArrayList<ObjectRenderer> worldObjectRenderer = new ArrayList<ObjectRenderer>();

	private WorldManager worldManager;
	private PlantManager plantManager;

	public WorldObjectManager(WorldManager worldManager, PlantManager plantManager) {
		this.worldManager = worldManager;
		this.plantManager = plantManager;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(0, 0);
		for (ObjectRenderer wO : worldObjectRenderer) {
			wO.render(g);
		}
		g.popTransform();
	}

	@Override
	public void renderAlphaMask(Graphics g) {
		g.clearAlphaMap();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // Blends the alpha map.
		for (ObjectRenderer wO : worldObjectRenderer) {
			wO.renderAlphaMask(g);
		}
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA); // Resets the blending factor.
		g.setDrawMode(Graphics.MODE_NORMAL); // Resets the Graphics draw mode.
		g.clearWorldClip();
	}

	public void update() {
		if (worldManager.worldTime.isDaytime() == true) {
			for (WorldObject wO : worldObjects) {
				wO.updateDayTime();
			}
		}

		else if (worldManager.worldTime.isNight() == true) {
			for (WorldObject wO : worldObjects) {
				wO.updateNight();
			}
		}
	}

	public ObjectTorch createTorchObject(Transform transform, int burnTime) {
		ObjectTorch obj = new ObjectTorch(worldManager.worldTime, transform, burnTime);
		ObjectRenderer objr = new ObjectRenderer(obj);

		worldObjects.add(obj);
		worldObjectRenderer.add(objr);
		return obj;
	}
}
