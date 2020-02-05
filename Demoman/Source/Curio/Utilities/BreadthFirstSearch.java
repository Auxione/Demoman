package Curio.Utilities;

import java.util.LinkedList;
import java.util.Queue;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.CellularMap.CellularMap;
import Curio.Renderer.Interface.Renderer;
import Default.Constants;

public class BreadthFirstSearch implements Renderer {
	boolean[][] visited;
	int maxCellX;
	int maxCellY;

	public BreadthFirstSearch(CellularMap cellularMap, CellCoordinate startCC, int zPosOfCellMap, int wantedID) {
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
			
			if (cellularMap.getCell(x, y - 1, zPosOfCellMap) == wantedID) {
				queue.add(new CellCoordinate(x, y - 1)); // go left
			}
			if (cellularMap.getCell(x, y + 1, zPosOfCellMap) == wantedID) {
				queue.add(new CellCoordinate(x, y + 1)); // go right
			}
			if (cellularMap.getCell(x - 1, y, zPosOfCellMap) == wantedID) {
				queue.add(new CellCoordinate(x - 1, y)); // go up
			}
			if (cellularMap.getCell(x + 1, y, zPosOfCellMap) == wantedID) {
				queue.add(new CellCoordinate(x + 1, y)); // go down
			}
		}
	}

	public boolean[][] getResult() {
		return visited;
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.setLineWidth(2);
		g.setColor(Color.black);
		for (int x = 0; x < visited.length; x++) {
			for (int y = 0; y < visited[0].length; y++) {
				if (visited[x][y] == true) {
					g.drawRect(x * Constants.CellSize, y * Constants.CellSize, 32, 32);
				}
			}
		}
		g.popTransform();
	}
}