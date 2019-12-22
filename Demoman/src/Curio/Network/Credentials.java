package Curio.Network;

import java.io.Serializable;

public class Credentials implements Serializable {
	public String username;

	public Credentials(String username) {
		this.username = username;
	}
}
