package Curio.Network.Server;

import com.jmr.wrapper.common.exceptions.NNCantStartServer;
import com.jmr.wrapper.server.Server;

import Curio.Console;

public class ServerStarter {
	private Server server;
	private Console console = new Console();

	public ServerStarter(int port, ServerListener serverListener) {
		// create server with listener
		try {
			this.server = new Server(port, port);
			this.server.getConfig().PACKET_BUFFER_SIZE = 64000;
			this.server.setListener(serverListener);
		} catch (NNCantStartServer e) {
			e.printStackTrace();
		}
		if (server.isConnected() == true) {
			console.Add(0, "Server started. Listening on port: " + port);
		}
	}

	public boolean isConnected() {
		// TODO Auto-generated method stub
		return server.isConnected();
	}
}
