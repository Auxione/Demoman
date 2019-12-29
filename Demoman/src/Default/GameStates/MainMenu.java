package Default.GameStates;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Console;
import Curio.HUD.Button;
import Default.Main;

public class MainMenu {
	private Button spButton, mpButton;
	private Console console;

	public MainMenu(Console console) {
		this.spButton = new Button(20, 20, 200, 50, "Singleplayer");
		this.mpButton = new Button(20, 80, 200, 50, "Multiplayer");
		this.console = console;
	}

	public void update(Input input) {
		spButton.inputEvent(input);
		mpButton.inputEvent(input);

		if (spButton.pressed) {
			Main.singlePlayerMenu = new SinglePlayerMenu(console);
			Main.GameState = 10;
		}

		else if (mpButton.pressed) {
			Main.multiplayer = new Multiplayer(console);
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
