package Default;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Curio.Items.Item;
import Curio.Tilemap.Tilemap;
import Curio.Tilemap.Logic.Logic;

public class Main extends BasicGame {
	public static long millis_start_time = 0;

	static GameManager manager;
	static Tilemap level1;
	public static Input input;
	
	static AppGameContainer app;
	
	public Main(String title) {
		super(title);
	}

	
	public void init(GameContainer container) throws SlickException {
		Constants.loadData();
		Constants.InitTileset();

		level1 = new Tilemap(40, 22, Constants.CellSize, Constants.Tset);
		manager = new GameManager(level1);

		manager.currentLevel.create_RandomLevel();
		manager.currentLevel.put_TileObj(Constants.obj_Building, 3, 3);
		
		Logic.createLogicMap(manager.currentLevel);
		/*Logic.put_MoveTrigger(2, 2, new Transform(2,3));
		Logic.put_ItemSpawner(2, 3, 1);
		
		Logic.put_MoveTrigger(3, 2, new Transform(3,3));
		Logic.put_ItemSpawner(3, 3, 2);
		
		Logic.put_MoveTrigger(4, 2, new Transform(4,3));
		Logic.put_ItemSpawner(4, 3, 3);
		
		Logic.put_MoveTrigger(5, 2, new Transform(5,3));
		Logic.put_ItemSpawner(5, 3, 4);
		*/
		Item.createItemMap(manager.currentLevel);
		
		manager.createPlayer(144, 272, 1);
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		input = container.getInput();
		manager.update(delta);

	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		manager.render(g);
	}

	public void keyPressed(int key, char c) {
		manager.KeyPressed(key,c);
	}

	public void keyReleased(int key, char c) {
		manager.KeyReleased(key,c);
	}

	public static void main(String[] args) throws SlickException {
		millis_start_time = System.currentTimeMillis();

		app = new AppGameContainer(new Main("Demoman"));
		app.setDisplayMode(1280, 720, false);
		app.setAlwaysRender(false);
		app.setShowFPS(true);
		app.setVSync(true);
		app.start();
		
	}

}
