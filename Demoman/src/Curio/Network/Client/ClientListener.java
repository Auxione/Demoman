package Curio.Network.Client;

import com.jmr.wrapper.common.Connection;
import com.jmr.wrapper.common.listener.SocketListener;

import Curio.Console;
import Curio.Network.Credentials;

public class ClientListener implements SocketListener {
	private Credentials crendentials;
	private Console console;

	public ClientListener(String clientName, Console console) {
		this.crendentials = new Credentials(clientName);
		this.console = console;
	}

	@Override
	public void connected(Connection connection) {
		connection.sendUdp(crendentials);
	}

	@Override
	public void disconnected(Connection connection) {
		console.Add(0, "disconnected from: " + connection.getAddress());
		connection.close();
	}

	@Override
	public void received(Connection connection, Object object) {

	}
}