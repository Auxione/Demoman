package Default;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Curio.Tileset;
import Curio.HUD.ConsoleDisplay;
import Default.GameStates.MainMenu;
import Default.GameStates.Multiplayer;
import Default.GameStates.SinglePlayer;

public class Main extends BasicGame {
	public static long millis_start_time = 0;
	public static int DisplayWidth;
	public static int DisplayHeight;
	public static Input input;
	static AppGameContainer app;

	public static int GameState = 0;

	MainMenu mainmenu;
	SinglePlayer singleplayer;
	Multiplayer multiplayer;

	public static ConsoleDisplay console;

	public Main(String title) {
		super(title);
	}

	public void init(GameContainer container) throws SlickException {
		console = new ConsoleDisplay(10, 10, 600, 400);
		Constants.loadData();
		Tileset.InitTileset();

		mainmenu = new MainMenu();
		singleplayer = new SinglePlayer(console);
		multiplayer = new Multiplayer();
	}

	public void update(GameContainer container, int delta) throws SlickException {
		input = container.getInput();
		console.inputEvent(input);

		switch (GameState) {
		// main menu
		case 0:
			mainmenu.update(input);
			break;
		// singleplayer
		case 1:
			singleplayer.update(delta);
			break;
		// multiplayer
		case 2:
			break;
		}
		// manager
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		switch (GameState) {
		// main menu
		case 0:
			mainmenu.render(g);
			break;
		case 1:
			singleplayer.render(g);
			break;
		}

		console.render(g);
	}

	public void keyPressed(int key, char c) {
		switch (GameState) {
		// main menu
		case 0:
			break;
		case 1:
			singleplayer.KeyPressed(key, c);
			break;
		}
		// manager.KeyPressed(key, c);
	}

	public void keyReleased(int key, char c) {
		switch (GameState) {
		// main menu
		case 0:
			break;
		case 1:
			singleplayer.KeyReleased(key, c);
			break;
		}
		// manager.KeyReleased(key, c);
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
