package Curio.Renderer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.Functions;
import Curio.Viewport;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.PlantManager.PlantList;
import Curio.SessionManagers.PlantManager.PlantMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class PlantMapRenderer implements Renderer {
	private PlantMap plantMap;
	private CellCoordinate renderStartCC;
	private int renderSizeX;
	private int renderSizeY;

	public PlantMapRenderer(PlantMap plantMap) {
		this.plantMap = plantMap;
		this.renderSizeX = (int) Math.floor(Viewport.screenSizeX / Constants.CellSize);
		this.renderSizeY = (int) Math.floor(Viewport.screenSizeY / Constants.CellSize);
	}

	public void render(Graphics g) {
		renderStartCC = Functions.worldPostoCellPosition(Viewport.position);
		for (int x = renderStartCC.getCellX(); x < renderStartCC.getCellX() + renderSizeX + 1; x++) {
			for (int y = renderStartCC.getCellY(); y < renderStartCC.getCellY() + renderSizeY + 2; y++) {
				if (plantMap.getCell(x, y, 0) > 0 && plantMap.getCell(x, y, 1) > 0) {
					// get all images
					Image[] i = PlantList.list.get(plantMap.getCell(x, y, 0)).getImageArray();
					g.drawImage(i[plantMap.getCell(x, y, 4)], x * Constants.CellSize, y * Constants.CellSize);
				}
			}
		}
	}
}