package Default;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Engine.Tilemap.Tilemap;
import Engine.Tilemap.Logic.Logic;
import Engine.Utilities.Transform;

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

		level1 = new Tilemap(50, 27, Constants.CellSize, Constants.Tset);
		manager = new GameManager(level1);

		manager.currentLevel.create_BlankLevel();
		
		Logic.createLogicMap(manager,manager.currentLevel, 50, 27);
		Logic.put_SwitchButton(2, 2, new Transform(2,3));
		Logic.put_DynamicWall(2, 3, 4);
		
		manager.createPlayer(100, 100, 1);
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
		app.setDisplayMode(1600, 900, false);
		app.setAlwaysRender(false);
		app.setShowFPS(true);
		app.setVSync(true);
		app.start();
		
	}

}
