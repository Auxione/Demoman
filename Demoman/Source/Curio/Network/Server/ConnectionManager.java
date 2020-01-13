package Curio.Network.Server;

import java.util.ArrayList;

import com.jmr.wrapper.common.Connection;

public class ConnectionManager {
	
	private static ArrayList<Connection> connections = new ArrayList<Connection>();
	
	public static void addConnection(Connection con) {
		connections.add(con);
	}
	
	public static void removeConnection(Connection con) {
		connections.remove(con);
	}
	
	public static ArrayList<Connection> getConnections() {
		return connections;
	}

}
