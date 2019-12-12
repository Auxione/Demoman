package Engine.Items;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Utilities.Transform;

public class Stimpack extends Item{
	public Transform transform;
	private int hpvalue = 10;

	Stimpack(int x, int y) {
		transform = new Transform(x, y);
	}

	public void render(Graphics g) {
		g.drawImage(Constants.medpack, transform.get_x() * Constants.CellSize, transform.get_y() * Constants.CellSize);
	}

	public int get_hp() {
		return  hpvalue;
	}
}
