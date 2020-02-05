package Curio.SessionManagers.LogicManager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.Physics.Time;
import Curio.Renderer.LogicObjectRenderer;
import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.AnimationRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.SessionManagers.LogicManager.Interfaces.LogicController;
import Curio.SessionManagers.LogicManager.Interfaces.LogicProcessor;
import Curio.SessionManagers.LogicManager.Interfaces.LogicTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.Utilities.CellCoordinate;

public class LogicManager implements Renderer, AlphaMaskRenderer, AnimationRenderer {
	public static ArrayList<LogicObject> logicObjectList = new ArrayList<LogicObject>();
	public static ArrayList<LogicObjectRenderer> logicObjectRendererList = new ArrayList<LogicObjectRenderer>();

	private Console console;
	public PlaceLogicObject placeObject;
	public LogicMap logicMap;

	private Time currentTime;
	private LogicChannels logicChannels;

	public LogicManager(WorldManager worldManager, ItemManager itemManager, FireManager fireManager,
			LogicMap logicMap) {
		this.placeObject = new PlaceLogicObject(itemManager, worldManager, fireManager);
		this.logicMap = logicMap;
		this.currentTime = worldManager.worldTime.getTime();
	}

	public LogicManager setConsole(Console console) {
		this.console = console;
		this.placeObject.setConsole(console);
		return this;
	}

	public void update() {
		for (LogicObject b : logicObjectList) {
			if (b instanceof LogicTrigger) {
				((LogicTrigger) b).update(logicMap);
			}
		}
		for (LogicObject b : logicObjectList) {
			if (b instanceof LogicProcessor) {
				((LogicProcessor) b).update(logicMap, currentTime);
			}
		}
		for (LogicObject b : logicObjectList) {
			if (b instanceof LogicController) {
				((LogicController) b).update(logicMap);
			}
		}
		logicMap.clearTickCells();
	}

	@Override
	public void render(Graphics g) {
		for (LogicObjectRenderer objR : logicObjectRendererList) {
			objR.render(g);
		}
	}

	@Override
	public void renderAlphaMask(Graphics g) {
		for (LogicObjectRenderer objR : logicObjectRendererList) {
			objR.renderAlphaMask(g);
		}
	}

	@Override
	public void renderAnimation(Graphics g) {
		for (LogicObjectRenderer objR : logicObjectRendererList) {
			objR.renderAnimation(g);
		}
	}

	public void onTopTrigger(CellCoordinate cellCoordinate) {
		for (LogicObject b : logicObjectList) {
			if (b instanceof LogicTrigger) {
				if (b.cellCoordinate.equals(cellCoordinate)) {
					((LogicTrigger) b).onTopEvent(true);
				} else if (!b.cellCoordinate.equals(cellCoordinate)) {
					((LogicTrigger) b).onTopEvent(false);
				}
			}
		}
	}

	public void activateTrigger(CellCoordinate cellCoordinate) {
		for (LogicObject b : logicObjectList) {
			if (b instanceof LogicTrigger) {
				if (b.cellCoordinate.equals(cellCoordinate)) {
					((LogicTrigger) b).keyEvent(true);
				}
			}
		}
	}
}
