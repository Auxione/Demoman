package Curio.Utilities.Navigation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.CellularMap.CellularMap;
import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.CellCoordinate;

public class AStarPathFinder implements Renderer {
	// boolean[][] visited;
	CellCoordinate[][] camefrom;

	private CellCoordinate startCell, targetCell;

	public AStarPathFinder(CellularMap cellularMap, CellCoordinate startCC, CellCoordinate targetCC) {
		this.camefrom = new CellCoordinate[cellularMap.getXAxisMaxCell()][cellularMap.getYAxisMaxCell()];
		Queue<CellCoordinate> queue = new LinkedList<>();
		this.startCell = startCC;
		this.targetCell = targetCC;
		CellCoordinate lastCC = startCC;

		queue.add(startCC);
		while (queue.isEmpty() == false) {
			CellCoordinate currentCC = queue.poll();
			int x = currentCC.getCellX();
			int y = currentCC.getCellY();
			
			camefrom[x][y] = lastCC;
			lastCC = currentCC;
			
			if (x < 0 || x >= cellularMap.getXAxisMaxCell()) {
				continue;
			}
			if (y < 0 || y >= cellularMap.getYAxisMaxCell()) {
				continue;
			}
			if (currentCC.getCellX() == targetCC.getCellX()) {
				if (currentCC.getCellY() == targetCC.getCellY()) {
					break;
				}
			}
			queue.add(new CellCoordinate(x, y - 1));
			queue.add(new CellCoordinate(x, y + 1));
			queue.add(new CellCoordinate(x - 1, y));
			queue.add(new CellCoordinate(x + 1, y));
		}
	}

	private int heuristic(CellCoordinate point1, CellCoordinate point2) {
		return Math.abs(point1.getCellX() - point2.getCellX()) + Math.abs(point1.getCellY() - point2.getCellY());
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		// draw start and target cell
		g.setColor(Color.red);
		g.fillRect(startCell.getWorldX(), startCell.getWorldY(), 32, 32);

		g.setColor(Color.green);
		g.fillRect(targetCell.getWorldX(), targetCell.getWorldY(), 32, 32);
//draw directions
		g.setLineWidth(2);
		g.setColor(Color.black);
		for (int x = 0; x < camefrom.length; x++) {
			for (int y = 0; y < camefrom[0].length; y++) {

			}
		}
		g.popTransform();
	}
}