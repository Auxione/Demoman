package Curio.Network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import Default.Player;

public class PlayerList implements Serializable {
	public ArrayList<Credentials> CredentialsList = new ArrayList<Credentials>();
	
	public PlayerList(ArrayList<Credentials> CredentialsList) {
		this.CredentialsList = CredentialsList;
	}

	public void addCredentials(Credentials credentials) {
		if (CredentialsList.contains((Credentials) credentials) == false) {
			CredentialsList.add(credentials);
		}
	}

	public void removeCredentials(Credentials credentials) {
		if (CredentialsList.contains((Credentials) credentials) == true) {
			CredentialsList.remove(credentials);
		}
	}
}
