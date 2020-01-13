package Curio.Network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Default.Player;

public class PlayerDataPackage implements Serializable {
	public ArrayList<Credentials> CredentialsList = new ArrayList<Credentials>();
	public HashMap<Credentials, Player> playerMap = new HashMap<Credentials, Player>();
	public HashMap<Credentials, int[][]> inventoryMap = new HashMap<Credentials, int[][]>();

	public PlayerDataPackage(ArrayList<Credentials> CredentialsList) {
		this.CredentialsList = CredentialsList;
	}

	public PlayerDataPackage addPlayerMap(Credentials credentials, Player player) {
		this.playerMap.put(credentials, player);
		return this;
	}

	public PlayerDataPackage addPlayerInventoryMap(Credentials credentials, int[][] iMap) {
		this.inventoryMap.put(credentials, iMap);
		return this;
	}
}
