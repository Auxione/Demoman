package Curio.Controllers.Input.AI.Tasks;

import Curio.Console;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.AI;
import Curio.Controllers.Input.AI.Task;
import Curio.Physics.Time;

public class Wait implements Task {
	private Time Goal;
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
	public void Order(Time currentTime, int seconds) {
		this.Goal = new Time(currentTime);
		this.Goal.addSecond(seconds);
		this.GoalTimeRemaining = currentTime.getSeconds() - Goal.getSeconds();
		this.active = true;

		if (console != null) {
			console.Add(0, "Waiting here for: " + GoalTimeRemaining + " seconds.");
		}
	}

	@Override
	public void update(ControlPackage controlPackage, Time currentTime) {
		this.GoalTimeRemaining = currentTime.getSeconds() - Goal.getSeconds();
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
