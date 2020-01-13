package Curio.Renderer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.Viewport;
import Curio.PlantSystem.PlantList;
import Curio.PlantSystem.PlantMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class PlantMapRenderer implements Renderer {
	private PlantMap plantMap;
	private Viewport viewPort;
	private CellCoordinate renderStartCC;
	private int renderSizeX;
	private int renderSizeY;

	public PlantMapRenderer(PlantMap plantMap, Viewport viewPort) {
		this.plantMap = plantMap;
		this.viewPort = viewPort;
		this.renderSizeX = (int) Math.floor(viewPort.screenSizeX / Constants.CellSize);
		this.renderSizeY = (int) Math.floor(viewPort.screenSizeY / Constants.CellSize);
	}

	public void render(Graphics g) {
		renderStartCC = plantMap.worldPostoCellPosition(viewPort.transform);
		for (int x = renderStartCC.getCellX(); x < renderStartCC.getCellX() + renderSizeX + 1; x++) {
			for (int y = renderStartCC.getCellY(); y < renderStartCC.getCellY() + renderSizeY + 2; y++) {
				if (plantMap.getTile(x, y, 0) > 0 && plantMap.getTile(x, y, 1) > 0) {
					// get all images
					Image[] i = PlantList.list.get(plantMap.getTile(x, y, 0)).getImageArray();
					g.drawImage(i[plantMap.getTile(x, y, 4)], x * Constants.CellSize, y * Constants.CellSize);
				}
			}
		}
	}
}