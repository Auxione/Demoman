package Curio.Network.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import Curio.Console;
import Curio.Network.Credentials;

public class ServerListener implements SocketListener {
	private Console console;
	private ArrayList<Connection> connectionList = ConnectionManager.getConnections();
	private HashMap<Connection, Credentials> credentialsList = new HashMap<Connection, Credentials>();

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
		DisplayUsers();
	}

	@Override
	public void received(Connection connection, Object object) {
		if (object instanceof Credentials) {
			credentialsList.put(connection, (Credentials) object);
			Main.addPlayer((Credentials) object);
		}
	}

	private void sendUDPEveryone(Object object) {
		for (Connection connection : connectionList) {
			connection.sendUdp(object);
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