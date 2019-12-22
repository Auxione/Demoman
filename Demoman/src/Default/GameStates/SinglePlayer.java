package Default.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Viewport;
import Curio.HUD.BarDisplay;
import Curio.HUD.ConsoleDisplay;
import Curio.HUD.InventoryDisplay;
import Curio.ItemMap.Inventory;
import Curio.ItemMap.ItemMap;
import Curio.LogicMap.LogicMap;
import Curio.Physics.DynamicObject;
import Curio.Physics.TilemapCollision;
import Curio.Tilemap.FireManager;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.Controller;
import Default.Main;
import Default.Player;

public class SinglePlayer {

	private Controller controller;
	private Player player;
	private TilemapCollision collision;
	private Viewport viewPort;

	private Inventory playerInventory;
	private InventoryDisplay inventoryDisplay;
	private BarDisplay hpBar, foodBar;

	private FireManager fm;
	private BombManager bm;

	TileMap tileMap;
	LogicMap logicMap;
	ItemMap itemMap;
	ConsoleDisplay console;

	public SinglePlayer(ConsoleDisplay console) {
		this.console = console;
		console.Add(0, "Creating Singleplayer Game");

		tileMap = new TileMap(30, 30, Constants.CellSize, console);
		tileMap.create_BlankLevel();
		itemMap = new ItemMap(tileMap);
		logicMap= new LogicMap(tileMap, itemMap);

		player = new Player(tileMap, 4, 4);
		controller = new Controller(1, player);
		collision = new TilemapCollision(tileMap, player);
		playerInventory = new Inventory(itemMap, player, 6);

		viewPort = new Viewport(Main.DisplayWidth, Main.DisplayHeight);
		hpBar = new BarDisplay(0, 0, 100, 10, Color.red);
		foodBar = new BarDisplay(0, 0, 100, 10, Color.green);
		inventoryDisplay = new InventoryDisplay(500, 500, playerInventory, itemMap);

		fm = new FireManager(tileMap);
		bm = new BombManager(fm, tileMap);

		itemMap.put(4, 4, 3);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
	}

	public void update(int delta) {
		controller.Pressed();
		controller.update();

		logicMap.mainloop();
		fm.update(player);
		bm.update(player);

		player.loop();
		viewPort.move(player.CellPosition);

		hpBar.Percentage(player.getCurrentHealth(), player.getMaxHealth());
		foodBar.Percentage(player.getCurrentFood(), player.getMaxFood());

		collision.checkCollisions();

		for (DynamicObject d : DynamicObject.dynamicObjectList) {
			d.updatePhysics(delta);
		}

		inventoryDisplay.inputEvent(Main.input);

		actions();

		controller.ActionEnd();
	}

	public void render(Graphics g) {
		g.pushTransform();
		viewPort.renderStart(g);
		// render level
		tileMap.render(g, viewPort);
		logicMap.mainRender(g);
		itemMap.mainRender(g);

		fm.render(g);
		bm.render(g);

		player.render(g);

		viewPort.renderEnd(g);
		//

		inventoryDisplay.render(g);

		g.translate(40, 500);
		hpBar.render(g);
		g.translate(0, 15);
		foodBar.render(g);
		g.popTransform();
	}

	public void KeyPressed(int key, char chr) {
	}

	public void KeyReleased(int key, char chr) {
	}

	private void actions() {
		if (controller.ActionTake == true) {
			playerInventory.take();
		}

		if (controller.ActionUse == true) {
			playerInventory.useSelf(bm, tileMap);
		}

		if (controller.ActionSwitchItem == true) {
			playerInventory.switchCurrentItem();
		}

		if (controller.ActionDrop == true) {
			playerInventory.drop();
		}
	}
}
