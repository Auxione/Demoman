package Curio.Network.Client;

import com.jmr.wrapper.client.Client;
import com.jmr.wrapper.common.Connection;

import Curio.Console;

public class ClientStarter {
	private Client client;
	private Console console;

	public ClientStarter(String ip, int port, ClientListener clientListener, Console console) {
		this.console = console;

		this.client = new Client(ip, port, port);
		this.client.setListener(clientListener);
		this.client.connect();

		if (client.isConnected() == true) {
			this.console.Add(0, "Connected.");
		} else if (client.isConnected() == false) {
			this.console.Add(0, "Failed to connect.");
		}
	}

	public boolean isConnected() {
		return client.isConnected();
	}

	public Connection getConnection() {
		return client.getServerConnection();
	}
}