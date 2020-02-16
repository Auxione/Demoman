package Default.GameStates;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Console;
import Curio.Renderer.DisplayObjects.Button;
import Curio.Utilities.Math.Vector;
import Default.Main;

public class MainMenu {
	private Button spButton, mpButton;
	private Console console;

	public MainMenu(Console console) {
		this.spButton = new Button().setPosition(new Vector(20, 20)).setSize(200, 50).setButtonText("Singleplayer");
		this.mpButton = new Button().setPosition(new Vector(20, 80)).setSize(200, 50).setButtonText("Multiplayer");
		this.console = console;
	}

	public void update(Input input) {
		spButton.inputEvent(input);
		mpButton.inputEvent(input);

		if (spButton.pressed) {
			Main.singlePlayerSetup = new SinglePlayerSetup(console);
			Main.GameState = 10;
		}

		else if (mpButton.pressed) {
			Main.multiplayerSetup = new MultiplayerSetup(console);
			Main.GameState = 20;
		}

		spButton.loopEnd();
		mpButton.loopEnd();
	}

	public void render(Graphics g) {
		spButton.render(g);
		mpButton.render(g);
	}
}

