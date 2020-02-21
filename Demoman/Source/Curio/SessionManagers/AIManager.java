package Curio.SessionManagers;

import java.util.ArrayList;

import Curio.Controllers.Input.AI.AI;
import Curio.Controllers.Input.AI.PrimitiveTaskList;
import Curio.Controllers.Input.AI.Interfaces.Task;
import Curio.Physics.Interfaces.FLUpdate;
import Default.Player;

public class AIManager implements FLUpdate {
	public static ArrayList<AI> aiList;
	@SuppressWarnings("unused")
	private PrimitiveTaskList primitiveTaskList;

	public AIManager() {
		aiList = new ArrayList<AI>();
		primitiveTaskList = new PrimitiveTaskList();
	}

	public AI createPlayerAI(Player player) {
		AI ai = new AI(player);
		aiList.add(ai);
		return ai;
	}

	public AIManager addTasktoAI(AI ai, Task task) {
		ai.addTask(task);
		return this;
	}

	public AI getAI(int id) {
		return aiList.get(id);
	}
	
	public AI getLastCreatedAI() {
		return aiList.get(aiList.size()-1);
	}

	@Override
	public void FirstUpdate() {
		for (AI ai : aiList) {
			ai.FirstUpdate();
		}
	}

	@Override
	public void LastUpdate() {
		for (AI ai : aiList) {
			ai.LastUpdate();
		}
	}
}
