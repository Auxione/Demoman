package Curio.SessionManagers.LogicManager;

import Curio.Console;
import Curio.Renderer.LogicObjectRenderer;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.DynamicWall;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.FireStarter;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.ItemSpawner;
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

	public void DynamicWall(int x, int y, int changeID) {
		Transform tr = new Transform(x*Constants.CellSize, y*Constants.CellSize);
		DynamicWall obj = new DynamicWall(worldManager.tileMap, tr, changeID);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);

		if (console != null) {
			String cmd = "DynamicWall: id: " + changeID + " created at:" + obj.cellCoordinate.getCellX() + "-" + obj.cellCoordinate.getCellY()+ ".";
			console.Add(0, cmd);
		}
	}

	public void ItemSpawner(int x, int y, int itemID) {
		Transform tr = new Transform(x*Constants.CellSize, y*Constants.CellSize);
		ItemSpawner obj = new ItemSpawner(itemManager, tr, itemID);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "ItemSpawner: itemID: " + itemID + " created at:" + obj.cellCoordinate.getCellX() + "-" + obj.cellCoordinate.getCellY() + ".";
			console.Add(0, cmd);
		}
	}

	public void Delay(int x, int y, int outputX, int outputY, int delayInSeconds) {
		CellCoordinate cc = new CellCoordinate(outputX, outputY);
		Transform tr = new Transform(x*Constants.CellSize, y*Constants.CellSize);
		Delay obj = new Delay(tr, cc, delayInSeconds);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "Delay: timer: " + delayInSeconds + " created at:" + obj.cellCoordinate.getCellX() + "-" + obj.cellCoordinate.getCellY() + ". Output set to: "
					+ outputX + "-" + outputY;
			console.Add(0, cmd);
		}
	}

	public void Movetrigger(int x, int y, int outputX, int outputY) {
		CellCoordinate cc = new CellCoordinate(outputX, outputY);
		Transform tr = new Transform(x*Constants.CellSize, y*Constants.CellSize);
		MoveTrigger obj = new MoveTrigger(tr, cc);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "MoveTrigger: created at:" + obj.cellCoordinate.getCellX() + "-" + obj.cellCoordinate.getCellY() + ". Output set to: " + obj.outputCC.getCellX() + "-" + obj.outputCC.getCellY();
			console.Add(0, cmd);
		}
	}

	public void Pushbutton(int x, int y, int outputX, int outputY) {
		CellCoordinate cc = new CellCoordinate(outputX, outputY);
		Transform tr = new Transform(x*Constants.CellSize, y*Constants.CellSize);
		Pushbutton obj = new Pushbutton(tr, cc);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "Pushbutton: created at:" + obj.cellCoordinate.getCellX() + "-" + obj.cellCoordinate.getCellY() + ". Output set to: " + obj.outputCC.getCellX() + "-" + obj.outputCC.getCellY();
			console.Add(0, cmd);
		}
	}

	public void Switchbutton(int x, int y, int outputX, int outputY) {
		CellCoordinate cc = new CellCoordinate(outputX, outputY);
		Transform tr = new Transform(x*Constants.CellSize, y*Constants.CellSize);
		Switchbutton obj = new Switchbutton(tr, cc);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "Switchbutton: created at:" + obj.cellCoordinate.getCellX() + "-" + obj.cellCoordinate.getCellY() + ". Output set to: " + obj.outputCC.getCellX() + "-" + obj.outputCC.getCellY();
			console.Add(0, cmd);
		}
	}

	public void PushToSwitch(int x, int y, int outputX, int outputY) {
		CellCoordinate cc = new CellCoordinate(outputX, outputY);
		Transform tr = new Transform(x*Constants.CellSize, y*Constants.CellSize);
		PushToSwitch obj = new PushToSwitch(tr, cc);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "PushToSwitch: created at:" + obj.cellCoordinate.getCellX() + "-" + obj.cellCoordinate.getCellY() + ". Output set to: " + obj.outputCC.getCellX() + "-" + obj.outputCC.getCellY();
			console.Add(0, cmd);
		}
	}

	public void FireStarter(int x, int y) {
		Transform tr = new Transform(x*Constants.CellSize, y*Constants.CellSize);
		FireStarter obj = new FireStarter(fireManager, tr);
		LogicObjectRenderer objR = new LogicObjectRenderer(obj);

		LogicManager.logicObjectList.add(obj);
		LogicManager.logicObjectRendererList.add(objR);
		if (console != null) {
			String cmd = "FireStarter: created at:" + obj.cellCoordinate.getCellX() + "-" + obj.cellCoordinate.getCellY();
			console.Add(0, cmd);
		}
	}
}
