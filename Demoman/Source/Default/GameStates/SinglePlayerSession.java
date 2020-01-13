package Default.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.FluidMap;
import Curio.TileList;
import Curio.TileMap;
import Curio.Viewport;
import Curio.BombManager.BombManager;
import Curio.FireManager.FireManager;
import Curio.GameObject.GameObjectManager;
import Curio.GameObject.Controllers.UserInput;
import Curio.ItemSystem.ItemList;
import Curio.ItemSystem.ItemMap;
import Curio.Lighting.RoundLightSource;
import Curio.Lighting.RoundLightSourceRenderer;
import Curio.LogicSystem.LogicMap;
import Curio.Physics.DynamicObject;
import Curio.PlantSystem.PlantList;
import Curio.PlantSystem.PlantMap;
import Curio.Renderer.BarDisplay;
import Curio.Renderer.InventoryDisplay;
import Curio.Renderer.ItemMapRenderer;
import Curio.Renderer.MouseStatsDisplay;
import Curio.Renderer.PlantMapRenderer;
import Curio.Renderer.TextDisplay;
import Curio.Renderer.TileMapRenderer;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Main;
import Default.Player;

public class SinglePlayerSession {
	@SuppressWarnings("unused")
	private ItemList itemlist = new ItemList();
	@SuppressWarnings("unused")
	private PlantList plantList = new PlantList();
	@SuppressWarnings("unused")
	private TileList tileList = new TileList();

	private UserInput userInput;
	private Viewport viewPort;

	private InventoryDisplay inventoryDisplay;
	private BarDisplay hpBar, foodBar;
	private TextDisplay PlayerName;

	private FireManager fm;
	private BombManager bm;
	private GameObjectManager gameObjectManager;

	private TileMap tileMap;
	private TileMapRenderer tileMapDisplay;
	private LogicMap logicMap;
	private ItemMap itemMap;
	private ItemMapRenderer itemMapDisplay;
	private PlantMap plantMap;
	private PlantMapRenderer plantMapDisplay;
	private FluidMap oxygenMap;

	private MouseStatsDisplay mouseStatsDisplay;
	private Player selfPlayer;

	public SinglePlayerSession(int mapSizeX, int mapSizeY, Console console) {
		console.Add(0, "Singleplayer launched.");

		userInput = new UserInput(Main.input);

		tileMap = new TileMap(mapSizeX, mapSizeY);
		tileMap.create_BlankLevel();
		itemMap = new ItemMap(tileMap, 15);
		logicMap = new LogicMap(tileMap, itemMap);
		plantMap = new PlantMap(tileMap, itemMap);
		oxygenMap = new FluidMap(tileMap);

		fm = new FireManager(tileMap);
		bm = new BombManager(fm, tileMap, itemMap, plantMap, oxygenMap);

		debugObjects();

		viewPort = new Viewport(Main.DisplayWidth, Main.DisplayHeight);
		plantMapDisplay = new PlantMapRenderer(plantMap, viewPort);
		itemMapDisplay = new ItemMapRenderer(itemMap, viewPort);
		tileMapDisplay = new TileMapRenderer(tileMap, viewPort);

		hpBar = new BarDisplay(500, 500 + 32, 128, 10, Color.red);
		foodBar = new BarDisplay(500, 500 + 32 + 10, 128, 10, Color.green);
		PlayerName = new TextDisplay(20, 20, "Cem");
		mouseStatsDisplay = new MouseStatsDisplay(viewPort, tileMap, itemMap, plantMap, oxygenMap);

		gameObjectManager = new GameObjectManager(viewPort, bm, fm, tileMap, itemMap, plantMap, mouseStatsDisplay,
				console);

		selfPlayer = gameObjectManager.CreateNewPlayer(userInput.getPackage());
		selfPlayer.spawn(6, 6);
		gameObjectManager.CreateNewPlayer(null).spawn(6, 6);
		gameObjectManager.CreateNewPlayer(null).spawn(6, 6);

		inventoryDisplay = new InventoryDisplay(new Transform(500, 500, 0),
				GameObjectManager.playerInventoryList.get(selfPlayer), itemMap);
	}

	public void update(int delta) {
		userInput.updateStart();

		gameObjectManager.playerUpdateStart();

		mouseStatsDisplay.getCellData();
		logicMap.update();
		plantMap.update();
		oxygenMap.update();

		viewPort.move(selfPlayer.transform.position);

		hpBar.Percentage(selfPlayer.getCurrentHealth(), selfPlayer.getMaxHealth());
		foodBar.Percentage(selfPlayer.getCurrentFood(), selfPlayer.getMaxFood());

		for (DynamicObject d : DynamicObject.dynamicObjectList) {
			d.updatePhysics(delta);
		}

		inventoryDisplay.inputEvent(Main.input);
		mouseStatsDisplay.inputEvent(Main.input);

		gameObjectManager.playerupdateEnd();
	}

	public void render(Graphics g) {
		viewPort.renderOnWorld(tileMapDisplay, g);
		viewPort.renderOnWorld(logicMap, g);
		viewPort.renderOnWorld(itemMapDisplay, g);
		viewPort.renderOnWorld(plantMapDisplay, g);
		viewPort.renderOnWorld(oxygenMap, g);
		viewPort.renderOnWorld(fm, g);
		viewPort.renderOnWorld(bm, g);

		gameObjectManager.render(g);

		viewPort.renderOnHUD(mouseStatsDisplay, g);
		viewPort.renderOnHUD(inventoryDisplay, g);
		viewPort.renderOnHUD(PlayerName, g);
		viewPort.renderOnHUD(hpBar, g);
		viewPort.renderOnHUD(foodBar, g);
	}

	public void KeyPressed(int key, char chr) {
		userInput.keyPressed(key, chr);
	}

	public void KeyReleased(int key, char chr) {
		userInput.keyReleased(key, chr);
	}

	private void debugObjects() {
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

		itemMap.put(4, 6, 10);

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

		tileMap.setTile(6, 6, 0, 6);
		tileMap.setTile(6, 7, 0, 6);
		tileMap.setTile(6, 8, 0, 6);
		tileMap.setTile(6, 9, 0, 6);
		tileMap.setTile(7, 6, 0, 6);
		tileMap.setTile(7, 7, 0, 6);
		tileMap.setTile(7, 8, 0, 6);
		tileMap.setTile(7, 9, 0, 6);
		/*
		 * tileMap.set_Tile(11, 11, 2); tileMap.set_Tile(11, 12, 2);
		 * tileMap.set_Tile(11, 13, 2); tileMap.set_Tile(11, 14, 2);
		 * tileMap.set_Tile(11, 15, 2); tileMap.set_Tile(12, 11, 2);
		 * tileMap.set_Tile(12, 15, 2); tileMap.set_Tile(13, 11, 2);
		 * tileMap.set_Tile(13, 15, 2); tileMap.set_Tile(14, 11, 2);
		 * tileMap.set_Tile(14, 15, 2); tileMap.set_Tile(15, 11, 2);
		 * tileMap.set_Tile(15, 12, 2); tileMap.set_Tile(15, 13, 2);
		 * tileMap.set_Tile(15, 14, 2); tileMap.set_Tile(15, 15, 2); oxygenMap.put(12,
		 * 12, 32);
		 */
		plantMap.put(6, 6, 2);
		plantMap.put(6, 7, 2);
		plantMap.put(6, 8, 2);
		plantMap.put(6, 9, 2);
		plantMap.put(7, 6, 2);
		plantMap.put(7, 7, 2);
		plantMap.put(7, 8, 2);
		plantMap.put(7, 9, 2);

		tileMap.setTile(15, 15, 0, Constants.obj_Building);
	}
}
