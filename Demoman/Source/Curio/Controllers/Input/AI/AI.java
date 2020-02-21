package Curio.Controllers.Input.AI;

import java.util.LinkedList;
import java.util.Queue;

import Curio.GameObject;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Tasks.AimToObjectTask;
import Curio.Controllers.Input.AI.Tasks.BlindMoveTask;
import Curio.Controllers.Input.AI.Tasks.BlindTakeItemFromGroundTask;
import Curio.Controllers.Input.AI.Tasks.BlindUseItemTask;
import Curio.Controllers.Input.AI.Tasks.FollowPathTask;
import Curio.Physics.DynamicObject;
import Curio.Physics.Interfaces.FLUpdate;
import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.ItemManager.ItemList;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Navigation.Path;
import Default.Player;

public class AI implements FLUpdate {
	private Queue<Task> Tasks = new LinkedList<Task>();
	// queue based system to control player actions

	private Task currentTask;
	private ControlPackage controlPackage = new ControlPackage();
	private ActionDecider actionDecider;

	public DynamicObject dynamicObject;

	public AI(DynamicObject dynamicObject) {
		this.dynamicObject = dynamicObject;
	}

	public AI setActionDecider(ActionDecider actionDecider) {
		this.actionDecider = actionDecider;
		return this;
	}
	
	public AI addTask(Task task) {
		Tasks.add(task);
		return this;
	}

	@Override
	public void FirstUpdate() {
		if (dynamicObject != null) {
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
	}

	@Override
	public void LastUpdate() {
		this.controlPackage.resetActions();
	}

	public ControlPackage getPackage() {
		// TODO Auto-generated method stub
		return controlPackage;
	}
}
