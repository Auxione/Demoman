package Curio.Renderer;


import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.Functions;
import Curio.Viewport;
import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.WorldManager.TileList;
import Curio.SessionManagers.WorldManager.TileMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class TileMapRenderer implements Renderer, AlphaMaskRenderer {
	private Image shadowMask = Constants.WallShadowMask;

	private TileMap tileMap;
	private CellCoordinate renderStartCC;
	private int renderSizeX;
	private int renderSizeY;
	final private int xOffset = 1;
	final private int yOffset = 2;

	public TileMapRenderer(TileMap tileMap) {
		this.tileMap = tileMap;
		this.renderSizeX = (int) Math.floor(Viewport.screenSizeX / Constants.CellSize);
		this.renderSizeY = (int) Math.floor(Viewport.screenSizeY / Constants.CellSize);
	}

	public void render(Graphics g) {
		renderStartCC = Functions.worldPostoCellPosition(Viewport.position);
		renderTiles(g);
	}

	public void renderTiles(Graphics g) {
		g.pushTransform();
		for (int x = renderStartCC.getCellX(); x < renderStartCC.getCellX() + renderSizeX + xOffset; x++) {
			for (int y = renderStartCC.getCellY(); y < renderStartCC.getCellY() + renderSizeY + yOffset; y++) {
				// sum of the cells in screen and screen position on tilemap
				if (tileMap.getCell(x, y, 0) != 0) {
					g.drawImage(TileList.getTile(tileMap.getCell(x, y, 0)).getTexture(), x * Constants.CellSize,
							y * Constants.CellSize);

				}
			}
		}
		g.popTransform();
	}

	@Override
	public void renderAlphaMask(Graphics g) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE); // Blends the alpha map.
		for (int x = renderStartCC.getCellX(); x < renderStartCC.getCellX() + renderSizeX + xOffset; x++) {
			for (int y = renderStartCC.getCellY(); y < renderStartCC.getCellY() + renderSizeY + yOffset; y++) {
				if (TileList.getTile(tileMap.getCell(x, y, 0)).isSolid() == true) {
					// check north if theres a non solid tile
					if (TileList.getTile(tileMap.getCell(x, y - 1, 0)).isSolid() == false) {
						g.pushTransform();
						g.translate((x + 0.5f) * Constants.CellSize, (y + 0.5f) * Constants.CellSize);
						g.rotate(0, 0, 0);
						shadowMask.drawCentered(0, -1.0f * Constants.CellSize);
						g.popTransform();
					}
					// check east if theres a non solid tile
					if (TileList.getTile(tileMap.getCell(x + 1, y, 0)).isSolid() == false) {
						g.pushTransform();
						g.translate((x + 0.5f) * Constants.CellSize, (y + 0.5f) * Constants.CellSize);
						g.rotate(0, 0, 90);
						shadowMask.drawCentered(0, -1.0f * Constants.CellSize);
						g.popTransform();
					}
					// check south if theres a non solid tile
					if (TileList.getTile(tileMap.getCell(x, y + 1, 0)).isSolid() == false) {
						g.pushTransform();
						g.translate((x + 0.5f) * Constants.CellSize, (y + 0.5f) * Constants.CellSize);
						g.rotate(0, 0, 180);
						shadowMask.drawCentered(0, -1.0f * Constants.CellSize);
						g.popTransform();
					}
					// check west if theres a non solid tile
					if (TileList.getTile(tileMap.getCell(x - 1, y, 0)).isSolid() == false) {
						g.pushTransform();
						g.translate((x + 0.5f) * Constants.CellSize, (y + 0.5f) * Constants.CellSize);
						g.rotate(0, 0, 270);
						shadowMask.drawCentered(0, -1.0f * Constants.CellSize);
						g.popTransform();
					}
				}
			}
		}
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_DST_ALPHA); // Resets the blending factor.
		GL11.glDisable(GL11.GL_BLEND);
		g.setDrawMode(Graphics.MODE_NORMAL); // Resets the Graphics draw mode.
		g.clearWorldClip();
	}
}
