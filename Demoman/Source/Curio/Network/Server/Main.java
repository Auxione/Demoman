package Curio.Network.Server;

import java.util.ArrayList;
import java.util.HashMap;

import Curio.Console;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.ObjectController;
import Curio.Network.Credentials;
import Curio.Network.GameRulesPackage;
import Curio.Network.MapPackage;
import Curio.Network.PlayerDataPackage;
import Curio.Network.PlayerList;
import Curio.Physics.DynamicObject;
import Curio.Physics.TilemapCollision;
import Curio.SessionManagers.ItemManager.Inventory;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.WorldManager.TileMap;
import Default.Player;

public class Main {
	private static Console console;
	private static ServerStarter server;
	private static ServerListener serverListener;

	private static int InventoryCellSize = 3;
	private static int InventoryCellCount = 3;
	private static int tileMapX = 30, tileMapY = 30;
	private static int itemMapCellCount = 3;

	public static ArrayList<Credentials> CredentialsList = new ArrayList<Credentials>();
	public static HashMap<Credentials, Player> playerMap = new HashMap<Credentials, Player>();
	public static HashMap<Credentials, TilemapCollision> collisionMap = new HashMap<Credentials, TilemapCollision>();
	public static HashMap<Credentials, DynamicObject> dynamicObjectMap = new HashMap<Credentials, DynamicObject>();
	public static HashMap<Credentials, Inventory> inventoryMap = new HashMap<Credentials, Inventory>();
	public static HashMap<Credentials, ObjectController> playerControllerMap = new HashMap<Credentials, ObjectController>();
	public static HashMap<Credentials, ControlPackage> controllerPackageMap = new HashMap<Credentials, ControlPackage>();

	public static PlayerList playerList = new PlayerList(CredentialsList);

	public static TileMap tileMap;
	public static ItemMap itemMap;

	public static GameRulesPackage gameRules;
	public static MapPackage mapPackage;

	public static void main(String[] args) {
		console = new Console();
		Tileset.InitTileset();
		console.Add(0, "Started");

		gameRules = new GameRulesPackage()// create game rules.
				.setInventoryRules(InventoryCellSize, InventoryCellCount)// set inventory sizes
				.setTileMapRules(tileMapX, tileMapY)// set map rules
				.setItemMapRules(itemMapCellCount);

		tileMap = new TileMap(tileMapX, tileMapY);
		tileMap.create_BlankLevel();
		itemMap = new ItemMap(tileMap, itemMapCellCount);

		mapPackage = new MapPackage().addItemMap(itemMap.getCellularMap()).addTileMap(tileMap.getCellularMap());

		console.Add(0, "Setup packages created");

		serverListener = new ServerListener(console);
		server = new ServerStarter(200, serverListener);

		while (server.isConnected() == true) {
			serverLoop();
		}
	}

	static void addPlayer(Credentials credentials) {
		Player player = new Player();
		DynamicObject dynamicObject = new DynamicObject(tileMap, player);
		Inventory inventory = new Inventory(itemMap, player, InventoryCellCount, InventoryCellSize);
		TilemapCollision tmapCollision = new TilemapCollision(tileMap, dynamicObject);
		ObjectController playerController = new ObjectController(dynamicObject);
		ControlPackage controllerPackage = new ControlPackage();

		player.spawn(7, 7);
		console.Add(0, "A new Player spawned.");

		playerMap.put(credentials, player);
		inventoryMap.put(credentials, inventory);
		dynamicObjectMap.put(credentials, dynamicObject);
		collisionMap.put(credentials, tmapCollision);
		playerControllerMap.put(credentials, playerController);
		controllerPackageMap.put(credentials, controllerPackage);
	}

	static void removePlayer(Credentials credentials) {
		playerMap.remove(credentials);
		inventoryMap.remove(credentials);
		dynamicObjectMap.remove(credentials);
		collisionMap.remove(credentials);
	}

	public static void passControls(Credentials credentials, ControlPackage controlPackage) {
		playerControllerMap.get(credentials).setControlPackage(controlPackage);
	}

	static void serverLoop() {
		PlayerDataPackage playerDataPackage = new PlayerDataPackage(CredentialsList);
		if (CredentialsList.isEmpty() == false) {
			for (Credentials c : CredentialsList) {
				ObjectController playerController = playerControllerMap.get(c);
				ControlPackage controlPackage = controllerPackageMap.get(c);

				playerController.updateStart();
			}

			for (Credentials c : CredentialsList) {
				Player player = playerMap.get(c);
				DynamicObject dynamicObject = dynamicObjectMap.get(c);
				Inventory inventory = inventoryMap.get(c);
				TilemapCollision mapCollision = collisionMap.get(c);

				player.update();
				mapCollision.checkCollisions();
				dynamicObject.updatePhysics(7.0f);

				int[][] iMap = inventoryMap.get(c).getMap();
				playerDataPackage.addPlayerInventoryMap(c, iMap);
				playerDataPackage.addPlayerMap(c, player);
			}

			for (Credentials c : CredentialsList) {
				ObjectController playerController = playerControllerMap.get(c);
				playerController.updateEnd();
				serverListener.sendUDPto(c, playerDataPackage);
			}
		}
	}
}
