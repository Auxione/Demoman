package Default;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.*;
import Curio.HUD.BarDisplay;
import Curio.Items.Item;
import Curio.Physics.DynamicObject;
import Curio.Physics.TilemapCollision;
import Curio.Tilemap.*;
import Curio.Tilemap.Bomb.BombManager;
import Curio.Tilemap.Logic.Logic;
import Curio.Tilemap.Logic.Trigger.MoveTrigger;
import Curio.Tilemap.Logic.Trigger.Pushbutton;
import Curio.Tilemap.Logic.Trigger.Switchbutton;

public class GameManager {
	private ArrayList<Controller> controllerList;

	private HashMap<Controller, DynamicPlayer> playerList;
	private HashMap<Controller, BarDisplay> hpBarList;
	private HashMap<Controller, TilemapCollision> collisionList;
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
		collisionList = new HashMap<Controller, TilemapCollision>();

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
		collisionList.put(ctrl, new TilemapCollision(currentLevel, playerList.get(ctrl)));
	}

	public void update(int delta) {
		Logic.mainloop();

		for (Controller c : controllerList) {
			fm.update(playerList.get(c));
			bm.update(playerList.get(c));
			
			playerList.get(c).loop();
			Item.itemScan(playerList.get(c));
			hpBarList.get(c).Percentage(playerList.get(c).getCurrentHealth(), playerList.get(c).getMaxHealth());
			collisionList.get(c).checkCollisions(playerList.get(c).psize);
		}

		for (DynamicObject d : DynamicObject.dynamicObjectList) {
			d.updatePhysics(delta);
		}

		for (Controller c : controllerList) {
			// check if the movetrigger activated by this player
			for (MoveTrigger m : Logic.movetriggerList) {
				m.update();
				if (Functions.isOnTop(playerList.get(c).CellPosition, m.transform) == true && m.activated == false) {
					m.activated = true;
				} else if (Functions.isOnTop(playerList.get(c).CellPosition, m.transform) == false) {
					m.activated = false;
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
				bm.create(playerList.get(c).CellPosition, playerList.get(c).Team, playerList.get(c).bombType,
						playerList.get(c).bombTimer);
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