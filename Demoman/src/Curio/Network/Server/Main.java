package Curio.Network.Server;

import java.util.HashMap;

import Curio.Console;
import Curio.ItemMap.Inventory;
import Curio.ItemMap.ItemMap;
import Curio.Network.Credentials;
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

	private static TileMap tileMap;
	private static ItemMap itemMap;

	public static void main(String[] args) {
		console = new Console();
		console.Add(0, "Started");

		serverListener = new ServerListener(console);
		server = new ServerStarter(200, serverListener);

		tileMap = new TileMap(30, 30, Constants.CellSize, console);
		itemMap = new ItemMap(tileMap, 5, console);
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

		String uname = credentials.username;
		console.Add(0, uname + " disconnected.");
	}
}
