package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Functions;
import Curio.Viewport;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.ItemManager.ItemList;
import Curio.SessionManagers.ItemManager.ItemMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class ItemMapRenderer implements Renderer {
	private ItemMap itemMap;
	private CellCoordinate renderStartCC;
	private int renderSizeX;
	private int renderSizeY;
	final private int xOffset = 1;
	final private int yOffset = 2;

	public ItemMapRenderer(ItemMap itemMap) {
		this.itemMap = itemMap;
		this.renderSizeX = (int) Math.floor(Viewport.screenSizeX / Constants.CellSize);
		this.renderSizeY = (int) Math.floor(Viewport.screenSizeY / Constants.CellSize);
	}

	public void render(Graphics g) {
		renderStartCC = Functions.worldPostoCellPosition(Viewport.position);
		g.pushTransform();
		for (int x = renderStartCC.getCellX(); x < renderStartCC.getCellX() + renderSizeX + xOffset; x++) {
			for (int y = renderStartCC.getCellY(); y < renderStartCC.getCellY() + renderSizeY + yOffset; y++) {
				if (itemMap.getCell(x, y, 0) > 0 && itemMap.getCell(x, y, 1) > 0) {
					g.drawImage(ItemList.list.get(itemMap.getCell(x, y, 0)).getImage(), x * Constants.CellSize,
							y * Constants.CellSize);
					g.setColor(Color.black);
					g.drawString(Integer.toString(itemMap.getCell(x, y, 1)), x * Constants.CellSize - 5,
							y * Constants.CellSize - 5);
				}
			}
		}
		g.popTransform();
	}
}
