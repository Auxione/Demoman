package Default;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Engine.Tilemap.Tilemap;
import Engine.Utilities.Transform;

public class CellPlayer {
	Transform transform;
	public int bomb_size = 2, bomb_timer = 1500;

	private int Health;
	private int MaxHealth = 100;
	public int Team = 1;// spectator, dead = 0 //blue = 1 // green = 2
	private Tilemap currentLevel;

	// current level,team, active or passive player, health
	public CellPlayer(Tilemap a, int h) {
		transform = new Transform();
		currentLevel = a;
		Health = h;
	}

//spawn in x,y coord in lvl
	public void spawn(int x, int y) {
		// check if the coords is in between 0 and lvl size.
		if (x < 0) {
			transform.set_x(0);
		} else if (x > currentLevel.get_MaxCellX()) {
			transform.set_x(currentLevel.get_MaxCellX() - 1);
		} else {
			transform.set_x(x);
		}
		if (y < 0) {
			transform.set_y(0);
		} else if (y > currentLevel.get_MaxCellY()) {
			transform.set_y(currentLevel.get_MaxCellY() - 1);
		} else {
			transform.set_y(y);
		}
	}

	int psize = 20;

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(transform.get_x() * Constants.CellSize + psize / 2,
				transform.get_y() * Constants.CellSize + psize / 2, Constants.CellSize - psize,
				Constants.CellSize - psize);
	}

	public void moveNorth() {
		// check surrounding tiles for occupation
		// Tset.getMoveable() get boolean value from id
		// currentLevel..get_Tile(x,y) to get id of that position
		// transform.get_x() , transform.get_y()-1 position of the player. -1 on y axis
		// means one
		// tile up
		// transform.get_x() is x axis position, transform.get_y() is y axis position.
		if (Constants.Tset.getMoveable(currentLevel.get_Tile(transform.get_x(), transform.get_y() - 1))) {
			transform.set_y(transform.get_y() - 1);
		}
	}

	// same applies
	public void moveSouth() {
		if (Constants.Tset.getMoveable(currentLevel.get_Tile(transform.get_x(), transform.get_y() + 1))) {
			transform.set_y(transform.get_y() + 1);
		}
	}

	public void moveEast() {
		if (Constants.Tset.getMoveable(currentLevel.get_Tile(transform.get_x() + 1, transform.get_y()))) {
			transform.set_x(transform.get_x() + 1);
		}
	}

	public void moveWest() {
		if (Constants.Tset.getMoveable(currentLevel.get_Tile(transform.get_x() - 1, transform.get_y()))) {
			transform.set_x(transform.get_x() - 1);
		}
	}

	// override values
	public void set_Team(int t) {
		Team = t;
	}

	public void set_Health(int h) {
		Health = h;
	}

	// get the player values
	public int getTeam() {
		return Team;
	}

	public int getHealth() {
		return Health;
	}

	public int getMaxHealth() {
		return MaxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		MaxHealth = maxHealth;
	}
}