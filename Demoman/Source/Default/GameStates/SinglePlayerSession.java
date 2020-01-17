package Default.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.FluidMap;
import Curio.Functions;
import Curio.TileList;
import Curio.TileMap;
import Curio.Viewport;
import Curio.GameObject.Controllers.UserInput;
import Curio.ItemSystem.ItemList;
import Curio.ItemSystem.ItemMap;
import Curio.LogicSystem.LogicMap;
import Curio.Physics.DynamicObject;
import Curio.Physics.WorldTime;
import Curio.PlantSystem.PlantList;
import Curio.PlantSystem.PlantMap;
import Curio.Renderer.BarDisplay;
import Curio.Renderer.InventoryDisplay;
import Curio.Renderer.ItemMapRenderer;
import Curio.Renderer.MouseStatsDisplay;
import Curio.Renderer.PlantMapRenderer;
import Curio.Renderer.TextDisplay;
import Curio.Renderer.TileMapRenderer;
import Curio.Renderer.WorldTimeDisplay;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.PlayerManager;
import Curio.SessionManagers.WorldManager;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.GameObjectManager.WorldObjectManager;
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
	int systemMillis;

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

	private FireManager fireManager;
	private BombManager bombManager;
	private WorldObjectManager worldObjectManager;
	private WorldManager worldManager;
	private PlayerManager playerManager;
	private PlantManager plantManager;

	private FluidMap oxygenMap;
	private LogicMap logicMap;

	private InventoryDisplay inventoryDisplay;
	private BarDisplay hpBar, foodBar;
	private TextDisplay PlayerName;
	private MouseStatsDisplay mouseStatsDisplay;
	private Player selfPlayer;

	private BreadthFS fs;

	public SinglePlayerSession(int mapSizeX, int mapSizeY, Console console) {
		console.Add(0, "Singleplayer launched.");

		userInput = new UserInput(Main.input);
		worldTime = new WorldTime(10);

		worldTime.setWorldTime(0, 11, 50);

		tileMap = new TileMap(mapSizeX, mapSizeY);
		tileMap.create_BlankLevel();
		itemMap = new ItemMap(tileMap, 15);
		plantMap = new PlantMap(tileMap);
		oxygenMap = new FluidMap(tileMap);

		logicMap = new LogicMap(tileMap, itemMap);

		worldManager = new WorldManager(plantMap, tileMap, itemMap, worldTime);
		plantManager = new PlantManager(worldManager, plantMap, itemMap);
		worldObjectManager = new WorldObjectManager(worldManager, plantManager);

		fireManager = new FireManager(worldManager, plantManager);
		bombManager = new BombManager(worldManager, fireManager, plantManager, itemMap);

		playerManager = new PlayerManager(worldManager, bombManager, fireManager, plantManager, worldObjectManager,
				itemMap);

		debugObjects();

		selfPlayer = playerManager.Create(userInput.getPackage());
		selfPlayer.spawn(6, 6);
		playerManager.Create(null).spawn(6, 6);
		playerManager.Create(null).spawn(6, 6);

		inventoryDisplay = new InventoryDisplay(new Transform(0, 600, 0),
				PlayerManager.playerInventoryList.get(selfPlayer), itemMap);

		viewPort = new Viewport(Main.DisplayWidth, Main.DisplayHeight);
		plantMapDisplay = new PlantMapRenderer(plantMap, viewPort);
		itemMapDisplay = new ItemMapRenderer(itemMap, viewPort);
		tileMapDisplay = new TileMapRenderer(tileMap, viewPort);

		hpBar = new BarDisplay(500, 500 + 32, 128, 10, Color.red);
		foodBar = new BarDisplay(500, 500 + 32 + 10, 128, 10, Color.green);
		PlayerName = new TextDisplay(20, 20, "Cem");
		mouseStatsDisplay = new MouseStatsDisplay(viewPort, worldManager, plantManager, playerManager, itemMap);

		worldTimeDisplay = new WorldTimeDisplay(400, 400, 128, 128, worldTime);
		
		fs = new BreadthFS(tileMap, new CellCoordinate(3,3), 1, 6);
	}

	public void update(int delta) {
		systemMillis = Functions.millis();
		worldTime.updateStart(systemMillis);
		worldManager.updateCycle();
		worldObjectManager.update();

		userInput.updateStart();
		playerManager.updateStart();
		plantManager.update();

		mouseStatsDisplay.getCellData();

		logicMap.update();
		oxygenMap.update();

		viewPort.move(selfPlayer.transform.position);

		hpBar.Percentage(selfPlayer.getCurrentHealth(), selfPlayer.getMaxHealth());
		foodBar.Percentage(selfPlayer.getCurrentFood(), selfPlayer.getMaxFood());

		for (DynamicObject d : DynamicObject.dynamicObjectList) {
			d.updatePhysics(delta);
		}

		inventoryDisplay.inputEvent(Main.input);
		mouseStatsDisplay.inputEvent(Main.input);

		playerManager.updateEnd();
		worldTime.updateEnd();
	}

	public void render(Graphics g) {
		viewPort.renderOnWorld(tileMapDisplay, g);
		viewPort.renderOnWorld(logicMap, g);
		viewPort.renderOnWorld(itemMapDisplay, g);
		viewPort.renderOnWorld(plantMapDisplay, g);
		viewPort.renderOnWorld(oxygenMap, g);
		
		viewPort.renderOnWorld(worldObjectManager, g);
		
		playerManager.render(viewPort, g);
		worldManager.render(viewPort, g);
		
		viewPort.renderOnHUD(worldTimeDisplay, g);
		viewPort.renderOnHUD(mouseStatsDisplay, g);
		viewPort.renderOnHUD(inventoryDisplay, g);
		viewPort.renderOnHUD(PlayerName, g);
		viewPort.renderOnHUD(hpBar, g);
		viewPort.renderOnHUD(foodBar, g);
		
		viewPort.renderOnWorld(fs, g);
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
				tileMap.setTile(4 + i, 4, 0, 2);
				itemMap.put(4 + i, 4, ItemList.list.get(i));
			}
		}

		for (int i = 1; i < plantListSize + 1; i++) {
			tileMap.setTile(4 + i, 5, 0, 6);
			plantMap.put(4 + i, 5, i);
		}

		tileMap.setTile(15, 15, 0, Constants.obj_Building);
	}
}
