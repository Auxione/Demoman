package Default;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Curio.Console;
import Curio.Tileset;
import Curio.HUD.ConsoleDisplay;
import Default.GameStates.MainMenu;
import Default.GameStates.MultiplayerSession;
import Default.GameStates.MultiplayerSetup;
import Default.GameStates.SinglePlayerSession;
import Default.GameStates.SinglePlayerSetup;

public class Main extends BasicGame {
	public static long millis_start_time = 0;
	public static int DisplayWidth;
	public static int DisplayHeight;
	public static Input input;
	static AppGameContainer app;

	public static int GameState;

	public static MainMenu mainmenu;

	public static SinglePlayerSetup singlePlayerSetup;
	public static SinglePlayerSession singlePlayerSession;

	public static MultiplayerSetup multiplayerSetup;
	public static MultiplayerSession multiplayerSession;

	public static Console console;
	public static ConsoleDisplay consoleDisplay;

	public Main(String title) {
		super(title);
	}

	public void init(GameContainer container) throws SlickException {
		console = new Console();
		consoleDisplay = new ConsoleDisplay(20, 20, 600, 400, console);

		Constants.loadData();
		Tileset.InitTileset();

		mainmenu = new MainMenu(console);
		GameState = 0;
	}

	public void update(GameContainer container, int delta) throws SlickException {
		input = container.getInput();
		consoleDisplay.inputEvent(input);
		switch (GameState) {
		// main menu
		case 0:
			mainmenu.update(input);
			break;
		// singleplayer setup
		case 10:
			singlePlayerSetup.update(input);
			break;
		// singleplayer session
		case 11:
			singlePlayerSession.update(delta);
			break;
		// multiplayer
		case 20:
			multiplayerSetup.update(input);
			break;
		case 21:
			multiplayerSession.update(input);
			break;
		}
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		switch (GameState) {
		// main menu
		case 0:
			mainmenu.render(g);
			break;
		// singleplayer setup
		case 10:
			singlePlayerSetup.render(g);
			break;
		// singleplayer ingame
		case 11:
			singlePlayerSession.render(g);
			break;
		case 20:
			multiplayerSetup.render(g);
			break;
		case 21:
			multiplayerSession.render(g);
			break;
		}

		consoleDisplay.render(g);
	}

	public void keyPressed(int key, char c) {
		switch (GameState) {
		// main menu
		case 0:
			break;
		// singleplayer setup
		case 10:
			singlePlayerSetup.KeyPressed(key, c);
			break;
		// singleplayer ingame
		case 11:
			singlePlayerSession.KeyPressed(key, c);
			break;
		case 20:
			multiplayerSetup.KeyPressed(key, c);
			break;
		case 21:
			multiplayerSession.KeyPressed(key, c);
			break;
		}
	}

	public void keyReleased(int key, char c) {
		switch (GameState) {
		// main menu
		case 0:
			break;
		// singleplayer setup
		case 10:
			singlePlayerSetup.KeyReleased(key, c);
			break;
		// singleplayer ingame
		case 11:
			singlePlayerSession.KeyReleased(key, c);
			break;
		case 20:
			multiplayerSetup.KeyReleased(key, c);
			break;
		case 21:
			multiplayerSession.KeyReleased(key, c);
			break;
		}
	}

	public static void main(String[] args) throws SlickException {
		millis_start_time = System.currentTimeMillis();

		app = new AppGameContainer(new Main("Demoman"));

		app.setDisplayMode(1280, 720, false);
		DisplayWidth = app.getWidth();
		DisplayHeight = app.getHeight();

		app.setAlwaysRender(false);
		app.setShowFPS(true);
		app.setVSync(true);
		app.start();
	}
}
