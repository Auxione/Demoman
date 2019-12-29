package Default.GameStates;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Console;
import Curio.HUD.Button;
import Curio.HUD.Inputbox;
import Default.Main;

public class SinglePlayerMenu {
	private Inputbox mapSizeXInputBox, mapSizeYInputBox;
	private Button startGameButton, backButton;

	private Console console;

	private int mapSizeXVal = 10;
	private int mapSizeYVal = 10;

	public SinglePlayerMenu(Console console) {
		this.mapSizeXInputBox = new Inputbox(20, 20, 200, 50, "Map Size x: ", 1);
		this.mapSizeYInputBox = new Inputbox(20, 80, 200, 50, "Map Size y: ", 1);
		this.startGameButton = new Button(20, 140, 200, 50, "Start");
		this.backButton = new Button(20, 200, 200, 50, "Back");

		this.console = console;
		this.console.Add(0, "Creating Singleplayer Game");
	}

	public void update(Input input) {
		startGameButton.inputEvent(input);
		backButton.inputEvent(input);
		mapSizeXInputBox.inputEvent(input);
		mapSizeYInputBox.inputEvent(input);

		if (mapSizeXInputBox.Completed == true) {
			mapSizeXVal = Integer.parseInt(mapSizeXInputBox.getInput());
		}

		if (mapSizeYInputBox.Completed == true) {
			mapSizeYVal = Integer.parseInt(mapSizeYInputBox.getInput());
		}

		if (startGameButton.pressed == true) {
			Main.singlePlayerSession = new SinglePlayerSession(mapSizeXVal, mapSizeYVal, console);
			Main.GameState = 11;
		}

		if (backButton.pressed == true) {
			Main.GameState = 0;
		}

		mapSizeXInputBox.loopEnd();
		mapSizeYInputBox.loopEnd();
	}

	public void render(Graphics g) {
		mapSizeXInputBox.render(g);
		mapSizeYInputBox.render(g);
		startGameButton.render(g);
		backButton.render(g);
	}

	public void KeyPressed(int key, char chr) {
		mapSizeXInputBox.keyPressed(key, chr);
		mapSizeYInputBox.keyPressed(key, chr);
	}

	public void KeyReleased(int key, char chr) {
		mapSizeXInputBox.keyReleased(key, chr);
		mapSizeYInputBox.keyReleased(key, chr);
	}
}
