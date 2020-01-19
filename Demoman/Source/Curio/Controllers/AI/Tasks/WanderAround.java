package Curio.Controllers.AI.Tasks;

import java.util.Random;

import Curio.Console;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.AI.AI;
import Curio.Controllers.AI.Task;
import Curio.Physics.Time;

public class WanderAround implements Task {
	private Time Goal;
	private int GoalTimeRemaining;
	private int wanderDir;
	private boolean active = false;
	private AI ai;
	private Console console;

	public WanderAround(AI ai) {
		this.ai = ai;
	}

	public WanderAround setConsole(Console console) {
		this.console = console;
		return this;
	}

	@Override
	public void Order(Time currentTime, int seconds) {
		wanderDir = getRandomDirection();
		
		this.Goal = new Time(currentTime);
		this.Goal.addSecond(seconds);
		this.GoalTimeRemaining = currentTime.getSeconds() - Goal.getSeconds();
		this.active = true;
		
		if (console != null) {
			console.Add(0, "Wandering Around for: " + GoalTimeRemaining + "ms.");
		}
	}

	@Override
	public void update(ControlPackage controlPackage, Time currentTime) {
		GoalTimeRemaining = Goal.getSeconds() - currentTime.getSeconds();

		if (GoalTimeRemaining > 0) {
			if (wanderDir == 1) {
				controlPackage.ActionNorth = true;
			}

			else if (wanderDir == 2) {
				controlPackage.ActionEast = true;
			}

			else if (wanderDir == 3) {
				controlPackage.ActionSouth = true;
			}

			else if (wanderDir == 4) {
				controlPackage.ActionWest = true;
			}

			else if (wanderDir == 12) {
				controlPackage.ActionNorth = true;
				controlPackage.ActionEast = true;
			}

			else if (wanderDir == 23) {
				controlPackage.ActionEast = true;
				controlPackage.ActionSouth = true;
			}

			else if (wanderDir == 34) {
				controlPackage.ActionSouth = true;
				controlPackage.ActionWest = true;

			} else if (wanderDir == 41) {
				controlPackage.ActionWest = true;
				controlPackage.ActionNorth = true;
			}
		}

		else if (GoalTimeRemaining <= 0) {
			active = false;
			finished();
		}
	}

	private int getRandomDirection() {
		int[] directionArray = { 1, 2, 3, 4, 12, 23, 34, 41 };
		int rnd = new Random().nextInt(directionArray.length);
		return directionArray[rnd];
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return active;
	}

	@Override
	public boolean overrideLeftTimer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void finished() {
		ai.isBusy = false;
	}

}
