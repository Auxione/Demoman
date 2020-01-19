package Default.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.FluidMap;
import Curio.Functions;
import Curio.Viewport;
import Curio.Controllers.UserInput;
import Curio.Physics.WorldTime;
import Curio.Renderer.BarDisplay;
import Curio.Renderer.InventoryDisplay;
import Curio.Renderer.ItemMapRenderer;
import Curio.Renderer.MouseStatsDisplay;
import Curio.Renderer.PlantMapRenderer;
import Curio.Renderer.TextDisplay;
import Curio.Renderer.TileMapRenderer;
import Curio.Renderer.WorldTimeDisplay;
import Curio.SessionManagers.DynamicObjectManager;
import Curio.SessionManagers.PlayerManager;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.ItemList;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.LogicManager.LogicManager;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.PlantManager.PlantList;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.PlantManager.PlantMap;
import Curio.SessionManagers.WorldManager.TileList;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Curio.Utilities.AStarPathFinder;
import Curio.Utilities.BreadthFS;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;
import Default.Main;
import Default.Player;

public class SinglePlayerSession {
	@SuppressWarnings("unused")
	private ItemList itemList = new ItemList();
	@SuppressWarnings("unused")
	private PlantList plantList = new PlantList();
	@SuppressWarnings("unused")
	private TileList tileList = new TileList();
	float systemMillis;

	private UserInput userInput;
	private Viewport viewPort;

	private WorldTime worldTime;
	private WorldTimeDisplay worldTimeDisplay;

	private TileMap tileMap;
	private TileMapRenderer tileMapDisplay;

	private ItemMap itemMap;
	private ItemMapRenderer itemMapDisplay;

	private PlantMap plantMap;
	private PlantMapRenderer plantMapDisplay;

	private DynamicObjectManager dynamicObjectManager;
	private FireManager fireManager;
	private BombManager bombManager;
	private WorldObjectManager worldObjectManager;
	private WorldManager worldManager;
	private PlayerManager playerManager;
	private PlantManager plantManager;
	private LogicManager logicManager;
	private ItemManager itemManager;

	private FluidMap oxygenMap;
	private LogicMap logicMap;

	private InventoryDisplay inventoryDisplay;
	private BarDisplay hpBar, foodBar;
	private TextDisplay PlayerName;
	private MouseStatsDisplay mouseStatsDisplay;
	private Player selfPlayer;

	private AStarPathFinder aspf;

	public SinglePlayerSession(int mapSizeX, int mapSizeY, Console console) {
		console.Add(0, "Singleplayer launched.");
		userInput = new UserInput(Main.input);
		worldTime = new WorldTime(1000);
		viewPort = new Viewport(Main.DisplayWidth, Main.DisplayHeight);

		tileMap = new TileMap(mapSizeX, mapSizeY).createBlankLevel();
		itemMap = new ItemMap(tileMap);
		plantMap = new PlantMap(tileMap);
		oxygenMap = new FluidMap(tileMap);
		logicMap = new LogicMap(tileMap);

		dynamicObjectManager = new DynamicObjectManager();
		worldManager = new WorldManager(viewPort, plantMap, tileMap, itemMap, worldTime);
		worldObjectManager = new WorldObjectManager(worldManager, plantManager);
		itemManager = new ItemManager(itemMap, 15);
		plantManager = new PlantManager(worldManager, itemManager, plantMap);
		fireManager = new FireManager(worldManager, plantManager);
		bombManager = new BombManager(worldManager, fireManager, plantManager, itemMap);
		logicManager = new LogicManager(worldManager, itemManager, fireManager, logicMap).setConsole(console);
		playerManager = new PlayerManager(worldManager, bombManager, fireManager, plantManager, worldObjectManager,
				itemManager, logicManager);

		debugObjects();

		selfPlayer = playerManager.Create(userInput.getPackage());
		selfPlayer.spawn(6, 6);
		playerManager.Create(null).spawn(6, 6);
		playerManager.Create(null).spawn(6, 6);

		inventoryDisplay = new InventoryDisplay(new Transform(0, 600, 0),
				PlayerManager.playerInventoryList.get(selfPlayer), itemMap);

		plantMapDisplay = new PlantMapRenderer(plantMap);
		itemMapDisplay = new ItemMapRenderer(itemMap);
		tileMapDisplay = new TileMapRenderer(tileMap);

		hpBar = new BarDisplay(500, 500 + 32, 128, 10, Color.red);
		foodBar = new BarDisplay(500, 500 + 32 + 10, 128, 10, Color.green);
		PlayerName = new TextDisplay(20, 20, "Cem");
		mouseStatsDisplay = new MouseStatsDisplay(viewPort, worldManager, plantManager, itemMap);

		worldTimeDisplay = new WorldTimeDisplay(200, 400, 256, 256, worldTime);

		worldTime.setWorldTime(0, 20, 59, 55);

		// aspf = new AStarPathFinder(tileMap, new CellCoordinate(3, 3), new
		// CellCoordinate(9, 9));
		logicManager.placeObject.DynamicWall(5, 8, 5);
		logicManager.placeObject.PushToSwitch(4, 8, 5, 8);
		logicManager.placeObject.Pushbutton(3, 8, 4, 8);
	}

	public void update(int delta) {
		systemMillis = Functions.millis();

		worldTime.updateStart(systemMillis);
		worldManager.updateCycle();
		worldObjectManager.update();

		userInput.updateStart();
		playerManager.updateStart();
		plantManager.update();
		dynamicObjectManager.update(delta);
		logicManager.update();

		mouseStatsDisplay.getCellData();

		oxygenMap.update();

		viewPort.move(selfPlayer.transform.position);

		hpBar.Percentage(selfPlayer.getCurrentHealth(), selfPlayer.getMaxHealth());
		foodBar.Percentage(selfPlayer.getCurrentFood(), selfPlayer.getMaxFood());
		inventoryDisplay.inputEvent(Main.input);
		mouseStatsDisplay.inputEvent(Main.input);

		playerManager.updateEnd();
		worldTime.updateEnd();
	}

	public void render(Graphics g) {
		viewPort.renderOnWorld(tileMapDisplay, g);
		viewPort.renderOnWorld(logicMap, g);
		viewPort.renderOnWorld(plantMapDisplay, g);
		viewPort.renderOnWorld(oxygenMap, g);
		viewPort.renderOnWorld(bombManager, g);
		viewPort.renderOnWorld(logicManager, g);
		viewPort.renderOnWorld(itemMapDisplay, g);
		
		viewPort.renderOnHUD(worldManager, g);
		viewPort.renderOnWorld(worldObjectManager, g);
		viewPort.renderOnWorld(playerManager, g);

		viewPort.renderOnHUD(worldTimeDisplay, g);
		viewPort.renderOnHUD(mouseStatsDisplay, g);
		viewPort.renderOnHUD(inventoryDisplay, g);
		viewPort.renderOnHUD(PlayerName, g);
		viewPort.renderOnHUD(hpBar, g);
		viewPort.renderOnHUD(foodBar, g);

		// viewPort.renderOnWorld(aspf, g);
	}

	public void KeyPressed(int key, char chr) {
		userInput.keyPressed(key, chr);
	}

	public void KeyReleased(int key, char chr) {
		userInput.keyReleased(key, chr);
	}

	private void debugObjects() {
		int itemListSize = ItemList.list.size();
		int plantListSize = PlantList.list.size();
		int itemsPerTile = 5;

		for (int c = 0; c < itemsPerTile; c++) {
			for (int i = 1; i < itemListSize + 1; i++) {
				tileMap.setCell(4 + i, 4, 0, 2);
				itemManager.put(4 + i, 4, ItemList.list.get(i));
			}
		}

		for (int i = 1; i < plantListSize + 1; i++) {
			tileMap.setCell(4 + i, 5, 0, 6);
			plantMap.put(4 + i, 5, i);
		}

		tileMap.setCell(15, 15, 0, Constants.obj_Building);
	}
}
