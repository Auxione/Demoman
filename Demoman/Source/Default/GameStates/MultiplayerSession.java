package Default.GameStates;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Console;
import Curio.TileMap;
import Curio.Viewport;
import Curio.GameObject.ObjectController;
import Curio.GameObject.ObjectRenderer;
import Curio.GameObject.Controllers.UserInput;
import Curio.ItemSystem.Inventory;
import Curio.ItemSystem.ItemList;
import Curio.ItemSystem.ItemMap;
import Curio.Network.ChatMessagePackage;
import Curio.Network.Credentials;
import Curio.Network.GameRulesPackage;
import Curio.Network.MapPackage;
import Curio.Network.PlayerDataPackage;
import Curio.Network.PlayerList;
import Curio.Network.Client.ClientListener;
import Curio.Network.Client.ClientStarter;
import Curio.Physics.DynamicObject;
import Curio.PlantSystem.PlantList;
import Curio.Renderer.Inputbox;
import Curio.Renderer.ItemMapRenderer;
import Curio.Renderer.TextDisplay;
import Curio.Renderer.TileMapRenderer;
import Default.Main;
import Default.Player;

public class MultiplayerSession {
	private ItemList itemlist = new ItemList();
	private PlantList plantList = new PlantList();
	
	private Console console;

	public static String playerListString = "";
	private ClientStarter client;
	private ClientListener clientListener;
	private Credentials selfCredentials;
	private UserInput userInput = new UserInput(Main.input);

	public static int mapSizeX;
	public static int mapSizeY;
	public static int InventoryCellSize;
	public static int InventoryCellCount;
	public static int itemMapCellCount;

	private static TileMap tileMap;
	private static ItemMap itemMap;
	private static TileMapRenderer tileMapDisplay;
	private static ItemMapRenderer itemMapDisplay;

	public static HashMap<Credentials, Player> playerMap = new HashMap<Credentials, Player>();
	public static HashMap<Credentials, int[][]> inventoryMap = new HashMap<Credentials, int[][]>();
	public static HashMap<Credentials, ObjectRenderer> objectDisplayMap = new HashMap<Credentials, ObjectRenderer>();

	public static ArrayList<Credentials> CredentialsList = new ArrayList<Credentials>();
	public static PlayerList playerList = new PlayerList(CredentialsList);
	private HashMap<Credentials, ObjectRenderer> playerDisplayList = new HashMap<Credentials, ObjectRenderer>();
	private HashMap<Credentials, Inventory> playerInventoryList = new HashMap<Credentials, Inventory>();
	private HashMap<Credentials, DynamicObject> dynamicObjectList = new HashMap<Credentials, DynamicObject>();
	private HashMap<Credentials, ObjectController> playerControllerList = new HashMap<Credentials, ObjectController>();

	private static Viewport viewPort;
	private Inputbox messageBoxDisplay;
	private TextDisplay PlayerListDisplay;
	private String ChatMessageString = " ";

	public MultiplayerSession(String targetIP, int targetPort, Credentials selfCredentials, Console console) {
		this.console = console;

		this.selfCredentials = selfCredentials;
		clientListener = new ClientListener(selfCredentials, console);
		client = new ClientStarter(targetIP, targetPort, clientListener, console);

		viewPort = new Viewport(Main.DisplayWidth, Main.DisplayHeight);
		PlayerListDisplay = new TextDisplay(20, 20);

		messageBoxDisplay = new Inputbox(20, 80, 200, 50, "Message:", 0);
		messageBoxDisplay.setClearWhenCompleted(true);
		messageBoxDisplay.setCompleteWhenFocusLoss(false);
	}

	public static void setRules(GameRulesPackage gameRules) {
		mapSizeX = gameRules.mapSizeX;
		mapSizeY = gameRules.mapSizeY;
		InventoryCellSize = gameRules.InventoryCellSize;
		InventoryCellCount = gameRules.InventoryCellCount;
		itemMapCellCount = gameRules.itemMapCellCount;
	}

	public static void CreateNewMap(MapPackage mapPackage) {
		tileMap = new TileMap(mapSizeX, mapSizeY);
		itemMap = new ItemMap(tileMap, itemMapCellCount);
		tileMapDisplay = new TileMapRenderer(tileMap, viewPort);
		itemMapDisplay = new ItemMapRenderer(itemMap, viewPort);

		tileMap.setCellularMap(mapPackage.tileArray);
		itemMap.setCellularMap(mapPackage.itemArray);
	}

	public static void setPlayerList(PlayerList pList) {
		playerList = pList;
		playerListString = "";
		for (Credentials c : pList.CredentialsList) {
			Player player = new Player();
			playerMap.put(c, player);
			objectDisplayMap.put(c, new ObjectRenderer(player));

			playerListString += c.username + "\n";
		}
	}

	public static void setPlayerData(PlayerDataPackage playerDataPackage) {
		playerMap = playerDataPackage.playerMap;
	}

	public void update(Input input) {
		userInput.updateStart();

		messageBoxDisplay.inputEvent(input);
		PlayerListDisplay.updateString(playerListString);

		if (messageBoxDisplay.Completed == true) {
			ChatMessageString = messageBoxDisplay.getInput();
			ChatMessagePackage CMPackage = new ChatMessagePackage(selfCredentials, ChatMessageString);
			client.getConnection().sendTcp(CMPackage);
		}
		messageBoxDisplay.loopEnd();
	}

	public void render(Graphics g) {
		viewPort.renderOnWorld(tileMapDisplay, g);
		viewPort.renderOnWorld(itemMapDisplay, g);
		for (ObjectRenderer obj : objectDisplayMap.values()) {
			viewPort.renderOnWorld(obj, g);
		}
		viewPort.renderOnHUD(PlayerListDisplay, g);
		viewPort.renderOnHUD(messageBoxDisplay, g);
	}

	public void KeyPressed(int key, char chr) {
		messageBoxDisplay.keyPressed(key, chr);
		userInput.keyPressed(key, chr);
		client.getConnection().sendTcp(userInput.getPackage());
	}

	public void KeyReleased(int key, char chr) {
		messageBoxDisplay.keyReleased(key, chr);
		userInput.keyReleased(key, chr);
		client.getConnection().sendTcp(userInput.getPackage());
	}

}
