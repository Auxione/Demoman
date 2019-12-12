package Engine.Items;

import org.newdawn.slick.Graphics;

import Default.Constants;
import Engine.Utilities.Transform;

public class bombSizeUpgrade extends Item {
	public Transform transform;

	bombSizeUpgrade(int x, int y) {
		transform = new Transform(x, y);
	}
	
	public void render(Graphics g) {
		g.drawImage(Constants.bombSizeUpgrade, transform.get_x() * Constants.CellSize,
				transform.get_y() * Constants.CellSize);
	}
}
