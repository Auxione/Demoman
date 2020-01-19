package Curio.Network.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import Curio.Console;
import Curio.Controllers.ControlPackage;
import Curio.Network.ChatMessagePackage;
import Curio.Network.Credentials;

public class ServerListener implements SocketListener {
	private Console console;
	private ArrayList<Connection> connectionList = ConnectionManager.getConnections();
	private HashMap<Connection, Credentials> credentialsList = new HashMap<Connection, Credentials>();
	private HashMap<Credentials, Connection> connectionsList = new HashMap<Credentials, Connection>();

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
		Main.removePlayer(credentials);

		String uname = credentials.username;
		console.Add(0, uname + " disconnected.");

		credentialsList.remove(connection);
		connectionsList.remove(credentials);

		sendTCPEveryone(Main.playerList);
		connection.close();
	}

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof Credentials) {
			Credentials credentials = (Credentials) object;
			credentialsList.put(connection, credentials);
			connectionsList.put(credentials, connection);

			connection.sendTcp(Main.gameRules);
			connection.sendTcp(Main.mapPackage);

			Main.addPlayer(credentials);
			Main.playerList.addCredentials(credentials);

			DisplayUsers();
			sendTCPEveryone(Main.playerList);
		}

		else if (object instanceof ChatMessagePackage) {
			ChatMessagePackage chatMessagePackage = (ChatMessagePackage) object;
			console.Add(2, credentialsList.get(connection).username + " : " + chatMessagePackage.ChatMessage);
			sendTCPEveryone(chatMessagePackage);
		}

		else if (object instanceof ControlPackage) {
			ControlPackage controlPackage = (ControlPackage) object;

			Main.passControls(credentialsList.get(connection), controlPackage);
		}
	}

	public void sendTCPEveryone(Object object) {
		for (Connection connection : connectionList) {
			connection.sendTcp(object);
		}
	}

	public void sendUDPto(Credentials credentials, Object object) {
		connectionsList.get(credentials).sendUdp(object);
	}

	public void sendTCPto(Credentials credentials, Object object) {
		connectionsList.get(credentials).sendTcp(object);
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