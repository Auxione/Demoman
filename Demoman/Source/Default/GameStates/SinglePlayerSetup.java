package Default.GameStates;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Console;
import Curio.HUD.Button;
import Curio.HUD.Inputbox;
import Default.Main;

public class SinglePlayerSetup {
	private Inputbox mapSizeXInputBox, mapSizeYInputBox;
	private Button startGameButton, backButton;

	private Console console;

	private int mapSizeXVal = 20;
	private int mapSizeYVal = 20;

	public SinglePlayerSetup(Console console) {
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

		this.mapSizeXInputBox.inputEvent(input);
		this.mapSizeYInputBox.inputEvent(input);

		if (mapSizeXInputBox.Completed == true) {
			if (mapSizeXInputBox.getInput() != "") {
				mapSizeXVal = Integer.parseInt(mapSizeXInputBox.getInput());
			}
		}

		else if (mapSizeYInputBox.Completed == true) {
			if (mapSizeYInputBox.getInput() != "") {
				mapSizeYVal = Integer.parseInt(mapSizeYInputBox.getInput());
			}
		}

		if (startGameButton.pressed == true) {
			Main.singlePlayerSession = new SinglePlayerSession(mapSizeXVal, mapSizeYVal, console);
			Main.GameState = 11;
		}

		else if (backButton.pressed == true) {
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
