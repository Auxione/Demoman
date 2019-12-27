package Default;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Constants {
	public static int CellSize = 32;
	// tiles
	public static Image empty;
	public static Image grass;
	public static Image brick;
	public static Image metalframe;
	public static Image rock;
	public static Image wood;
	public static Image burntwood;
	public static Image burntgrass;
	public static Image Farmland;
//logic objects
	public static Image firestarter;
	public static Image barrel;
	public static Image itemspawner;
	public static Image switchon;
	public static Image switchoff;
	public static Image pushbutton;
	public static Image movetrigger;
//items
	public static Image medpack;
	public static Image stimpack;
	public static Image bombSizeUpgrade;
	public static Image bombFlameUpgrade;
	public static Image pizzaSlice;
	public static Image pizza;
	public static Image sausage;
	public static Image berries;
	public static Image SausageSeed;
//anims
	public static Image ExplosionSprite;
	public static Image FireSprite;
//bombs
	public static Image blueBombNormal;
	public static Image greenBombNormal;
	public static Image blueBombNapalm;
	public static Image greenBombNapalm;
//plants
	public static Image berryBushState1, berryBushState2, berryBushState3;
	public static Image sausagePlant1, sausagePlant2, sausagePlant3, sausagePlant4, sausagePlant5;

	public static void loadData() throws SlickException {
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
		Farmland = new Image("Data/Sprites/Tiles/farmland.png");

		medpack = new Image("Data/Sprites/Items/medpack.png");
		stimpack = new Image("Data/Sprites/Items/stimpack.png");
		bombSizeUpgrade = new Image("Data/Sprites/Items/sizeupgrade.png");
		bombFlameUpgrade = new Image("Data/Sprites/Items/flameupgr.png");

		pizzaSlice = new Image("Data/Sprites/Items/Food/PizzaSlice.png");
		pizza = new Image("Data/Sprites/Items/Food/Pizza.png");
		sausage = new Image("Data/Sprites/Items/Food/sausage.png");
		berries = new Image("Data/Sprites/Items/Food/berries.png");

		SausageSeed = new Image("Data/Sprites/Items/SausageSeed.png");

		berryBushState1 = new Image("/Data/Sprites/Plants/BerryBush1.png");
		berryBushState2 = new Image("/Data/Sprites/Plants/BerryBush2.png");
		berryBushState3 = new Image("/Data/Sprites/Plants/BerryBush3.png");

		sausagePlant1 = new Image("Data/Sprites/Plants/sausagePlant1.png");
		sausagePlant2 = new Image("Data/Sprites/Plants/sausagePlant2.png");
		sausagePlant3 = new Image("Data/Sprites/Plants/sausagePlant3.png");
		sausagePlant4 = new Image("Data/Sprites/Plants/sausagePlant4.png");
		sausagePlant5 = new Image("Data/Sprites/Plants/sausagePlant5.png");

		Main.console.Add(0, "Textures Loaded");
	}

	public static int[][] obj_Building = { //
			{ 4, 4, 4, 4, 4, 4, 4 }, //
			{ 4, 1, 1, 1, 1, 1, 4 }, //
			{ 4, 1, 1, 1, 1, 1, 4 }, //
			{ 4, 1, 1, 1, 1, 1, 4 }, //
			{ 4, 1, 1, 1, 1, 1, 4 }, //
			{ 4, 1, 1, 1, 1, 1, 4 }, //
			{ 4, 4, 4, 4, 4, 4, 4 }, //
	};

	public int[][] obj_Snake = { { 2, 2, 2, 2, 2, 2, 2 }, { 4, 4, 4, 1, 1, 4, 2 }, { 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 4, 4, 1, 1, 4, 4 }, { 2, 2, 2, 2, 2, 2, 2 }, { 4, 4, 4, 1, 1, 4, 2 }, { 2, 2, 2, 2, 2, 2, 2 }, };

}