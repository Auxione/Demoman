package Curio.Network.Server;

import java.util.HashMap;
import java.util.Map.Entry;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.exceptions.NNCantStartServer;
import com.jmr.wrapper.common.listener.SocketListener;
import com.jmr.wrapper.server.Server;

import Curio.Tileset;
import Curio.HUD.ConsoleDisplay;
import Curio.ItemMap.Inventory;
import Curio.ItemMap.ItemMap;
import Curio.Network.Credentials;
import Curio.Network.PlayerListPackage;
import Curio.Network.PlayerPositionPackage;
import Curio.Physics.TilemapCollision;
import Curio.Tilemap.FireManager;
import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Constants;
import Default.Player;

public class ServerStarter {
	private Server server;
	static ConsoleDisplay console = new ConsoleDisplay(10, 10, 600, 400);

	public static HashMap<Connection, Credentials> credentialsList = new HashMap<Connection, Credentials>();
	public static HashMap<Credentials, Player> playerList = new HashMap<Credentials, Player>();
	public static HashMap<Credentials, Inventory> playerInventoryList = new HashMap<Credentials, Inventory>();
	public static HashMap<Credentials, TilemapCollision> collisionList = new HashMap<Credentials, TilemapCollision>();

	PlayerListPackage plistPackage = new PlayerListPackage();

	public TileMap level = new TileMap(30, 30, Constants.CellSize, console);
	public ItemMap itemMap;
	
	private FireManager fm;
	private BombManager bm;
	static ServerStarter sv;

	public static void main(String[] args) {
		Tileset.InitTileset();
		sv = new ServerStarter(200);
	}

	public ServerStarter(int port) {
		// create server with listener
		try {
			server = new Server(port, port);
			server.setListener(new ServerListener());
		} catch (NNCantStartServer e) {
			e.printStackTrace();
		}
		if (server.isConnected() == true) {
			console.Add(0, "Server started. Listening on port: " + port);
			// create map
			level.create_BlankLevel();
			itemMap = new ItemMap(level, 10);
			
			fm = new FireManager(level);
			bm = new BombManager(fm, level);
		}
	}

	void serverUpdate() {
		if (server.isConnected() == true) {

		}
	}

	void PlayerUpdate() {
		if (server.isConnected() == true) {
			for (Entry<Credentials, Player> dp : playerList.entrySet()) {
				Player player = dp.getValue();
				player.loop();

				for (Entry<Credentials, TilemapCollision> tc : collisionList.entrySet()) {
					TilemapCollision collision = tc.getValue();
					collision.checkCollisions();
				}
				fm.update(player);
				bm.update(player);
			}
		}
	}

	void MapUpdate() {
		// update map and send it to players

	}

	void createPlayer(Credentials cre, int posx, int posy) {
		Player dp = new Player(level, posx, posy);
		playerList.put(cre, dp);
		TilemapCollision tc = new TilemapCollision(level, dp);
		collisionList.put(cre, tc);
	}

	void removePlayer(Credentials cre) {
		playerList.remove(cre);
		collisionList.remove(cre);
	}

	public class ServerListener implements SocketListener {
		@Override
		public void connected(Connection con) {
			System.out.println("Client connected. ");
			ConnectionManager.addConnection(con);
		}

		@Override
		public void disconnected(Connection con) {
			String uname = ServerStarter.credentialsList.get(con).username;
			System.out.println(uname + " disconnected.");
			ConnectionManager.removeConnection(con);
			credentialsList.remove(con);
		}

		@Override
		public void received(Connection con, Object object) {
			if (object instanceof Credentials) {
				Credentials cred = (Credentials) object;
				credentialsList.put(con, cred);
				plistPackage.add(cred);
				
				createPlayer(cred, 200, 200);

				// send leveldata to client
				con.sendUdp(plistPackage);
				// send new playerlist to everyone
				for (Connection c : ConnectionManager.getConnections()) {
					c.sendTcp(plistPackage);
				}

				DisplayUsers();
			} else if (object instanceof PlayerPositionPackage) {
				PlayerPositionPackage cpp = (PlayerPositionPackage) object;
				Player p = playerList.get(cpp.credentials);

				//p.set(cpp.position);

				for (Connection c : ConnectionManager.getConnections()) {
					c.sendTcp(cpp);
				}
			}
		}

		void DisplayUsers() {
			System.out.println("Playerlist:");
			System.out.println("--------");
			for (Entry<Connection, Credentials> c : ServerStarter.credentialsList.entrySet()) {
				System.out.println("-> " + c.getValue().username);
			}
			System.out.println("--------");
		}
	}
}
