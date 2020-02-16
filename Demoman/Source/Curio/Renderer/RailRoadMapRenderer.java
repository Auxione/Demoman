package Curio.Renderer;

import org.newdawn.slick.Graphics;

import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.RailSystemManager.RailroadMap;
import Default.Constants;

public class RailRoadMapRenderer implements Renderer {
	private RailroadMap railRoadMap;

	public RailRoadMapRenderer(RailroadMap railRoadMap) {
		this.railRoadMap = railRoadMap;
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		for (int x = 0; x < railRoadMap.getXAxisMaxCell(); x++) {
			for (int y = 0; y < railRoadMap.getYAxisMaxCell(); y++) {
				g.translate(x * Constants.CellSize, y * Constants.CellSize);
				
				if (railRoadMap.getCell(x, y, 0) == 1) {
					g.drawImage(Constants.horizontalTracks, 0, 0);
				}

				else if (railRoadMap.getCell(x, y, 0) == 2) {
					g.drawImage(Constants.verticalTracks, 0, 0);
				}
			}
		}
		g.popTransform();
	}
}
