package Curio.Network.Client;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import Curio.Console;
import Curio.Network.ChatMessagePackage;
import Curio.Network.Credentials;
import Curio.Network.GameRulesPackage;
import Curio.Network.MapPackage;
import Curio.Network.PlayerDataPackage;
import Curio.Network.PlayerList;
import Default.Main;
import Default.GameStates.MultiplayerSession;

public class ClientListener implements SocketListener {
	private Credentials selfCredentials;
	private Console console;

	public ClientListener(Credentials selfCredentials, Console console) {
		this.selfCredentials = selfCredentials;
		this.console = console;
	}

	@Override
	public void connected(Connection connection) {
		connection.sendTcp(selfCredentials);
	}

	@Override
	public void disconnected(Connection connection) {
		console.Add(0, "disconnected from: " + connection.getAddress().getHostAddress());
		connection.close();
		Main.GameState = 0;
		Main.multiplayerSession = null;
		Main.multiplayerSetup = null;
	}

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof GameRulesPackage) {
			console.Add(0, "Recieved rules from server.");
			GameRulesPackage gameRules = (GameRulesPackage) object;
			MultiplayerSession.setRules(gameRules);
			Main.GameState = 21;
		}

		else if (object instanceof MapPackage) {
			console.Add(0, "Recieved map data from server.");
			MapPackage mapPackage = (MapPackage) object;
			MultiplayerSession.CreateNewMap(mapPackage);
		}

		else if (object instanceof PlayerList) {
			PlayerList playerListPackage = (PlayerList) object;
			MultiplayerSession.setPlayerList(playerListPackage);
		}

		else if (object instanceof PlayerDataPackage) {
			PlayerDataPackage playerDataPackage = (PlayerDataPackage) object;
			MultiplayerSession.setPlayerData(playerDataPackage);
		}

		else if (object instanceof ChatMessagePackage) {
			ChatMessagePackage chatMessagePackage = (ChatMessagePackage) object;

			String MessageString = chatMessagePackage.ChatMessage;
			String UsernameString = chatMessagePackage.credentials.username;
			console.Add(2, UsernameString + " : " + MessageString);
		}
	}
}