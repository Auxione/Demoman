package Curio.Renderer;

import org.newdawn.slick.Graphics;

import Curio.TileMap;
import Curio.TileList;
import Curio.Viewport;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class TileMapRenderer implements Renderer {
	private TileMap tileMap;
	private Viewport viewPort;
	private CellCoordinate renderStartCC;
	private int renderSizeX;
	private int renderSizeY;
	final private int xOffset = 1;
	final private int yOffset = 2;

	public TileMapRenderer(TileMap tileMap, Viewport viewPort) {
		this.tileMap = tileMap;
		this.viewPort = viewPort;
		this.renderSizeX = (int) Math.floor(viewPort.screenSizeX / Constants.CellSize);
		this.renderSizeY = (int) Math.floor(viewPort.screenSizeY / Constants.CellSize);
	}

	public void render(Graphics g) {
		renderStartCC = tileMap.worldPostoCellPosition(viewPort.transform);
		g.pushTransform();
		for (int x = renderStartCC.getCellX(); x < renderStartCC.getCellX() + renderSizeX + xOffset; x++) {
			for (int y = renderStartCC.getCellY(); y < renderStartCC.getCellY() + renderSizeY + yOffset; y++) {
				// sum of the cells in screen and screen position on tilemap
				if (tileMap.getTile(x, y, 0) != 0) {
					g.drawImage(TileList.getTile(tileMap.getTile(x, y, 0)).getTexture(), x * Constants.CellSize,
							y * Constants.CellSize);
				}
			}
		}
		g.popTransform();
	}
}
