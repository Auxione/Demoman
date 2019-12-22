package Curio.Network;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerListPackage implements Serializable {
	private ArrayList<Credentials> CredentialsList = new ArrayList<Credentials>();

	public PlayerListPackage() {
	}

	public void add(Credentials cre) {
		if (CredentialsList.contains((Credentials) cre) == false) {
			CredentialsList.add(cre);
		}
	}

	public void remove(Credentials cre) {
		if (CredentialsList.contains((Credentials) cre) == true) {
			CredentialsList.remove(cre);
		}
	}

	public ArrayList<Credentials> getList() {
		return CredentialsList;
	}
}
