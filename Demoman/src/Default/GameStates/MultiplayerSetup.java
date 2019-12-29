package Default.GameStates;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Console;
import Curio.HUD.Button;
import Curio.HUD.Inputbox;
import Curio.Network.Credentials;
import Curio.Network.Client.*;
import Default.Main;

public class MultiplayerSetup {
	private Console console;
	private ClientStarter client;
	private ClientListener clientListener;
	private Credentials selfCredentials;

	private Inputbox playerName, serverIP;
	private Button connectButton, backButton;

	private String ServerIPString = "";
	private String playerNameString = "";

	public MultiplayerSetup(Console console) {
		this.console = console;
		this.serverIP = new Inputbox(20, 20, 200, 50, "Server IP: ", 0);
		this.playerName = new Inputbox(20, 80, 200, 50, "Player Name: ", 0);

		this.connectButton = new Button(20, 140, 200, 50, "Connect");
		this.backButton = new Button(20, 200, 200, 50, "Back");
	}

	public void update(Input input) {
		connectButton.inputEvent(input);
		backButton.inputEvent(input);

		playerName.inputEvent(input);
		serverIP.inputEvent(input);

		if (serverIP.Completed == true) {
			ServerIPString = serverIP.getInput();
		}

		if (playerName.Completed == true) {
			playerNameString = playerName.getInput();
		}

		if (connectButton.pressed == true) {
			console.Add(0, "Connecting to: " + ServerIPString);
			selfCredentials = new Credentials(playerNameString);
			clientListener = new ClientListener(selfCredentials, console);
			client = new ClientStarter(ServerIPString, 200, clientListener, console);

			if (client.isConnected() == true) {
				Main.GameState = 1;
			}

		}

		if (backButton.pressed == true) {
			Main.GameState = 0;
		}

		playerName.loopEnd();
		serverIP.loopEnd();
	}

	public void render(Graphics g) {
		serverIP.render(g);
		playerName.render(g);
		connectButton.render(g);
		backButton.render(g);
	}

	public void KeyPressed(int key, char chr) {
		playerName.keyPressed(key, chr);
		serverIP.keyPressed(key, chr);
	}

	public void KeyReleased(int key, char chr) {
		playerName.keyReleased(key, chr);
		serverIP.keyReleased(key, chr);
	}
}
