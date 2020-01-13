package Curio.Network.Server;

import java.util.HashMap;

import Curio.Console;
import Curio.Tileset;
import Curio.ItemMap.Inventory;
import Curio.ItemMap.ItemMap;
import Curio.Network.Credentials;
import Curio.Network.GameRulesPackage;
import Curio.Network.MapPackage;
import Curio.Physics.TilemapCollision;
import Curio.Tilemap.TileMap;
import Default.Constants;
import Default.Player;

public class Main {
	private static Console console;
	private static ServerStarter server;
	private static ServerListener serverListener;

	public static HashMap<Credentials, Player> playerList = new HashMap<Credentials, Player>();
	public static HashMap<Credentials, Inventory> playerInventoryList = new HashMap<Credentials, Inventory>();
	public static HashMap<Credentials, TilemapCollision> collisionList = new HashMap<Credentials, TilemapCollision>();

	public static TileMap tileMap;
	public static int tileMapX = 30, tileMapY = 30;
	public static ItemMap itemMap;
	public static GameRulesPackage gameRules;
	public static MapPackage mapPackage;

	public static void main(String[] args) {
		console = new Console();
		Tileset.InitTileset();
		console.Add(0, "Started");

		tileMap = new TileMap(tileMapX, tileMapY, Constants.CellSize, console);
		tileMap.create_BlankLevel();
		itemMap = new ItemMap(tileMap, 5, console);
		

		mapPackage = new MapPackage(tileMap,itemMap);
		gameRules = new GameRulesPackage(tileMapX, tileMapY);
		console.Add(0, "Setup packages created");
		
		serverListener = new ServerListener(console);
		server = new ServerStarter(200, serverListener);
	}

	static void addPlayer(Credentials credentials) {
		Player player = new Player(tileMap);
		playerList.put(credentials, player);
		collisionList.put(credentials, new TilemapCollision(tileMap, player));
		playerInventoryList.put(credentials, new Inventory(itemMap, player, 3, 4));
	}

	static void removePlayer(Credentials credentials) {
		playerList.remove(credentials);
		playerInventoryList.remove(credentials);
		collisionList.remove(credentials);
	}
}
