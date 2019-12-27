package Curio.LogicMap;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import Curio.HUD.ConsoleDisplay;
import Curio.ItemMap.ItemMap;
import Curio.LogicMap.Trigger.*;
import Curio.LogicMap.Controller.*;
import Curio.LogicMap.Processor.*;
import Curio.Tilemap.FireManager;
import Curio.Tilemap.TileMap;
import Curio.Utilities.CellCoordinate;

public class LogicMap {
	ConsoleDisplay console;

	private int[][][] logicMap;
	private TileMap level;
	private ItemMap itemMap;

	public ArrayList<DynamicWall> dynamicwallList = new ArrayList<DynamicWall>();;
	public ArrayList<Pushbutton> pushbuttonList = new ArrayList<Pushbutton>();
	public ArrayList<Switchbutton> switchbuttonList = new ArrayList<Switchbutton>();
	public ArrayList<ItemSpawner> itemspawnerList = new ArrayList<ItemSpawner>();
	public ArrayList<Delay> delayList = new ArrayList<Delay>();
	public ArrayList<MoveTrigger> movetriggerList = new ArrayList<MoveTrigger>();
	public ArrayList<PushToSwitch> pushtoswitchList = new ArrayList<PushToSwitch>();
	public ArrayList<FireStarter> firestarterList = new ArrayList<FireStarter>();

	public LogicMap(TileMap lvl, ItemMap itemMap, ConsoleDisplay console) {
		this.console = console;
		this.itemMap = itemMap;

		level = lvl;
		// coordinate system x,y
		// logicMap[][][0] is one tick.0=off , 1=on;
		// logicMap[][][1] is switch.0=off , 1=on;
		logicMap = new int[lvl.get_MaxCellX()][lvl.get_MaxCellY()][2];

		// set value of the all channels to 0
		for (int x = 0; x < logicMap.length; x++) {
			for (int y = 0; y < logicMap[0].length; y++) {
				logicMap[x][y][0] = 0;
				logicMap[x][y][1] = 0;
			}
		}
		console.Add(0, "Logic: created");
	}

	public LogicMap(TileMap lvl, ItemMap itemMap) {
		this.console = null;
		this.itemMap = itemMap;
		level = lvl;
		// coordinate system x,y
		// logicMap[][][0] is one tick.0=off , 1=on;
		// logicMap[][][1] is switch.0=off , 1=on;
		logicMap = new int[lvl.get_MaxCellX()][lvl.get_MaxCellY()][2];

		// set value of the all channels to 0
		for (int x = 0; x < logicMap.length; x++) {
			for (int y = 0; y < logicMap[0].length; y++) {
				logicMap[x][y][0] = 0;
				logicMap[x][y][1] = 0;
			}
		}
	}

	public void put_DynamicWall(int x, int y, int changeid) {
		dynamicwallList.add(new DynamicWall(level, x, y, changeid));
		if (console != null) {
			String cmd = "DynamicWall: id: " + changeid + " created at:" + x + "-" + y + ".";
			console.Add(0, cmd);
		}
	}

	public void put_ItemSpawner(int x, int y, int id) {
		itemspawnerList.add(new ItemSpawner(x, y, id, itemMap));
		if (console != null) {
			String cmd = "ItemSpawner: id: " + id + " created at:" + x + "-" + y + ".";
			console.Add(0, cmd);
		}
	}

	public void put_Delay(int x, int y,CellCoordinate cpos, int time) {
		delayList.add(new Delay(x, y, cpos, time));
		if (console != null) {
			String cmd = "Delay: timer: " + time + " created at:" + x + "-" + y + ". Output set to: " + cpos.getCellX()
					+ "-" + cpos.getCellY();
			console.Add(0, cmd);
		}
	}

	public void put_MoveTrigger(int x, int y, CellCoordinate cpos) {
		movetriggerList.add(new MoveTrigger(x, y, cpos));
		if (console != null) {
			String cmd = "MoveTrigger: created at:" + x + "-" + y + ". Output set to: " + cpos.getCellX() + "-"
					+ cpos.getCellY();
			console.Add(0, cmd);
		}
	}

	public void put_PushButton(int x, int y, CellCoordinate cpos) {
		pushbuttonList.add(new Pushbutton(x, y, cpos));
		if (console != null) {
			String cmd = "Pushbutton: created at:" + x + "-" + y + ". Output set to: " + cpos.getCellX() + "-"
					+ cpos.getCellY();
			console.Add(0, cmd);
		}
	}

	public void put_SwitchButton(int x, int y,CellCoordinate cpos) {
		switchbuttonList.add(new Switchbutton(x, y, cpos));
		if (console != null) {
			String cmd = "Switchbutton: created at:" + x + "-" + y + ". Output set to: " +cpos.getCellX() + "-"
					+ cpos.getCellY();
			console.Add(0, cmd);
		}
	}

	public void put_PushToSwitch(int x, int y, CellCoordinate cpos) {
		pushtoswitchList.add(new PushToSwitch(x, y, cpos));
		if (console != null) {
			String cmd = "PushToSwitch: created at:" + x + "-" + y + ". Output set to: " + cpos.getCellX() + "-"
					+ cpos.getCellY();
			console.Add(0, cmd);
		}
	}

	public void put_FireStarter(FireManager m, int x, int y) {
		firestarterList.add(new FireStarter(m, x, y));
		if (console != null) {
			String cmd = "FireStarter: created at:" + x + "-" + y + ".";
			console.Add(0, cmd);
		}
	}

	public void update() {
		// triggers
		for (Pushbutton b : pushbuttonList) {
			b.update(this);
		}
		for (Switchbutton sw : switchbuttonList) {
			sw.update(this);
		}
		for (MoveTrigger mt : movetriggerList) {
			mt.update(this);
		}

		// processors
		for (PushToSwitch p : pushtoswitchList) {
			p.update(this);
		}
		for (Delay t : delayList) {
			t.update(this);
		}

		// controllers
		for (DynamicWall dw : dynamicwallList) {
			dw.update(this);
		}
		for (ItemSpawner is : itemspawnerList) {
			is.update(this);
		}
		for (FireStarter fs : firestarterList) {
			fs.update(this);
		}
		// clear tick channel
		clearTickChannels();
	}

	public void render(Graphics g) {
		for (Delay t : delayList) {
			t.render(g);
		}
		for (PushToSwitch p : pushtoswitchList) {
			p.render(g);
		}

		for (MoveTrigger m : movetriggerList) {
			m.render(g);
		}
		for (Pushbutton p : pushbuttonList) {
			p.render(g);
		}

		for (Switchbutton sw : switchbuttonList) {
			sw.render(g);
		}

		for (ItemSpawner is : itemspawnerList) {
			is.render(g);
		}

		for (FireStarter fs : firestarterList) {
			fs.render(g);
		}
	}

	public void clearTickChannels() {
		for (int x = 0; x < logicMap.length; x++) {
			for (int y = 0; y < logicMap[0].length; y++) {
				logicMap[x][y][0] = 0;
			}
		}
	}

	public void sendTick(CellCoordinate cpos, boolean state) {
		if (state == true) {
			logicMap[cpos.getCellX()][cpos.getCellY()][0] = 1;
		}
	}

	public void setState(CellCoordinate cpos, boolean state) {
		if (state == true) {
			logicMap[cpos.getCellX()][cpos.getCellY()][1] = 1;
		} else if (state == false) {
			logicMap[cpos.getCellX()][cpos.getCellY()][1] = 0;
		}
	}

	public boolean getState(CellCoordinate cpos) {
		if (logicMap[cpos.getCellX()][cpos.getCellY()][0] == 1) {
			return true;
		} else if (logicMap[cpos.getCellX()][cpos.getCellY()][1] == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public int[][][] getMap() {
		return logicMap;
	}
}
