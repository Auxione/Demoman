package Curio.Controllers.Input.AI;

import java.util.LinkedList;
import java.util.Queue;

import Curio.GameObject;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Tasks.AimToObjectTask;
import Curio.Controllers.Input.AI.Tasks.BlindMoveTask;
import Curio.Controllers.Input.AI.Tasks.FollowPathTask;
import Curio.Controllers.Input.AI.Tasks.TakeItemFromGroundTask;
import Curio.Controllers.Input.AI.Tasks.UseItemTask;
import Curio.Physics.Interfaces.FrameUpdate;
import Curio.SessionManagers.ItemManager.Inventory;
import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.ItemManager.ItemList;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Navigation.Path;
import Default.Player;
import Default.GameStates.SinglePlayerSession;

public class AI implements FrameUpdate {
	Queue<Task> Tasks = new LinkedList<Task>();
	// queue based system to control player actions

	private Task currentTask;
	private ControlPackage controlPackage = new ControlPackage();
	private Player player;
	private Inventory playerInventory;
	private TileMap tilemap;
	private ItemManager itemManager;

	public AI(TileMap tilemap, ItemManager itemManager, Player player, Inventory playerInventory) {
		this.tilemap = tilemap;
		this.itemManager = itemManager;
		this.player = player;
		this.playerInventory = playerInventory;
		
		CellCoordinate cc = new CellCoordinate(8, 5);
		blindMove(new Vector(cc.getWorldX(), cc.getWorldY()));
		takeItemFromGround();
		blindMove(new Vector(500, 500));
		useItem(ItemList.list.get(3));
		followPath(SinglePlayerSession.testPath);
	}

	@Override
	public void frameUpdate() {
		if (this.currentTask == null) {
			this.currentTask = Tasks.poll();
		}

		else if (this.currentTask != null) {
			if (this.currentTask.finished() == false) {
				this.currentTask.frameUpdate();
			}

			else if (this.currentTask.finished() == true) {
				this.currentTask = null;
			}
		}
	}

	public ControlPackage getPackage() {
		// TODO Auto-generated method stub
		return controlPackage;
	}

	private void blindMove(Vector targetPosition) {
		this.Tasks.add(new BlindMoveTask(controlPackage, player, targetPosition));
	}

	private void followPath(Path path) {
		this.Tasks.add(new FollowPathTask(controlPackage, player, path));
	}

	private void aimToObject(GameObject gameObject) {
		this.Tasks.add(new AimToObjectTask(controlPackage, player, gameObject));
	}

	private void takeItemFromGround() {
		this.Tasks.add(new TakeItemFromGroundTask(controlPackage, player, itemManager));
	}

	private void useItem(Item item) {
		this.Tasks.add(new UseItemTask(controlPackage, playerInventory, item));
	}
}
