package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.FluidMap;
import Curio.Functions;
import Default.Constants;

public class FluidMapRenderer implements Renderer {
	private FluidMap fluidMap;

	public FluidMapRenderer(FluidMap fluidMap) {
		this.fluidMap = fluidMap;
	}

	public void render(Graphics g) {
		g.pushTransform();
		for (int x = 0; x < fluidMap.getXAxisMaxCell(); x++) {
			for (int y = 0; y < fluidMap.getYAxisMaxCell(); y++) {
				g.pushTransform();
				g.translate(x * Constants.CellSize, y * Constants.CellSize);

				float alphaVal = Functions.map(fluidMap.getTile(x, y, 0), fluidMap.minCellVolume,
						fluidMap.maxCellVolume, 0, 180);

				g.setColor(new Color(0, 0, 255, alphaVal));
				g.fillRect(0, 0, Constants.CellSize, Constants.CellSize);

				g.popTransform();
			}
		}
		g.popTransform();
	}
}
