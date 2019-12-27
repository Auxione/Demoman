package Curio.Tilemap;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Utilities.CellCoordinate;
import Curio.Utilities.FloodFill;
import Default.Constants;

public class Area {
	FloodFill scanner;
	private boolean[][] areaArray;
	private CellCoordinate position;
	private ArrayList<CellCoordinate> areaList;

	public Area(TileMap tilemap, CellCoordinate position) {
		scanner = new FloodFill(tilemap, position.getCellX(), position.getCellY());
		this.position = position;
		this.areaArray = scanner.getArray();
		this.areaList = scanner.getCellList();
	}

	public void render(Graphics g) {
		for (CellCoordinate cellCord : areaList) {
			g.pushTransform();
			g.translate(cellCord.getWorldX(), cellCord.getWorldY());
			g.setLineWidth(2);
			g.setColor(new Color(255, 0, 255));
			g.drawRect(0, 0, Constants.CellSize, Constants.CellSize);
			g.popTransform();
		}
	}
}
