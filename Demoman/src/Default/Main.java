package Default;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Curio.Items.Item;
import Curio.Tilemap.Tilemap;
import Curio.Tilemap.Tileset;
import Curio.Tilemap.Logic.Logic;

public class Main extends BasicGame {
	public static long millis_start_time = 0;
	public static int DisplayWidth;
	public static int DisplayHeight;

	static GameManager manager;
	static Tilemap level1;
	public static Input input;

	static AppGameContainer app;

	public Main(String title) {
		super(title);
	}

	public void init(GameContainer container) throws SlickException {
		Constants.loadData();
		Tileset.InitTileset();
		level1 = new Tilemap(40, 22, Constants.CellSize);
		manager = new GameManager(level1);

		manager.currentLevel.create_BlankLevel();
		manager.currentLevel.put_TileObj(Constants.obj_Building, 7, 7);

		Logic.createLogicMap(manager.currentLevel);
		Item.createItemMap(manager.currentLevel);
		
		Item.put(3, 3, 1);
		Item.put(3, 3, 1);
		
		Item.put(3, 4, 2);
		Item.put(3, 4, 2);

		Item.put(3, 5, 3);
		Item.put(3, 5, 3);
		
		Item.put(3, 6, 4);
		Item.put(3, 6, 4);
		Item.put(3, 6, 4);
		Item.put(3, 6, 4);
		Item.put(3, 6, 4);
		Item.put(3, 6, 4);
	}

	public void update(GameContainer container, int delta) throws SlickException {
		input = container.getInput();
		manager.update(delta);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		manager.render(g);
	}

	public void keyPressed(int key, char c) {
		manager.KeyPressed(key, c);
	}

	public void keyReleased(int key, char c) {
		manager.KeyReleased(key, c);
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
