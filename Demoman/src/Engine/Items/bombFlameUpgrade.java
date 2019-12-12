package Engine.Items;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Utilities.Transform;

public class bombFlameUpgrade extends Item {
	public Transform transform;

	bombFlameUpgrade(int x, int y) {
		transform = new Transform(x, y);
	}

	public void render(Graphics g) {
		g.drawImage(Constants.bombFlameUpgrade, transform.get_x() * Constants.CellSize,
				transform.get_y() * Constants.CellSize);
	}

}
