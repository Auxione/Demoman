package Curio.Controllers.AI.Tasks;

import Curio.Console;
import Curio.Functions;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.AI.AI;
import Curio.Controllers.AI.Task;
import Curio.Physics.Time;

public class SetRotation implements Task {
	private boolean active = false;
	private AI ai;
	private Console console;
	private int newAngle;

	public SetRotation(AI ai) {
		this.ai = ai;
	}

	public SetRotation setConsole(Console console) {
		this.console = console;
		return this;
	}

	@Override
	public void Order(Time currentTime, int seconds) {
		this.active = true;
		this.newAngle = seconds;

		if (console != null) {
			console.Add(0, "Turning to: " + newAngle + " degrees.");
		}
	}

	@Override
	public void update(ControlPackage controlPackage, Time currentTime) {
		controlPackage.rotation.setAngle(this.newAngle);
		active = false;
		finished();
	}

	@Override
	public void finished() {
		ai.isBusy = false;
	}

	@Override
	public boolean overrideLeftTimer() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean active() {
		// TODO Auto-generated method stub
		return active;
	}

}
