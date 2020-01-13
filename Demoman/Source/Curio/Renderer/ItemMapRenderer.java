package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Viewport;
import Curio.ItemSystem.ItemList;
import Curio.ItemSystem.ItemMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class ItemMapRenderer implements Renderer {
	private ItemMap itemMap;
	private Viewport viewPort;
	private CellCoordinate renderStartCC;
	private int renderSizeX;
	private int renderSizeY;
	final private int xOffset = 1;
	final private int yOffset = 2;

	public ItemMapRenderer(ItemMap itemMap, Viewport viewPort) {
		this.itemMap = itemMap;
		this.viewPort = viewPort;
		this.renderSizeX = (int) Math.floor(viewPort.screenSizeX / Constants.CellSize);
		this.renderSizeY = (int) Math.floor(viewPort.screenSizeY / Constants.CellSize);
	}

	public void render(Graphics g) {
		renderStartCC = itemMap.worldPostoCellPosition(viewPort.transform);
		g.pushTransform();
		for (int x = renderStartCC.getCellX(); x < renderStartCC.getCellX() + renderSizeX + xOffset; x++) {
			for (int y = renderStartCC.getCellY(); y < renderStartCC.getCellY() + renderSizeY + yOffset; y++) {
				if (itemMap.getTile(x, y, 0) > 0 && itemMap.getTile(x, y, 1) > 0) {
					g.drawImage(ItemList.list.get(itemMap.getTile(x, y, 0)).getImage(), x * Constants.CellSize,
							y * Constants.CellSize);
					g.setColor(Color.black);
					g.drawString(Integer.toString(itemMap.getTile(x, y, 1)), x * Constants.CellSize - 5,
							y * Constants.CellSize - 5);
				}
			}
		}
		g.popTransform();
	}
}
