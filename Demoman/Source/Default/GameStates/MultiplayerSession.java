package Default.GameStates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Console;
import Curio.Viewport;
import Curio.HUD.Inputbox;
import Curio.HUD.TextDisplay;
import Curio.ItemMap.Inventory;
import Curio.ItemMap.ItemMap;
import Curio.Network.ChatMessagePackage;
import Curio.Network.Credentials;
import Curio.Network.GameRulesPackage;
import Curio.Network.MapPackage;
import Curio.Network.PlayerListPackage;
import Curio.Network.Client.ClientListener;
import Curio.Network.Client.ClientStarter;
import Curio.Physics.TilemapCollision;
import Curio.Tilemap.TileMap;
import Default.Constants;
import Default.Main;
import Default.Player;

public class MultiplayerSession {
	private Console console;

	public static String playerListString = "";
	private ClientStarter client;
	private ClientListener clientListener;
	private Credentials selfCredentials;

	public static ArrayList<Credentials> credentialsList;
	public static HashMap<Credentials, Player> playerList = new HashMap<Credentials, Player>();
	public static HashMap<Credentials, Inventory> playerInventoryList = new HashMap<Credentials, Inventory>();
	public static HashMap<Credentials, TilemapCollision> collisionList = new HashMap<Credentials, TilemapCollision>();

	private static TileMap tileMap;
	private static ItemMap itemMap;

	private Viewport viewPort;
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
		tileMap = new TileMap(gameRules.mapSizeX, gameRules.mapSizeY, Constants.CellSize);
		itemMap = new ItemMap(tileMap, 5);
	}

	public static void setMap(MapPackage mapPackage) {
		tileMap.updateMap(mapPackage.tileArray);
		itemMap.updateMap(mapPackage.itemArray);
	}

	public static void setPlayerList(PlayerListPackage pListPackage) {
		credentialsList = pListPackage.getList();
		playerListString = "";
		for (Credentials c : credentialsList) {
			playerListString += c.username + "\n";
		}
	}

	public void update(Input input) {
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
		g.pushTransform();
		viewPort.renderStart(g);
		if (tileMap != null) {
			tileMap.render(g, viewPort);
		}
		if (itemMap != null) {
			itemMap.render(g);
		}
		for (Entry<Credentials, Player> p : playerList.entrySet()) {
			Player player = p.getValue();
			player.render(g);
		}

		viewPort.renderEnd(g);
		PlayerListDisplay.render(g);
		messageBoxDisplay.render(g);

		g.popTransform();
	}

	public void KeyPressed(int key, char chr) {
		messageBoxDisplay.keyPressed(key, chr);

	}

	public void KeyReleased(int key, char chr) {
		messageBoxDisplay.keyReleased(key, chr);

	}
}
