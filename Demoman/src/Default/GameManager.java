package Default;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Engine.*;
import Engine.HUD.BarDisplay;
import Engine.Items.Item;
import Engine.Physics.DynamicObject;
import Engine.Tilemap.*;
import Engine.Tilemap.Bomb.BombManager;
import Engine.Tilemap.Logic.Logic;
import Engine.Tilemap.Logic.Controller.ItemSpawner;
import Engine.Tilemap.Logic.Trigger.MoveTrigger;
import Engine.Tilemap.Logic.Trigger.Pushbutton;
import Engine.Tilemap.Logic.Trigger.Switchbutton;

public class GameManager {
	private ArrayList<Controller> controllerList;

	private HashMap<Controller, DynamicPlayer> playerList;
	private HashMap<Controller, BarDisplay> hpBarList;
	private FireManager fm;
	private BombManager bm;

	Tilemap currentLevel;

	// what should manager do?
	// create levels and put interactive elements
	// check the player surroundings for interactive objects for player use
	// check if the player use "bomb" item.if so place a bomb. Also check if the
	// bomb is exploded.
	// check the health values of the players.

	GameManager(Tilemap l) {
		currentLevel = l;

		controllerList = new ArrayList<Controller>();
		playerList = new HashMap<Controller, DynamicPlayer>();
		hpBarList = new HashMap<Controller, BarDisplay>();

		fm = new FireManager(currentLevel);
		bm = new BombManager(fm, currentLevel);
	}

	public void createLevel() {
		// create new level from width and height
		currentLevel.create_BlankLevel();
	}

	public void createPlayer(float posx, float posy, int mode) {
		// create new controller
		controllerList.add(new Controller(mode));
		// get the last created controller
		Controller ctrl = controllerList.get(controllerList.size() - 1);
		// assign player and bomb to last created controller
		playerList.put(ctrl, new DynamicPlayer(currentLevel, posx, posy));
		hpBarList.put(ctrl, new BarDisplay(playerList.get(ctrl).Position, 60, 10, Color.red));

	}

	public void update(int delta) {
		Logic.mainloop();

		for (Controller c : controllerList) {
			playerList.get(c).movementUpdate();
		}

		for (DynamicObject d : DynamicObject.dynamicObjectList) {
			d.updatePhysics(delta);
		}

		fm.update();
		bm.update();

		for (Controller c : controllerList) {
			// check if the item taken by this player
			for (ItemSpawner is : Logic.itemspawnerList) {
				if (Functions.isOnTop(playerList.get(c).CellPosition, is.transform)) {
					// is.itemtaken();
				}
			}
			// check if the movetrigger activated by this player
			for (MoveTrigger m : Logic.movetriggerList) {
				if (Functions.isOnTop(playerList.get(c).CellPosition, m.transform)) {
					m.keyEvent();
				}
			}
			c.ActionEnd();
		}
	}

	void render(Graphics g) {
		// render level
		currentLevel.render(g);
		Logic.mainRender(g);

		Item.mainRender(g);

		fm.render(g);
		bm.render(g);

		for (Controller c : controllerList) {
			playerList.get(c).render(g);
			hpBarList.get(c).render(g);
		}

	}

	void KeyPressed(int key, char chr) {
		for (Controller c : controllerList) {
			c.Pressed();
			playerList.get(c).MovementDir(chr, 1);

			if (c.ActionBomb == true) {
				bm.create(playerList.get(c).CellPosition, 1, 2, 1500);
			}

			if (c.ActionUse == true) {
				fm.create(playerList.get(c).CellPosition.get_x(), playerList.get(c).CellPosition.get_y());
			}

			// check if the player activates
			for (Switchbutton sw : Logic.switchbuttonList) {
				if (c.ActionUse == true && Functions.isOnTop(playerList.get(c).CellPosition, sw.transform)) {
					sw.keyEvent();
				}
			}
			// check if the player activates
			for (Pushbutton b : Logic.pushbuttonList) {
				if (c.ActionUse == true && Functions.isOnTop(playerList.get(c).CellPosition, b.transform)) {
					b.keyEvent();
				}
			}
		}
	}

	void KeyReleased(int key, char chr) {
		for (Controller c : controllerList) {

			playerList.get(c).MovementDir(chr, 0);
		}
	}
}