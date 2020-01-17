package Curio.SessionManagers;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.Viewport;
import Curio.AI.AI;
import Curio.GameObject.ObjectController;
import Curio.GameObject.ObjectRenderer;
import Curio.GameObject.Controllers.ControlPackage;
import Curio.ItemSystem.Inventory;
import Curio.ItemSystem.Item;
import Curio.ItemSystem.ItemMap;
import Curio.Physics.DynamicObject;
import Curio.Physics.TilemapCollision;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.GameObjectManager.WorldObjectManager;
import Default.Player;

public class PlayerManager {
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	public static HashMap<Player, ObjectRenderer> playerDisplayList = new HashMap<Player, ObjectRenderer>();
	public static HashMap<Player, Inventory> playerInventoryList = new HashMap<Player, Inventory>();
	public static HashMap<Player, DynamicObject> dynamicObjectList = new HashMap<Player, DynamicObject>();
	public static HashMap<Player, ObjectController> playerControllerList = new HashMap<Player, ObjectController>();
	public static HashMap<Player, TilemapCollision> tilemapCollisionList = new HashMap<Player, TilemapCollision>();
	public static HashMap<Player, AI> aiList = new HashMap<Player, AI>();

	private ItemMap itemMap;
	private Console console;

	private BombManager bombManager;
	private FireManager fireManager;
	private PlantManager plantManager;
	private WorldManager worldManager;
	private WorldObjectManager gameObjectManager;

	public PlayerManager(WorldManager worldManager, BombManager bombManager, FireManager fireManager,
			PlantManager plantManager, WorldObjectManager gameObjectManager, ItemMap itemMap) {
		this.bombManager = bombManager;
		this.fireManager = fireManager;
		this.plantManager = plantManager;
		this.worldManager = worldManager;
		this.gameObjectManager = gameObjectManager;
		this.itemMap = itemMap;
	}

	public PlayerManager setConsole(Console console) {
		this.console = console;
		return this;
	}

	public Player Create(ControlPackage controlPackage) {
		Player player = new Player();
		ObjectRenderer playerDisplay = new ObjectRenderer(player).setSize(player.psize);
		Inventory playerInventory = new Inventory(6, 1, 5);
		DynamicObject dynamicObject = new DynamicObject(player).setSize(player.psize);
		ObjectController playerController;

		if (controlPackage != null) {
			playerController = new ObjectController(dynamicObject).setControlPackage(controlPackage);
		}

		else {
			AI ai = new AI(worldManager.tileMap, itemMap, player);
			if (this.console != null) {
				ai.setConsole(console);
			}

			aiList.put(player, ai);
			playerController = new ObjectController(dynamicObject).setControlPackage(ai.getPackage());
		}

		TilemapCollision collision = new TilemapCollision(this.worldManager.tileMap, dynamicObject);

		playerList.add(player);
		playerDisplayList.put(player, playerDisplay);
		playerInventoryList.put(player, playerInventory);
		dynamicObjectList.put(player, dynamicObject);
		playerControllerList.put(player, playerController);
		tilemapCollisionList.put(player, collision);

		return player;
	}

	private void controllerActions() {
		for (Player player : playerList) {
			if (playerControllerList.get(player).controlPackage.ActionTake == true) {
				takePlayerItemFromItemMap(player);
				plantManager.harvest(player);
			}

			if (playerControllerList.get(player).controlPackage.ActionUse == true) {
				useItem(player);
			}

			if (playerControllerList.get(player).controlPackage.ActionSwitchItem == true) {
				playerInventoryList.get(player).changeIndex();
			}

			if (playerControllerList.get(player).controlPackage.ActionDrop == true) {
				dropPlayerItem(player);
			}
		}
	}

	private void dropPlayerItem(Player player) {
		Item item = playerInventoryList.get(player).getItemFromIndex();
		if (item != null) {
			itemMap.put(player.cellCoordinate, item);
			playerInventoryList.get(player).removeItemFromIndex(1);
		}
	}

	private void takePlayerItemFromItemMap(Player player) {
		Item item = itemMap.getItemFromCell(player.cellCoordinate);
		if (item != null) {
			if (playerInventoryList.get(player).putItemToCell(item) == true) {
				itemMap.removeItem(player.cellCoordinate, 1);
			}
		}
	}

	private void useItem(Player player) {
		if (playerInventoryList.get(player).getItemFromIndex() != null) {
			if (playerInventoryList.get(player).getItemFromIndex().condition(worldManager, gameObjectManager,
					bombManager, plantManager, player) == true) {
				playerInventoryList.get(player).getItemFromIndex().apply(worldManager, gameObjectManager, bombManager,
						plantManager, player);
				playerInventoryList.get(player).removeItemFromIndex(1);
			}
		}
	}

	public void render(Viewport viewPort, Graphics g) {
		for (Player player : playerList) {
			viewPort.renderOnWorld(playerDisplayList.get(player), g);
		}
	}

	public void updateStart() {
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

	public void updateEnd() {
		controllerActions();
		for (Player player : playerList) {
			playerControllerList.get(player).updateEnd();
		}
	}
}
