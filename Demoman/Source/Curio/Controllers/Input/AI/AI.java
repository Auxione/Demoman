package Curio.Controllers.Input.AI;

import static Curio.Functions.*;

import Curio.Console;
import Curio.Controllers.ControlPackage;
import Curio.Controllers.Input.AI.Tasks.SetRotation;
import Curio.Controllers.Input.AI.Tasks.Wait;
import Curio.Controllers.Input.AI.Tasks.WanderAround;
import Curio.Physics.Time;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.SessionManagers.WorldManager.TileMap;
import Default.Player;

public class AI {
	private Console console = null;

	private ControlPackage controlPackage = new ControlPackage();

	private Player player;
	private ItemMap itemmap;
	private TileMap tilemap;

	public boolean isBusy = false;

	private WanderAround wanderAround;
	private Wait wait;
	private SetRotation setRotation;

	public AI(TileMap tilemap, ItemMap itemmap, Player player) {
		this.itemmap = itemmap;
		this.tilemap = tilemap;
		this.player = player;
		this.wanderAround = new WanderAround(this);
		this.wait = new Wait(this);
		this.setRotation = new SetRotation(this);
	}

	public AI setConsole(Console console) {
		this.console = console;
		wanderAround.setConsole(console);
		wait.setConsole(console);
		return this;
	}

	public void update(Time currentTime) {
		if (isBusy == false) {
			decideWhatToDo(currentTime);
		}

		if (isBusy == true) {
			if (wanderAround.active() == true) {
				wanderAround.update(controlPackage, currentTime);
			}

			else if (wait.active() == true) {
				wait.update(controlPackage, currentTime);
			} 
			
			else if (setRotation.active() == true) {
				setRotation.update(controlPackage, currentTime);
			}
		}
	}

	private void decideWhatToDo(Time currentTime) {
		int chance = random(0, 3);
		if (chance == 0) {
			if (wanderAround.active() == false) {
				wanderAround.Order(currentTime, random(1, 2));
				isBusy = true;
			}
		}

		else if (chance == 1) {
			if (wait.active() == false) {
				wait.Order(currentTime, random(3, 5));
				isBusy = true;
			}
		} 
		
		else if (chance == 2) {
			if (setRotation.active() == false) {
				setRotation.Order(currentTime, random(0, 359));
				isBusy = true;
			}
		}
	}

	public ControlPackage getPackage() {
		// TODO Auto-generated method stub
		return controlPackage;
	}
}
