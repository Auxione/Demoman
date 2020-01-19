package Curio.SessionManagers;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.ObjectController;
import Curio.Controllers.AI.AI;
import Curio.Physics.TilemapCollision;
import Curio.Renderer.ObjectRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.Inventory;
import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.SessionManagers.LogicManager.LogicManager;
import Curio.SessionManagers.LogicManager.Interfaces.LogicTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Curio.Utilities.CellCoordinate;
import Default.Player;

public class PlayerManager implements Renderer {
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	public static HashMap<Player, ObjectRenderer> playerDisplayList = new HashMap<Player, ObjectRenderer>();
	public static HashMap<Player, Inventory> playerInventoryList = new HashMap<Player, Inventory>();
	public static HashMap<Player, ObjectController> playerControllerList = new HashMap<Player, ObjectController>();
	public static HashMap<Player, TilemapCollision> tilemapCollisionList = new HashMap<Player, TilemapCollision>();
	public static HashMap<Player, AI> aiList = new HashMap<Player, AI>();

	private Console console;

	private BombManager bombManager;
	private FireManager fireManager;
	private PlantManager plantManager;
	private WorldManager worldManager;
	private WorldObjectManager gameObjectManager;
	private ItemManager itemManager;
	private LogicManager logicManager;

	public PlayerManager(WorldManager worldManager, BombManager bombManager, FireManager fireManager,
			PlantManager plantManager, WorldObjectManager gameObjectManager, ItemManager itemManager,
			LogicManager logicManager) {
		this.logicManager = logicManager;
		this.bombManager = bombManager;
		this.fireManager = fireManager;
		this.plantManager = plantManager;
		this.worldManager = worldManager;
		this.gameObjectManager = gameObjectManager;
		this.itemManager = itemManager;
	}

	public PlayerManager setConsole(Console console) {
		this.console = console;
		return this;
	}

	public Player Create(ControlPackage controlPackage) {
		Player player = new Player();
		ObjectRenderer playerDisplay = new ObjectRenderer(player).setObjectImageSize(player.psize);
		Inventory playerInventory = new Inventory(6, 1, 5);

		ObjectController playerController;

		if (controlPackage != null) {
			playerController = new ObjectController(player).setControlPackage(controlPackage);
		}

		else {
			AI ai = new AI(worldManager.tileMap, itemManager.itemMap, player);
			if (this.console != null) {
				ai.setConsole(console);
			}

			aiList.put(player, ai);
			playerController = new ObjectController(player).setControlPackage(ai.getPackage());
		}

		TilemapCollision collision = new TilemapCollision(this.worldManager.tileMap, player);

		playerList.add(player);
		playerDisplayList.put(player, playerDisplay);
		playerInventoryList.put(player, playerInventory);
		playerControllerList.put(player, playerController);
		tilemapCollisionList.put(player, collision);

		DynamicObjectManager.add(player);

		return player;
	}

	private void controllerActions() {
		for (Player player : playerList) {
			if (playerControllerList.get(player).controlPackage.ActionTake == true) {
				takePlayerItemFromItemMap(player);
				plantManager.harvest(player);
			}

			if (playerControllerList.get(player).controlPackage.ActionUseItem == true) {
				useItem(player);
			}

			if (playerControllerList.get(player).controlPackage.ActionSwitchItem == true) {
				playerInventoryList.get(player).changeIndex();
			}

			if (playerControllerList.get(player).controlPackage.ActionDrop == true) {
				dropPlayerItem(player);
			}
			logicManager.onTopTrigger(player.cellCoordinate);
			if (playerControllerList.get(player).controlPackage.ActionUse == true) {
				logicManager.activateTrigger(player.cellCoordinate);
			}

		}
	}

	private void dropPlayerItem(Player player) {
		Item item = playerInventoryList.get(player).getItemFromIndex();
		if (item != null) {
			itemManager.put(player.cellCoordinate, item);
			playerInventoryList.get(player).removeItemFromIndex(1);
		}
	}

	private void takePlayerItemFromItemMap(Player player) {
		Item item = itemManager.getItemFromCell(player.cellCoordinate);
		if (item != null) {
			if (playerInventoryList.get(player).putItemToCell(item) == true) {
				itemManager.removeItem(player.cellCoordinate, 1);
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

	public void render(Graphics g) {
		for (Player player : playerList) {
			playerDisplayList.get(player).render(g);
			;
		}
	}

	public void updateStart() {
		for (Player player : playerList) {
			if (aiList.get(player) != null) {
				aiList.get(player).update(worldManager.worldTime.getTime());
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
