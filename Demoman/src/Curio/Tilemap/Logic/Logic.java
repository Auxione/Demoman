package Curio.Tilemap.Logic;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import Curio.Tilemap.FireManager;
import Curio.Tilemap.Tilemap;
import Curio.Tilemap.Logic.Controller.DynamicWall;
import Curio.Tilemap.Logic.Controller.FireStarter;
import Curio.Tilemap.Logic.Controller.ItemSpawner;
import Curio.Tilemap.Logic.Processor.Delay;
import Curio.Tilemap.Logic.Processor.PushToSwitch;
import Curio.Tilemap.Logic.Trigger.MoveTrigger;
import Curio.Tilemap.Logic.Trigger.Pushbutton;
import Curio.Tilemap.Logic.Trigger.Switchbutton;
import Curio.Utilities.Transform;

public abstract class Logic {

	private static int[][][] logicMap;
	private static Tilemap level;

	public static ArrayList<DynamicWall> dynamicwallList = new ArrayList<DynamicWall>();;
	public static ArrayList<Pushbutton> pushbuttonList = new ArrayList<Pushbutton>();
	public static ArrayList<Switchbutton> switchbuttonList = new ArrayList<Switchbutton>();
	public static ArrayList<ItemSpawner> itemspawnerList = new ArrayList<ItemSpawner>();
	public static ArrayList<Delay> delayList = new ArrayList<Delay>();
	public static ArrayList<MoveTrigger> movetriggerList = new ArrayList<MoveTrigger>();
	public static ArrayList<PushToSwitch> pushtoswitchList = new ArrayList<PushToSwitch>();
	public static ArrayList<FireStarter> firestarterList = new ArrayList<FireStarter>();

	public static void createLogicMap(Tilemap lvl) {
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

	public static void put_DynamicWall(int x, int y, int changeid) {
		dynamicwallList.add(new DynamicWall(level, x, y, changeid));
	}

	public static void put_ItemSpawner(int x, int y, int id) {
		itemspawnerList.add(new ItemSpawner(x, y, id));
	}

	public static void put_Delay(int x, int y, Transform output, int time) {
		delayList.add(new Delay(x, y, output, time));
	}

	public static void put_MoveTrigger(int x, int y, Transform output) {
		movetriggerList.add(new MoveTrigger(x, y, output));
	}

	public static void put_PushButton(int x, int y, Transform output) {
		pushbuttonList.add(new Pushbutton(x, y, output));
	}

	public static void put_SwitchButton(int x, int y, Transform output) {
		switchbuttonList.add(new Switchbutton(x, y, output));
	}

	public static void put_PushToSwitch(int x, int y, Transform output) {
		pushtoswitchList.add(new PushToSwitch(x, y, output));
	}

	public static void put_FireStarter(FireManager m, int x, int y) {
		firestarterList.add(new FireStarter(m, x, y));
	}

	public static void mainloop() {
		// triggers
		for (Pushbutton b : pushbuttonList) {
			b.update();
		}
		for (Switchbutton sw : switchbuttonList) {
			sw.update();
		}
		for (MoveTrigger mt : movetriggerList) {
			mt.update();
		}

		// processors
		for (PushToSwitch p : pushtoswitchList) {
			p.update();
		}
		for (Delay t : delayList) {
			t.update();
		}

		// controllers
		for (DynamicWall dw : dynamicwallList) {
			dw.update();
		}
		for (ItemSpawner is : itemspawnerList) {
			is.update();
		}
		for (FireStarter fs : firestarterList) {
			fs.update();
		}
		// clear tick channel
		Logic.clearTickChannels();
	}

	public static void mainRender(Graphics g) {
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

	public static void clearTickChannels() {
		for (int x = 0; x < logicMap.length; x++) {
			for (int y = 0; y < logicMap[0].length; y++) {
				logicMap[x][y][0] = 0;
			}
		}
	}

	public void sendTick(Transform outputTile, boolean state) {
		if (state == true) {
			logicMap[outputTile.get_x()][outputTile.get_y()][0] = 1;
		}
	}

	public void setState(Transform outputTile, boolean state) {
		if (state == true) {
			logicMap[outputTile.get_x()][outputTile.get_y()][1] = 1;
		} else if (state == false) {
			logicMap[outputTile.get_x()][outputTile.get_y()][1] = 0;
		}
	}

	public boolean getState(Transform inputTile) {
		if (logicMap[inputTile.get_x()][inputTile.get_y()][0] == 1) {
			return true;
		} else if (logicMap[inputTile.get_x()][inputTile.get_y()][1] == 1) {
			return true;
		} else {
			return false;
		}
	}

	public abstract void keyEvent();

	public abstract void update();

	public abstract void render(Graphics g);

}
