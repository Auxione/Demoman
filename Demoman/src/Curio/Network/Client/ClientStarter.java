package Curio.Network.Client;

import java.util.ArrayList;
import java.util.HashMap;

import com.jmr.wrapper.client.Client;
import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import Curio.ItemMap.Inventory;
import Curio.Network.Credentials;
import Curio.Network.MapPackage;
import Curio.Network.PlayerListPackage;
import Curio.Tilemap.TileMap;
import Default.Player;

public class ClientStarter {
	private Client client;

	static Credentials crendentials = new Credentials("cem");
	static TileMap level;

	private ArrayList<Credentials> CredentialsList = new ArrayList<Credentials>();
	public static HashMap<Credentials, Player> playerList = new HashMap<Credentials, Player>();
	public static HashMap<Credentials, Inventory> playerInventoryList = new HashMap<Credentials, Inventory>();

	public ClientStarter(String ip, int port) {
		client = new Client(ip, port, port);
		client.setListener(new ClientListener());
		client.connect();
	}

	public class ClientListener implements SocketListener {
		@Override
		public void connected(Connection con) {
			con.sendUdp(ClientStarter.crendentials);
		}

		@Override
		public void disconnected(Connection con) {
		}

		@Override
		public void received(Connection con, Object object) {
			if (object instanceof MapPackage) {
				MapPackage MPP = (MapPackage) object;
				level.update_Level(MPP.tilemap);
			} else if (object instanceof PlayerListPackage) {
				PlayerListPackage PLP = (PlayerListPackage) object;
				CredentialsList = PLP.getList();
				DisplayUsers(PLP);
			}
		}

		void DisplayUsers(PlayerListPackage p) {
			System.out.println("Playerlist from Server:");
			System.out.println("--------");
			for (Credentials c : p.getList()) {
				System.out.println("-> " + c.username);
			}
			System.out.println("--------");
		}
	}
}