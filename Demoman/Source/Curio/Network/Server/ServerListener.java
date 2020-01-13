package Curio.Network.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import Curio.Console;
import Curio.Network.ChatMessagePackage;
import Curio.Network.Credentials;
import Curio.Network.GameRulesPackage;
import Curio.Network.PlayerListPackage;

public class ServerListener implements SocketListener {
	private Console console;
	private ArrayList<Connection> connectionList = ConnectionManager.getConnections();
	private HashMap<Connection, Credentials> credentialsList = new HashMap<Connection, Credentials>();

	private PlayerListPackage playerListPackage = new PlayerListPackage();

	public ServerListener(Console console) {
		this.console = console;
	}

	public void connected(Connection connection) {
		ConnectionManager.addConnection(connection);
		console.Add(0, "Client connected.");
	}

	@Override
	public void disconnected(Connection connection) {
		Credentials credentials = credentialsList.get(connection);
		credentialsList.remove(connection);
		Main.removePlayer(credentials);

		String uname = credentials.username;
		console.Add(0, uname + " disconnected.");
		sendTCPEveryone(playerListPackage);
	}

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof Credentials) {
			Credentials credentials = (Credentials) object;
			credentialsList.put(connection, credentials);
			playerListPackage.add(credentials);
			Main.addPlayer(credentials);

			sendTCPEveryone(playerListPackage);
			DisplayUsers();

			connection.sendTcp(Main.gameRules);
			// connection.sendTcp(Main.mapPackage);
		}

		else if (object instanceof ChatMessagePackage) {
			ChatMessagePackage chatMessagePackage = (ChatMessagePackage) object;
			console.Add(2, chatMessagePackage.credentials.username + " : " + chatMessagePackage.ChatMessage);
			sendTCPEveryone(chatMessagePackage);
		}
	}

	private void sendTCPEveryone(Object object) {
		for (Connection connection : connectionList) {
			connection.sendTcp(object);
		}
	}

	private void DisplayUsers() {
		System.out.println("Server Playerlist:");
		System.out.println("--------");
		for (Entry<Connection, Credentials> c : credentialsList.entrySet()) {
			System.out.println("-> " + c.getValue().username);
		}
		System.out.println("--------");
	}
}