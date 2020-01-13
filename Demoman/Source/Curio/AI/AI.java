package Curio.AI;

import java.util.Random;

import Curio.Console;
import Curio.Functions;
import Curio.TileMap;
import Curio.AI.Tasks.Wait;
import Curio.AI.Tasks.WanderAround;
import Curio.GameObject.Controllers.ControlPackage;
import Curio.ItemSystem.ItemMap;
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

	public AI(TileMap tilemap, ItemMap itemmap, Player player) {
		this.itemmap = itemmap;
		this.tilemap = tilemap;
		this.player = player;
		this.wanderAround = new WanderAround(this);
		this.wait = new Wait(this);
	}

	public AI setConsole(Console console) {
		this.console = console;
		// wanderAround.setConsole(console);
		// wait.setConsole(console);
		return this;
	}

	public void update() {
		int currentTime = Functions.millis();
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
		}
	}

	private void decideWhatToDo(int currentTime) {
		int chance = getRandomInt(0, 2);
		if (chance == 0) {
			if (wanderAround.active() == false) {
				wanderAround.Order(currentTime, getRandomInt(200, 600));
				isBusy = true;
			}
		} else if (chance == 1) {
			if (wait.active() == false) {
				wait.Order(currentTime, getRandomInt(1000, 3000));
				isBusy = true;
			}
		}
	}

	private int getRandomInt(int min, int max) {
		int rnd = 0;
		if (min < max) {
			rnd = new Random().nextInt(max - min) + min;
		}
		return rnd;
	}

	public ControlPackage getPackage() {
		// TODO Auto-generated method stub
		return controlPackage;
	}
}
