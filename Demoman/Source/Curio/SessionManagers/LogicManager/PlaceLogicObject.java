package Curio.SessionManagers.LogicManager;

import org.newdawn.slick.Color;

import Curio.Console;
import Curio.Renderer.LogicObjectRenderer;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.DynamicWall;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.FireStarter;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.ItemSpawner;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.LightBulb;
import Curio.SessionManagers.LogicManager.LogicObjects.Processor.Delay;
import Curio.SessionManagers.LogicManager.LogicObjects.Processor.PushToSwitch;
import Curio.SessionManagers.LogicManager.LogicObjects.Trigger.MoveTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.Trigger.Pushbutton;
import Curio.SessionManagers.LogicManager.LogicObjects.Trigger.Switchbutton;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class PlaceLogicObject {
	private ItemManager itemManager;
	private WorldManager worldManager;
	private FireManager fireManager;
	private Console console;

	public PlaceLogicObject(ItemManager itemManager, WorldManager worldManager, FireManager fireManager) {
		this.itemManager = itemManager;
		this.fireManager = fireManager;
		this.worldManager = worldManager;
	}

	public PlaceLogicObject setConsole(Console console) {
		this.console = console;
		return this;
	}

	public DynamicWall DynamicWall(int x, int y, int inputX, int inputY, int changeID) {
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		CellCoordinate inputCC = new CellCoordinate(inputX, inputY);
		DynamicWall obj = new DynamicWall(worldManager.tileMap, tr, inputCC, changeID);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);

		if (console != null) {
			String cmd = "DynamicWall: id: " + changeID + " created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY() + ".";
			console.Add(0, cmd);
		}
		return obj;
	}

	public ItemSpawner ItemSpawner(int x, int y, int inputX, int inputY, int itemID) {
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		CellCoordinate inputCC = new CellCoordinate(inputX, inputY);
		ItemSpawner obj = new ItemSpawner(itemManager, tr, inputCC, itemID);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "ItemSpawner: itemID: " + itemID + " created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY() + ".";
			console.Add(0, cmd);
		}
		return obj;
	}

	public Delay Delay(int x, int y, int inputX, int inputY, int delayInSeconds) {
		CellCoordinate inputCC = new CellCoordinate(inputX, inputY);
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		Delay obj = new Delay(tr, inputCC, delayInSeconds);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "Delay: timer: " + delayInSeconds + " created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY() + ". Input set to: " + inputX + "-" + inputY;
			console.Add(0, cmd);
		}
		return obj;
	}

	public MoveTrigger MoveTrigger(int x, int y) {
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		MoveTrigger obj = new MoveTrigger(tr);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "MoveTrigger: created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY();
			console.Add(0, cmd);
		}
		return obj;
	}

	public Pushbutton Pushbutton(int x, int y) {
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		Pushbutton obj = new Pushbutton(tr);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "Pushbutton: created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY();
			console.Add(0, cmd);
		}
		return obj;
	}

	public Switchbutton Switchbutton(int x, int y) {
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		Switchbutton obj = new Switchbutton(tr);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "Switchbutton: created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY();
			console.Add(0, cmd);
		}
		return obj;
	}

	public PushToSwitch PushToSwitch(int x, int y,int inputX, int inputY) {
		CellCoordinate inputCC = new CellCoordinate(inputX, inputY);
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		PushToSwitch obj = new PushToSwitch(tr, inputCC);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "PushToSwitch: created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY() + ". Input set to: " + obj.inputCC.getCellX() + "-"
					+ obj.inputCC.getCellY();
			console.Add(0, cmd);
		}
		return obj;
	}

	public FireStarter FireStarter(int x, int y,int inputX, int inputY) {
		CellCoordinate inputCC = new CellCoordinate(inputX, inputY);
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		FireStarter obj = new FireStarter(fireManager, tr, inputCC);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "FireStarter: created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY();
			console.Add(0, cmd);
		}
		return obj;
	}
	
	public LightBulb LightBulb(int x, int y,int inputX, int inputY,Color lightColor) {
		CellCoordinate inputCC = new CellCoordinate(inputX, inputY);
		Transform tr = new Transform(x * Constants.CellSize, y * Constants.CellSize);
		LightBulb obj = new LightBulb(tr, inputCC, lightColor);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "LightBulb: created at:" + obj.cellCoordinate.getCellX() + "-"
					+ obj.cellCoordinate.getCellY();
			console.Add(0, cmd);
		}
		return obj;
	}
}
