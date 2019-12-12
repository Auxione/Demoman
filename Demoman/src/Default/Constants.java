package Default;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Engine.Utilities.Tileset;

public class Constants {
	public static int CellSize = 32;

	public static Tileset Tset;

	// tiles
	public static Image empty = null, grass = null, brick = null, metalframe = null, rock = null, wood = null,burntwood =null, burntgrass =null;
//objects
	public static Image firestarter = null, barrel = null, itemspawner = null, switchon = null, switchoff = null,
			pushbutton = null, movetrigger = null;
//items
	public static Image medpack = null, stimpack = null, bombSizeUpgrade = null, bombFlameUpgrade = null;
//anims
	public static Image ExplosionSprite = null, FireSprite = null;
//bombs
	public static Image blueBombNormal = null, greenBombNormal = null,blueBombNapalm = null, greenBombNapalm = null;

	static void loadData() throws SlickException {
		// explosion sprite disaridan alindi:
		// https://opengameart.org/content/explosion-3
		ExplosionSprite = new Image("Data/Sprites/Animations/explosion.png");
		// explosion sound effect disaridan alindi:
		// https://opengameart.org/content/bombexplosion8bit
		// ExplosionSound = new SoundFile(this, "Data/explosion.wav");

		// fire sprite disaridan alindi
		// https://opengameart.org/content/fire-and-smoke-static-and-trail
		FireSprite = new Image("Data/Sprites/Animations/fireanim.png");

		blueBombNormal = new Image("Data/Sprites/Bombs/BlueBombNormal.png");
		greenBombNormal = new Image("Data/Sprites/Bombs/GreenBombNormal.png");
		blueBombNapalm = new Image("Data/Sprites/Bombs/BlueBombNapalm.png");
		greenBombNapalm = new Image("Data/Sprites/Bombs/GreenBombNapalm.png");

		barrel = new Image("Data/Sprites/Objects/barrel.png");
		itemspawner = new Image("Data/Sprites/Objects/itemspawner.png");
		switchon = new Image("Data/Sprites/Objects/switchon.png");
		switchoff = new Image("Data/Sprites/Objects/switchoff.png");
		pushbutton = new Image("Data/Sprites/Objects/pushbutton.png");
		movetrigger = new Image("Data/Sprites/Objects/movetrigger.png");
		firestarter = new Image("Data/Sprites/Objects/FireStarter.png");

		empty = new Image("Data/Sprites/Tiles/empty.png");
		grass = new Image("Data/Sprites/Tiles/grass.png");
		burntgrass = new Image("Data/Sprites/Tiles/burntgrass.png");
		rock = new Image("Data/Sprites/Tiles/rock.png");
		brick = new Image("Data/Sprites/Tiles/brick.png");
		metalframe = new Image("Data/Sprites/Tiles/metalframe.png");
		wood = new Image("Data/Sprites/Tiles/wood.png");
		burntwood = new Image("Data/Sprites/Tiles/BurntWood.png");

		medpack = new Image("Data/Sprites/Items/medpack.png");
		stimpack = new Image("Data/Sprites/Items/stimpack.png");
		bombSizeUpgrade = new Image("Data/Sprites/Items/sizeupgrade.png");
		bombFlameUpgrade = new Image("Data/Sprites/Items/flameupgr.png");
	}

	static int[][] obj_Building = { { 2, 2, 2, 2, 2, 2, 2 }, { 2, 1, 1, 1, 1, 1, 2 }, { 2, 1, 1, 2, 1, 1, 2 },
			{ 1, 1, 2, 2, 2, 1, 2 }, { 2, 1, 1, 2, 1, 1, 2 }, { 2, 1, 1, 1, 1, 1, 2 }, { 2, 2, 2, 2, 2, 2, 2 }, };

	static int[][] obj_Snake = { { 2, 2, 2, 2, 2, 2, 2 }, { 4, 4, 4, 1, 1, 4, 2 }, { 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 4, 4, 1, 1, 4, 4 }, { 2, 2, 2, 2, 2, 2, 2 }, { 4, 4, 4, 1, 1, 4, 2 }, { 2, 2, 2, 2, 2, 2, 2 }, };

	static void InitTileset() {
		Tset = new Tileset();
		Tset.createTile(0, "Empty", false, false, 0, true, empty);
		Tset.createTile(1, "grass", false, true, 400, true, grass);
		Tset.createTile(100, "burntgrass", false, false, 0, true, burntgrass);
		Tset.createTile(2, "wood", false, true, 1000, true, wood);
		Tset.createTile(200, "burntwood", false, false, 0, true, burntwood);
		Tset.createTile(3, "rock", true, false, 0, false, rock);
		Tset.createTile(4, "Brickwall", true, false, 0, false, brick);
		Tset.createTile(5, "metalframe", false, false, 0, false, metalframe);

	}
}