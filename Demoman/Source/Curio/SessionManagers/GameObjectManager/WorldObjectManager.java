package Curio.SessionManagers.GameObjectManager;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;

import Curio.Lighting.RoundLightSource;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.WorldManager;
import Curio.Utilities.Math.Transform;

//yeniden incelenip manager parcalarina ayrilmali

public class WorldObjectManager {
	private ArrayList<WorldObject> worldObjects = new ArrayList<WorldObject>();
	
	private WorldManager worldManager;
	private PlantManager plantManager;

	public WorldObjectManager(WorldManager worldManager, PlantManager plantManager) {
		this.worldManager = worldManager;
		this.plantManager = plantManager;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(0, 0);
		for (WorldObject wO : worldObjects) {
			wO.render(g);
		}
		RenderToAlphaMap(g);
		
		g.popTransform();
	}

	public void RenderToAlphaMap(Graphics g) {
		g.clearAlphaMap();
		GL11.glBlendFunc(GL11.	GL_SRC_ALPHA, GL11.GL_ONE); // Blends the alpha map.
		for (WorldObject wO : worldObjects) {
			wO.renderToAlphaMap(g);
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

		for (WorldObject wO : worldObjects) {
			wO.updateAllDay();
		}
	}

	public ObjectTorch createTorchObject(Transform transform, int burnTime) {
		ObjectTorch obj = new ObjectTorch(worldManager.worldTime, transform, burnTime);
		worldObjects.add(obj);
		return obj;
	}

	public RoundLightSource createNewLightSource(Transform transform) {
		RoundLightSource r = new RoundLightSource().setColor(255, 255, 0, 255).setRadius(256);
		r.setTransform(transform);
		worldObjects.add(r);
		//roundLightSourceRenderer.add(new LightSourceRenderer(r));
		return r;
	}
}
