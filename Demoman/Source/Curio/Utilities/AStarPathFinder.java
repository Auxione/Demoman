package Curio.Utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import Curio.CellularMap.CellularMap;

public class AStarPathFinder {
	// A* pathfinding algorithm
	private CellCoordinate start;
	private CellCoordinate end;
	private Queue<CellCoordinate> frontier = new LinkedList<>();
	private boolean[][] visited;
	private CellularMap cellularMap;

	public AStarPathFinder(CellularMap cellularMap, CellCoordinate start, CellCoordinate end) {
		this.cellularMap = cellularMap;
		this.visited = new boolean[cellularMap.getXAxisMaxCell()][cellularMap.getYAxisMaxCell()];
		this.frontier.add(start);
		this.visited[start.getCellX()][start.getCellY()] = true;

		while (frontier.isEmpty() == false) {
			CellCoordinate currentCC = frontier.poll();
			Iterator<CellCoordinate> i = frontier.iterator();
			
			while (i.hasNext()) {
				CellCoordinate nextCC = i.next();
				if (visited[nextCC.getCellX()][nextCC.getCellY()] == false) {
					visited[nextCC.getCellX()][nextCC.getCellY()] = true;
					frontier.add(nextCC);
				}
			}
		}
	}
}
