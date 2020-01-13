package Default.GameStates;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Console;
import Curio.Network.Credentials;
import Curio.Renderer.Button;
import Curio.Renderer.Inputbox;
import Default.Main;

public class MultiplayerSetup {
	private Console console;
	private Credentials selfCredentials;

	private Inputbox playerName, serverIP;
	private Button connectButton, backButton;

	private String ServerIPString = "";
	private String playerNameString = "";

	public MultiplayerSetup(Console console) {
		this.console = console;
		
		this.serverIP = new Inputbox(20, 20, 200, 50, "Server IP: ", 0);
		serverIP.setCompleteWhenFocusLoss(true);

		this.playerName = new Inputbox(20, 80, 200, 50, "Player Name: ", 0);
		playerName.setCompleteWhenFocusLoss(true);

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
			selfCredentials = new Credentials(playerNameString);
			console.Add(0, "Username: " + playerNameString);
			console.Add(0, "Connecting to: " + ServerIPString);
			Main.multiplayerSession = new MultiplayerSession(ServerIPString, 200, selfCredentials, console);

		}

		if (backButton.pressed == true) {
			Main.GameState = 0;
		}

		playerName.loopEnd();
		serverIP.loopEnd();
		connectButton.loopEnd();
		backButton.loopEnd();
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
