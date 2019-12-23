package Default.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.Viewport;
import Curio.HUD.BarDisplay;
import Curio.HUD.ConsoleDisplay;
import Curio.HUD.InventoryDisplay;
import Curio.HUD.TextDisplay;
import Curio.ItemMap.Inventory;
import Curio.ItemMap.ItemMap;
import Curio.LogicMap.LogicMap;
import Curio.Physics.DynamicObject;
import Curio.Physics.TilemapCollision;
import Curio.PlantMap.PlantMap;
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
	private TextDisplay PlayerName;
	private FireManager fm;
	private BombManager bm;

	private TileMap tileMap;
	private LogicMap logicMap;
	private ItemMap itemMap;
	private PlantMap plantMap;
	private ConsoleDisplay console;

	public SinglePlayer(ConsoleDisplay console) {
		this.console = console;
		console.Add(0, "Creating Singleplayer Game");

		tileMap = new TileMap(30, 30, Constants.CellSize, console);
		tileMap.create_BlankLevel();
		itemMap = new ItemMap(tileMap, 15);
		logicMap = new LogicMap(tileMap, itemMap);
		plantMap = new PlantMap(tileMap, itemMap);

		player = new Player(tileMap, 4, 4);
		controller = new Controller(1, player);
		collision = new TilemapCollision(tileMap, player);
		playerInventory = new Inventory(itemMap, player, 4, 5);

		viewPort = new Viewport(Main.DisplayWidth, Main.DisplayHeight);
		hpBar = new BarDisplay(0, 0, 100, 10, Color.red);
		foodBar = new BarDisplay(0, 0, 100, 10, Color.green);
		inventoryDisplay = new InventoryDisplay(500, 500, playerInventory, itemMap);
		PlayerName = new TextDisplay(20, 20, 0, 0, "Name", "cem");

		fm = new FireManager(tileMap);
		bm = new BombManager(fm, tileMap);

		itemMap.put(4, 4, 3);

		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		itemMap.put(4, 5, 5);
		
		itemMap.put(2, 2, 9);
		itemMap.put(2, 2, 9);
		itemMap.put(2, 2, 9);
		itemMap.put(2, 2, 9);
		itemMap.put(2, 2, 9);
		
		plantMap.put(4, 6, 1);
		plantMap.put(4, 7, 1);
		plantMap.put(4, 8, 1);
		plantMap.put(4, 9, 1);
		plantMap.put(5, 6, 1);
		plantMap.put(5, 7, 1);
		plantMap.put(5, 8, 1);
		plantMap.put(5, 9, 1);

		tileMap.set_Tile(6, 6, 6);
		tileMap.set_Tile(6, 7, 6);
		tileMap.set_Tile(6, 8, 6);
		tileMap.set_Tile(6, 9, 6);
		tileMap.set_Tile(7, 6, 6);
		tileMap.set_Tile(7, 7, 6);
		tileMap.set_Tile(7, 8, 6);
		tileMap.set_Tile(7, 9, 6);

		plantMap.put(6, 6, 2);
		plantMap.put(6, 7, 2);
		plantMap.put(6, 8, 2);
		plantMap.put(6, 9, 2);
		plantMap.put(7, 6, 2);
		plantMap.put(7, 7, 2);
		plantMap.put(7, 8, 2);
		plantMap.put(7, 9, 2);

	}

	public void update(int delta) {
		controller.Pressed();
		controller.update();

		logicMap.update();
		plantMap.update(Functions.millis());

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
		logicMap.render(g);
		itemMap.render(g);
		plantMap.render(g);

		fm.render(g);
		bm.render(g);

		player.render(g);

		viewPort.renderEnd(g);
		//

		inventoryDisplay.render(g);
		PlayerName.render(g);
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
			plantMap.harvest(player);
		}

		if (controller.ActionUse == true) {
			playerInventory.useSelf(bm, tileMap, plantMap);

		}

		if (controller.ActionSwitchItem == true) {
			playerInventory.switchCurrentItem();
		}

		if (controller.ActionDrop == true) {
			playerInventory.drop();
		}
	}
}
