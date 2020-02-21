package Default.GameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.Functions;
import Curio.Viewport;
import Curio.Controllers.Input.UserKeyboardInput;
import Curio.Controllers.Input.AI.AI;
import Curio.Controllers.Input.AI.PrimitiveTaskList;
import Curio.MouseSelectionUI.MouseSelect;
import Curio.Renderer.ItemMapRenderer;
import Curio.Renderer.PathRenderer;
import Curio.Renderer.PlantMapRenderer;
import Curio.Renderer.RailRoadMapRenderer;
import Curio.Renderer.TileMapRenderer;
import Curio.Renderer.DisplayObjects.BarDisplay;
import Curio.Renderer.DisplayObjects.InventoryDisplay;
import Curio.Renderer.DisplayObjects.TextDisplay;
import Curio.Renderer.DisplayObjects.WorldTimeDisplay;
import Curio.SessionManagers.AIManager;
import Curio.SessionManagers.CollisionManager;
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
import Curio.SessionManagers.RailSystemManager.RailSystemManager;
import Curio.SessionManagers.RailSystemManager.RailroadMap;
import Curio.SessionManagers.WorldManager.TileList;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldManager.WorldTime;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Navigation.Path;
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

	private UserKeyboardInput userKeyboardInput;
	private Viewport viewport;

	private WorldTime worldTime;
	private WorldTimeDisplay worldTimeDisplay;

	private TileMap tileMap;
	private TileMapRenderer tileMapDisplay;

	private ItemMap itemMap;
	private ItemMapRenderer itemMapDisplay;

	private PlantMap plantMap;
	private PlantMapRenderer plantMapDisplay;

	private RailroadMap railroad;
	private RailRoadMapRenderer railRoadMapDisplay;

	private LogicMap logicMap;

	private AIManager aiManager;
	private DynamicObjectManager dynamicObjectManager;
	private CollisionManager collisionManager;
	private FireManager fireManager;
	private BombManager bombManager;
	private WorldObjectManager worldObjectManager;
	private WorldManager worldManager;
	private PlayerManager playerManager;
	private PlantManager plantManager;
	private LogicManager logicManager;
	private ItemManager itemManager;
	private RailSystemManager railSystemManager;

	private InventoryDisplay inventoryDisplay;
	private BarDisplay hpBar, foodBar;
	private TextDisplay PlayerName;
	private Player selfPlayer;

	private MouseSelect mouseSelect;

	public static Path testPath = new Path();
	public PathRenderer testPathRenderer = new PathRenderer(testPath).setColor(Color.blue);

	public SinglePlayerSession(int mapSizeX, int mapSizeY, Console console) {
		userKeyboardInput = new UserKeyboardInput(Main.input);
		worldTime = new WorldTime(1000);
		viewport = new Viewport(Main.DisplayWidth, Main.DisplayHeight);

		tileMap = new TileMap(mapSizeX, mapSizeY).createBlankLevel();
		itemMap = new ItemMap(tileMap);
		plantMap = new PlantMap(tileMap);
		logicMap = new LogicMap(tileMap);
		railroad = new RailroadMap(tileMap);

		aiManager = new AIManager();
		worldManager = new WorldManager(plantMap, tileMap, itemMap, worldTime);
		dynamicObjectManager = new DynamicObjectManager();
		collisionManager = new CollisionManager(worldManager);
		worldObjectManager = new WorldObjectManager(worldManager, plantManager);
		itemManager = new ItemManager(itemMap, 15);
		plantManager = new PlantManager(worldManager, itemManager, plantMap);
		fireManager = new FireManager(worldManager, plantManager);
		bombManager = new BombManager(worldManager, fireManager, plantManager, itemMap);
		logicManager = new LogicManager(worldManager, itemManager, fireManager, logicMap);
		railSystemManager = new RailSystemManager(railroad);
		playerManager = new PlayerManager(worldManager, bombManager, fireManager, plantManager, worldObjectManager,
				itemManager, logicManager, dynamicObjectManager, collisionManager, aiManager);

		selfPlayer = playerManager.Create(userKeyboardInput.getPackage());
		selfPlayer.spawn(6, 6);

		inventoryDisplay = new InventoryDisplay(PlayerManager.playerInventoryList.get(selfPlayer))
				.setPosition(new Vector(0, 600));

		plantMapDisplay = new PlantMapRenderer(plantMap);
		itemMapDisplay = new ItemMapRenderer(itemMap);
		tileMapDisplay = new TileMapRenderer(tileMap);
		railRoadMapDisplay = new RailRoadMapRenderer(railroad);

		hpBar = new BarDisplay().setBarColor(Color.red).setPosition(new Vector(500, 500 + 32)).setSize(128, 10);
		foodBar = new BarDisplay().setBarColor(Color.green).setPosition(new Vector(500, 500 + 32 + 10)).setSize(128,
				10);
		PlayerName = new TextDisplay().setPosition(new Vector(20, 20)).setText("Cem");
		worldTimeDisplay = new WorldTimeDisplay(worldTime).setPosition(new Vector(200, 400)).setSize(256, 256);

		mouseSelect = new MouseSelect(selfPlayer, viewport, playerManager, Main.input);

		console.Add(0, "Singleplayer launched.");

		debugObjects();
	}

	public void update(int delta) {
		userKeyboardInput.FirstUpdate();
		aiManager.FirstUpdate();
		mouseSelect.FirstUpdate();

		worldTime.fixedUpdate(delta);
		dynamicObjectManager.fixedUpdate(delta);
		collisionManager.frameUpdate();

		worldObjectManager.frameUpdate();
		playerManager.frameUpdate();
		plantManager.frameUpdate();
		logicManager.frameUpdate();

		viewport.move(selfPlayer.transform.position);
		hpBar.setPercentage(selfPlayer.healthRatio);
		foodBar.setPercentage(selfPlayer.foodRatio);

		inventoryDisplay.inputEvent(Main.input);

		worldTime.LastUpdate();
		mouseSelect.LastUpdate();
		userKeyboardInput.LastUpdate();
		aiManager.LastUpdate();
	}

	public void render(Graphics g) {
		viewport.renderOnWorld(tileMapDisplay, g);
		viewport.renderAlphaMaskOnWorld(tileMapDisplay, g);
		viewport.renderOnWorld(plantMapDisplay, g);
		viewport.renderOnWorld(bombManager, g);
		viewport.renderAnimationOnWorld(bombManager, g);
		viewport.renderOnWorld(logicManager, g);
		viewport.renderAnimationOnWorld(logicManager, g);
		viewport.renderOnWorld(itemMapDisplay, g);
		viewport.renderOnWorld(worldObjectManager, g);
		viewport.renderOnWorld(playerManager, g);
		viewport.renderOnWorld(mouseSelect, g);
		viewport.renderOnWorld(railSystemManager, g);
		viewport.renderOnWorld(railRoadMapDisplay, g);

		viewport.renderOnWorld(testPathRenderer, g); // for testing

		viewport.renderAlphaMaskOnWorld(worldObjectManager, g);
		viewport.renderAlphaMaskOnWorld(logicManager, g);
		viewport.renderAlphaMaskOnWorld(bombManager, g);

		//viewport.renderOnHUD(worldTimeDisplay, g);
		viewport.renderOnHUD(inventoryDisplay, g);
		viewport.renderOnHUD(PlayerName, g);
		viewport.renderOnHUD(hpBar, g);
		viewport.renderOnHUD(foodBar, g);

		// viewPort.renderOnWorld(aspf, g);
	}

	public void KeyPressed(int key, char chr) {
	}

	public void KeyReleased(int key, char chr) {
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

		Color[] cArray = { Color.blue, Color.red, Color.magenta, Color.yellow, Color.green };

		for (int i = 0; i < 10; i++) {
			logicManager.placeObject.ItemSpawner(4 + i, 13, 4 + i, 10, Functions.random(1, 7));
			logicManager.placeObject.DynamicWall(4 + i, 12, 4 + i, 10, 5);
			logicManager.placeObject.LightBulb(4 + i, 11, 4 + i, 10, (Color) Functions.random(cArray));
			int trig = Functions.random(0, 3);

			if (trig == 0) {
				logicManager.placeObject.MoveTrigger(4 + i, 10);
			}

			else if (trig == 1) {
				logicManager.placeObject.Pushbutton(4 + i, 10);
			}

			else if (trig == 2) {
				logicManager.placeObject.Switchbutton(4 + i, 10);
			}

			railroad.putVerticalRail(4 + i, 12);
		}
		tileMap.setCell(15, 26, 0, Constants.obj_Building);

		railSystemManager.createStation(new CellCoordinate(20, 5))// 1
				.setColor(Color.red)// set color
				.createNode(new CellCoordinate(22, 7)) // 2
				.createNode(new CellCoordinate(22, 9)) // 3
				.createNode(new CellCoordinate(20, 11)) // 4
				.createNode(new CellCoordinate(18, 11)) // 5
				.createNode(new CellCoordinate(16, 9)) // 6
				.createNode(new CellCoordinate(16, 7)) // 7
				.createNode(new CellCoordinate(18, 5)) // 8
				.checkStatus();

		testPath.addPoint(new Vector(500, 100))// 1
				.addPoint(new Vector(700, 300)) // 2
				.addPoint(new Vector(500, 500))// 3
				.addPoint(new Vector(600, 600))// 4
				.addPoint(new Vector(500, 700))// 5
				.addPoint(new Vector(600, 800));// 6

		playerManager.Create(null).spawn(7, 7);

		AI a = aiManager.getLastCreatedAI();
		aiManager.addTasktoAI(a, PrimitiveTaskList.blindMove(a, new CellCoordinate(8, 4).get2AxisCellMiddle()));
		aiManager.addTasktoAI(a, PrimitiveTaskList.blindTakeItemFromGround(a));
		aiManager.addTasktoAI(a, PrimitiveTaskList.followPath(a, testPath));
		aiManager.addTasktoAI(a, PrimitiveTaskList.blindDropItem(a));
		
		aiManager.addTasktoAI(a, PrimitiveTaskList.blindMove(a, new CellCoordinate(9, 4).get2AxisCellMiddle()));
		aiManager.addTasktoAI(a, PrimitiveTaskList.blindTakeItemFromGround(a));
		aiManager.addTasktoAI(a, PrimitiveTaskList.blindMove(a, new CellCoordinate(9, 20).get2AxisCellMiddle()));
		aiManager.addTasktoAI(a, PrimitiveTaskList.blindDropItem(a));

		worldTime.setWorldTime(0, 20, 59, 55);
	}
}
