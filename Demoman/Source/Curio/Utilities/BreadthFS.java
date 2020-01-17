package Curio.Utilities;

import java.util.LinkedList;
import java.util.Queue;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.CellularMap.CellularMap;
import Curio.Renderer.Renderer;
import Default.Constants;

public class BreadthFS implements Renderer {
	boolean[][] visited;
	int maxCellX;
	int maxCellY;

	public BreadthFS(CellularMap cellularMap, CellCoordinate startCC, int wantedID, int notWantedID) {
		int maxCellX = cellularMap.getXAxisMaxCell();
		int maxCellY = cellularMap.getYAxisMaxCell();

		this.visited = new boolean[maxCellX][maxCellY];
		Queue<CellCoordinate> queue = new LinkedList<>();

		queue.add(startCC);
		while (queue.isEmpty() == false) {
			CellCoordinate currentCC = queue.poll();
			int x = currentCC.getCellX();
			int y = currentCC.getCellY();

			if (x < 0 || x >= maxCellX) {
				continue;
			}
			if (y < 0 || y >= maxCellY) {
				continue;
			}
			if (visited[x][y] == true) {
				continue;
			}

			visited[x][y] = true;
			queue.add(new CellCoordinate(x, y - 1)); // go left
			queue.add(new CellCoordinate(x, y + 1)); // go right
			queue.add(new CellCoordinate(x - 1, y)); // go up
			queue.add(new CellCoordinate(x + 1, y)); // go down
		}
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.setLineWidth(2);
		g.setColor(Color.black);
		for (int x = 0; x < maxCellX; x++) {
			for (int y = 0; y < maxCellY; y++) {
				g.drawRect(x * Constants.CellSize, y * Constants.CellSize, 16, 16);
			}
		}
		g.popTransform();
	}

	public boolean[][] getResult() {
		return visited;
	}
}