package Curio.Controllers.Input.AI;

import Curio.GameObject;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Controllers.Input.AI.Tasks.AimToObjectTask;
import Curio.Controllers.Input.AI.Tasks.BlindDropItemTask;
import Curio.Controllers.Input.AI.Tasks.BlindMoveTask;
import Curio.Controllers.Input.AI.Tasks.BlindTakeItemFromGroundTask;
import Curio.Controllers.Input.AI.Tasks.BlindUseItemTask;
import Curio.Controllers.Input.AI.Tasks.FollowPathTask;
import Curio.Utilities.Math.Vector;
import Curio.Utilities.Navigation.Path;

public class PrimitiveTaskList {
	public PrimitiveTaskList() {
	}

	static public Task blindMove(AI ai, Vector targetPosition) {
		return new BlindMoveTask(ai, targetPosition);
	}

	static public Task blindTakeItemFromGround(AI ai) {
		return new BlindTakeItemFromGroundTask(ai);
	}

	static public Task blindUseItem(AI ai) {
		return new BlindUseItemTask(ai);
	}

	static public Task blindDropItem(AI ai) {
		return new BlindDropItemTask(ai);
	}

	static public Task followPath(AI ai, Path path) {
		return new FollowPathTask(ai, path);
	}

	static public Task aimToObject(AI ai, GameObject gameObject) {
		return new AimToObjectTask(ai, gameObject);
	}
}
