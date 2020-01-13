package Curio.AI.Tasks;

import Curio.Console;
import Curio.Functions;
import Curio.AI.AI;
import Curio.AI.Task;
import Curio.GameObject.Controllers.ControlPackage;

public class Wait implements Task {
	private int GoalTime = 0;
	private int GoalTimeRemaining;
	private boolean active = false;
	private AI ai;
	private Console console;

	public Wait(AI ai) {
		this.ai = ai;
	}

	public Wait setConsole(Console console) {
		this.console = console;
		return this;
	}

	@Override
	public void Order(int currentTime, int time) {
		GoalTime = currentTime + time;
		GoalTimeRemaining = GoalTime - Functions.millis();
		active = true;
		if (console != null) {
			console.Add(0, "Waiting here for: " + time+ "ms.");
		}
	}

	@Override
	public void update(ControlPackage controlPackage, int currentTime) {
		GoalTimeRemaining = GoalTime - currentTime;
		if (GoalTimeRemaining <= 0) {
			active = false;
			finished();
		}
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public void finished() {
		ai.isBusy = false;
	}

	@Override
	public boolean overrideLeftTimer() {
		// TODO Auto-generated method stub
		return false;
	}

}
