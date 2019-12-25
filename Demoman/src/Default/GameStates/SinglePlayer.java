package Default.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Viewport;
import Curio.HUD.BarDisplay;
import Curio.HUD.ConsoleDisplay;
import Curio.HUD.InventoryDisplay;
import Curio.HUD.MouseStatsDisplay;
import Curio.HUD.ObjectiveDisplay;
import Curio.HUD.TextDisplay;
import Curio.ItemMap.Inventory;
import Curio.ItemMap.ItemMap;
import Curio.LogicMap.LogicMap;
import Curio.ObjectiveSystem.ObjectiveSystem;
import Curio.ObjectiveSystem.Objectives.MoveToTile;
import Curio.Physics.DynamicObject;
import Curio.Physics.TilemapCollision;
import Curio.PlantMap.PlantMap;
import Curio.Tilemap.FireManager;
import Curio.Tilemap.FluidMap;
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
	private FluidMap oxygenMap;

	private ObjectiveSystem objectiveSystem;
	private ObjectiveDisplay objDisplay;

	private MouseStatsDisplay mouseStatsDisplay;

	public SinglePlayer(ConsoleDisplay console) {
		console.Add(0, "Creating Singleplayer Game");

		tileMap = new TileMap(30, 30, Constants.CellSize, console);
		tileMap.create_BlankLevel();
		itemMap = new ItemMap(tileMap, 15);
		logicMap = new LogicMap(tileMap, itemMap);
		plantMap = new PlantMap(tileMap, itemMap);
		oxygenMap = new FluidMap(tileMap);

		player = new Player(tileMap, 250, 250);
		controller = new Controller(1, player);
		collision = new TilemapCollision(tileMap, player);
		playerInventory = new Inventory(itemMap, player, 4, 5);

		viewPort = new Viewport(Main.DisplayWidth, Main.DisplayHeight);
		hpBar = new BarDisplay(500, 500 + 32, 128, 10, Color.red);
		foodBar = new BarDisplay(500, 500 + 32 + 10, 128, 10, Color.green);
		inventoryDisplay = new InventoryDisplay(500, 500, playerInventory, itemMap);
		PlayerName = new TextDisplay(20, 20, "Cem");
		mouseStatsDisplay = new MouseStatsDisplay(viewPort, player, tileMap, itemMap, plantMap, oxygenMap);

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

		tileMap.set_Tile(11, 11, 2);
		tileMap.set_Tile(11, 12, 2);
		tileMap.set_Tile(11, 13, 2);
		tileMap.set_Tile(11, 14, 2);
		tileMap.set_Tile(11, 15, 2);
		tileMap.set_Tile(12, 11, 2);
		tileMap.set_Tile(12, 15, 2);
		tileMap.set_Tile(13, 11, 2);
		tileMap.set_Tile(13, 15, 2);
		tileMap.set_Tile(14, 11, 2);
		tileMap.set_Tile(14, 15, 2);
		tileMap.set_Tile(15, 11, 2);
		tileMap.set_Tile(15, 12, 2);
		tileMap.set_Tile(15, 13, 2);
		tileMap.set_Tile(15, 14, 2);
		tileMap.set_Tile(15, 15, 2);
		oxygenMap.put(12, 12, 32);

		plantMap.put(6, 6, 2);
		plantMap.put(6, 7, 2);
		plantMap.put(6, 8, 2);
		plantMap.put(6, 9, 2);
		plantMap.put(7, 6, 2);
		plantMap.put(7, 7, 2);
		plantMap.put(7, 8, 2);
		plantMap.put(7, 9, 2);

		objectiveSystem = new ObjectiveSystem(player, tileMap, itemMap, plantMap);

		objectiveSystem.add(new MoveToTile(5, 5));

		objDisplay = new ObjectiveDisplay(20, 50, new MoveToTile(5, 5));
	}

	public void update(int delta) {
		controller.Pressed();
		controller.update();
		objectiveSystem.update(player, tileMap, itemMap, plantMap);
		mouseStatsDisplay.getCellData();
		logicMap.update();
		plantMap.update();
		oxygenMap.update();

		fm.update(player);
		bm.update(player);

		player.loop();
		viewPort.move(player.Position);

		hpBar.Percentage(player.getCurrentHealth(), player.getMaxHealth());
		foodBar.Percentage(player.getCurrentFood(), player.getMaxFood());

		collision.checkCollisions();

		for (DynamicObject d : DynamicObject.dynamicObjectList) {
			d.updatePhysics(delta);
		}

		inventoryDisplay.inputEvent(Main.input);
		mouseStatsDisplay.inputEvent(Main.input);
		actions();
		objectiveSystem.updateEnd();
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
		oxygenMap.render(g);

		fm.render(g);
		bm.render(g);

		player.render(g);

		viewPort.renderEnd(g);
		//
		mouseStatsDisplay.render(g);
		objDisplay.render(g);
		inventoryDisplay.render(g);
		PlayerName.render(g);
		hpBar.render(g);
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
