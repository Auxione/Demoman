package Curio.SessionManagers;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;

import Curio.Controllers.ControlPackage;
import Curio.Controllers.ObjectController;
import Curio.Controllers.Input.AI.AI;
import Curio.Physics.Interfaces.FrameUpdate;
import Curio.Renderer.ObjectRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.BombManager.BombManager;
import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.ItemManager.Inventory;
import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.SessionManagers.LogicManager.LogicManager;
import Curio.SessionManagers.PlantManager.PlantManager;
import Curio.SessionManagers.WorldManager.WorldManager;
import Curio.SessionManagers.WorldObjectManager.WorldObjectManager;
import Curio.Utilities.Math.Geometry.Circle;
import Default.Player;

public class PlayerManager implements Renderer, FrameUpdate {
	public static ArrayList<Player> playerList;
	public static HashMap<Player, ObjectRenderer> playerDisplayList;
	public static HashMap<Player, Inventory> playerInventoryList;
	public static HashMap<Player, ObjectController> playerControllerList;

	private BombManager bombManager;
	private FireManager fireManager;
	private PlantManager plantManager;
	private WorldManager worldManager;
	private WorldObjectManager gameObjectManager;
	private ItemManager itemManager;
	private LogicManager logicManager;
	private DynamicObjectManager dynamicObjectManager;
	private CollisionManager collisionManager;
	private AIManager aiManager;

	public PlayerManager(WorldManager worldManager, BombManager bombManager, FireManager fireManager,
			PlantManager plantManager, WorldObjectManager gameObjectManager, ItemManager itemManager,
			LogicManager logicManager, DynamicObjectManager dynamicObjectManager, CollisionManager collisionManager,
			AIManager aiManager) {

		playerList = new ArrayList<Player>();
		playerDisplayList = new HashMap<Player, ObjectRenderer>();
		playerInventoryList = new HashMap<Player, Inventory>();
		playerControllerList = new HashMap<Player, ObjectController>();

		this.logicManager = logicManager;
		this.bombManager = bombManager;
		this.fireManager = fireManager;
		this.plantManager = plantManager;
		this.worldManager = worldManager;
		this.gameObjectManager = gameObjectManager;
		this.itemManager = itemManager;
		this.dynamicObjectManager = dynamicObjectManager;
		this.collisionManager = collisionManager;
		this.aiManager = aiManager;
	}

	public Player Create(ControlPackage controlPackage) {
		Circle c = new Circle().setRadius(10);
		Player player = new Player(c);
		ObjectRenderer playerDisplay = new ObjectRenderer(player).setObjectShape(c);
		Inventory playerInventory = new Inventory(6, 1, 5);

		ObjectController playerController;

		if (controlPackage != null) {
			playerController = new ObjectController(player).setControlPackage(controlPackage);
		}

		else {
			AI ai = aiManager.createPlayerAI(player);
			playerController = new ObjectController(player).setControlPackage(ai.getPackage());
		}

		playerList.add(player);
		playerDisplayList.put(player, playerDisplay);
		playerInventoryList.put(player, playerInventory);
		playerControllerList.put(player, playerController);
		dynamicObjectManager.add(player).setTorqueCalculation(false);
		collisionManager.addTilemapCollision(player);

		return player;
	}
	
	private void controllerActions() {
		for (Player player : playerList) {
			if (playerControllerList.get(player).controlPackage.ActionTake == true) {
				takePlayerItemFromItemMap(player, playerControllerList.get(player).controlPackage);
				plantManager.harvest(player);
			}

			if (playerControllerList.get(player).controlPackage.ActionUseItem == true) {
				useItem(player, player);
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

	private void takePlayerItemFromItemMap(Player player, ControlPackage controlPackage) {
		Item item = itemManager.getItemFromCell(player.cellCoordinate);
		if (item != null) {
			if (playerInventoryList.get(player).putItemToCell(item) == true) {
				itemManager.removeItem(player.cellCoordinate, 1);
			}
		}
	}

	public void useItem(Player player, Player playerToUse) {
		if (playerInventoryList.get(player).getItemFromIndex() != null) {
			if (playerInventoryList.get(player).getItemFromIndex().condition(worldManager, gameObjectManager,
					bombManager, plantManager, playerToUse) == true) {
				playerInventoryList.get(player).getItemFromIndex().apply(worldManager, gameObjectManager, bombManager,
						plantManager, playerToUse);
				playerInventoryList.get(player).removeItemFromIndex(1);
			}
		}
	}

	public void render(Graphics g) {
		for (Player player : playerList) {
			playerDisplayList.get(player).render(g);
		}
	}

	@Override
	public void frameUpdate() {
		for (Player player : playerList) {
			playerControllerList.get(player).update();
		}

		for (Player player : playerList) {
			fireManager.update(player);
			bombManager.update(player);
			player.update();
		}
		controllerActions();
	}
}
