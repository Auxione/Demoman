package Curio.GameObject;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.TileMap;
import Curio.Viewport;
import Curio.AI.AI;
import Curio.BombManager.BombManager;
import Curio.FireManager.FireManager;
import Curio.GameObject.Controllers.ControlPackage;
import Curio.ItemSystem.Inventory;
import Curio.ItemSystem.ItemMap;
import Curio.Lighting.RoundLightSource;
import Curio.Lighting.RoundLightSourceRenderer;
import Curio.Physics.DynamicObject;
import Curio.Physics.TilemapCollision;
import Curio.PlantSystem.PlantMap;
import Curio.Renderer.MouseStatsDisplay;
import Curio.Utilities.Math.Transform;
import Default.Player;

//yeniden incelenip manager parcalarina ayrilmali

public class GameObjectManager {
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	public static HashMap<Player, ObjectRenderer> playerDisplayList = new HashMap<Player, ObjectRenderer>();
	public static HashMap<Player, Inventory> playerInventoryList = new HashMap<Player, Inventory>();
	public static HashMap<Player, DynamicObject> dynamicObjectList = new HashMap<Player, DynamicObject>();
	public static HashMap<Player, ObjectController> playerControllerList = new HashMap<Player, ObjectController>();
	public static HashMap<Player, TilemapCollision> tilemapCollisionList = new HashMap<Player, TilemapCollision>();
	public static HashMap<Player, AI> aiList = new HashMap<Player, AI>();

	public static ArrayList<RoundLightSource> roundLightSource = new ArrayList<RoundLightSource>();
	public static ArrayList<RoundLightSourceRenderer> roundLightSourceRenderer = new ArrayList<RoundLightSourceRenderer>();

	private TileMap tileMap;
	private ItemMap itemMap;
	private MouseStatsDisplay mouseStatsDisplay;
	private Console console;
	private BombManager bombManager;
	private FireManager fireManager;
	private Viewport viewPort;
	private PlantMap plantMap;

	public GameObjectManager(Viewport viewPort, BombManager bombManager, FireManager fireManager, TileMap tileMap,
			ItemMap itemMap, PlantMap plantMap, MouseStatsDisplay mouseStatsDisplay, Console console) {
		this.tileMap = tileMap;
		this.itemMap = itemMap;
		this.plantMap = plantMap;
		this.mouseStatsDisplay = mouseStatsDisplay;
		this.console = console;
		this.bombManager = bombManager;
		this.fireManager = fireManager;
		this.viewPort = viewPort;
	}

	public void render(Graphics g) {
		for (Player player : playerList) {
			viewPort.renderOnWorld(playerDisplayList.get(player), g);
		} 
		for (RoundLightSourceRenderer ls : roundLightSourceRenderer) {
			viewPort.renderOnWorld(ls, g);
		}
	}

	// ==============================================================================
	private void controllerActions() {
		for (Player player : playerList) {
			if (playerControllerList.get(player).controlPackage.ActionTake == true) {
				playerInventoryList.get(player).take();
				plantMap.harvest(player);
			}

			if (playerControllerList.get(player).controlPackage.ActionUse == true) {
				playerInventoryList.get(player).useSelf(bombManager, this, tileMap, plantMap);
			}

			if (playerControllerList.get(player).controlPackage.ActionSwitchItem == true) {
				playerInventoryList.get(player).switchCurrentItem();
			}

			if (playerControllerList.get(player).controlPackage.ActionDrop == true) {
				playerInventoryList.get(player).drop();
			}
		}
	}

	public void playerUpdateStart() {
		for (Player player : playerList) {
			if (aiList.get(player) != null) {
				aiList.get(player).update();
			}
		}

		for (Player player : playerList) {
			playerControllerList.get(player).updateStart();
		}

		for (Player player : playerList) {
			fireManager.update(player);
			bombManager.update(player);
			player.update();
		}

		for (Player player : playerList) {
			tilemapCollisionList.get(player).checkCollisions();
		}
	}

	public void playerupdateEnd() {
		controllerActions();

		for (Player player : playerList) {
			playerControllerList.get(player).updateEnd();
		}
	}

	public Player CreateNewPlayer(ControlPackage controlPackage) {
		Player player = new Player();
		ObjectRenderer playerDisplay = new ObjectRenderer(player).setSize(player.psize);
		Inventory playerInventory = new Inventory(itemMap, player, 4, 5);
		DynamicObject dynamicObject = new DynamicObject(tileMap, player).setSize(player.psize);
		ObjectController playerController;

		if (controlPackage != null) {
			playerController = new ObjectController(dynamicObject).setControlPackage(controlPackage);
		}

		else {
			AI ai = new AI(tileMap, itemMap, player).setConsole(console);
			aiList.put(player, ai);
			playerController = new ObjectController(dynamicObject).setControlPackage(ai.getPackage());
		}

		TilemapCollision collision = new TilemapCollision(tileMap, dynamicObject);

		playerList.add(player);
		playerDisplayList.put(player, playerDisplay);
		playerInventoryList.put(player, playerInventory);
		dynamicObjectList.put(player, dynamicObject);
		playerControllerList.put(player, playerController);
		tilemapCollisionList.put(player, collision);
		mouseStatsDisplay.addPlayer(player);

		return player;
	}

//==============================================================================
	public RoundLightSource CreateNewLightSource(Transform transform) {
		RoundLightSource r = new RoundLightSource().setColor(255, 255, 0, 255).setRadius(256);
		r.setTransform(transform);
		roundLightSource.add(r);
		roundLightSourceRenderer.add(new RoundLightSourceRenderer(r));
		return r;
	}
}
