package Default;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Viewport;
import Curio.HUD.BarDisplay;
import Curio.HUD.InventoryDisplay;
import Curio.Items.Inventory;
import Curio.Items.Item;
import Curio.Physics.DynamicObject;
import Curio.Physics.TilemapCollision;
import Curio.Tilemap.FireManager;
import Curio.Tilemap.Tilemap;
import Curio.Tilemap.Bomb.BombManager;
import Curio.Tilemap.Logic.Logic;

public class GameManager {
	private Controller controller;
	private DynamicPlayer player;
	private TilemapCollision collision;
	private Viewport viewPort;

	private Inventory playerInventory;
	private InventoryDisplay inventoryDisplay;
	private BarDisplay hpBar, foodBar;

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

		controller = new Controller(1);
		player = new DynamicPlayer(currentLevel, 200, 200);
		collision = new TilemapCollision(currentLevel, player);

		hpBar = new BarDisplay(0, 0, 100, 10, Color.red);
		foodBar = new BarDisplay(0, 0, 100, 10, Color.green);

		playerInventory = new Inventory(player, 3);
		inventoryDisplay = new InventoryDisplay(500, 500, playerInventory);

		viewPort = new Viewport(Main.DisplayWidth, Main.DisplayHeight);


		fm = new FireManager(currentLevel);
		bm = new BombManager(fm, currentLevel);
	}

	public void createLevel() {
		// create new level from width and height
		currentLevel.create_BlankLevel();
	}

	public void update(int delta) {
		controller.Pressed();

		Logic.mainloop();
		fm.update(player);
		bm.update(player);

		player.loop();
		viewPort.move(player.Position);

		hpBar.Percentage(player.getCurrentHealth(), player.getMaxHealth());
		foodBar.Percentage(player.getCurrentFood(), player.getMaxFood());

		collision.checkCollisions(player.psize);

		for (DynamicObject d : DynamicObject.dynamicObjectList) {
			d.updatePhysics(delta);
		}
		
		inventoryDisplay.inputEvent(Main.input);
		actions();

		controller.ActionEnd();
	}

	void render(Graphics g) {
		viewPort.renderStart(g);
		// render level
		currentLevel.render(g, viewPort);
		Logic.mainRender(g);
		Item.mainRender(g);

		fm.render(g);
		bm.render(g);

		player.render(g);

		viewPort.renderEnd(g);
		//
		inventoryDisplay.render(g);
		hpBar.render(g);
		foodBar.render(g);

	}

	void KeyPressed(int key, char chr) {
		player.MovementDir(chr, 1);

	}

	void KeyReleased(int key, char chr) {
		player.MovementDir(chr, 0);
	}

	private void actions() {
		if (controller.ActionTake == true) {
			playerInventory.take();
		}

		if (controller.ActionUse == true) {
			playerInventory.useSelf();
		}

		if (controller.ActionSwitchItem == true) {
			playerInventory.switchCurrentItem();
		}

		if (controller.ActionDrop == true) {
			playerInventory.drop();
		}
	}
}
