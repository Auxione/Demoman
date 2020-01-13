package Curio.Utilities;

import java.util.ArrayList;

public class PathFinder {
	// A* pathfinding algorithm
	private CellCoordinate start;
	private CellCoordinate end;
	private ArrayList<CellCoordinate> openSet = new ArrayList<CellCoordinate>();
	private ArrayList<CellCoordinate> closedSet = new ArrayList<CellCoordinate>();

	public PathFinder(CellCoordinate start, CellCoordinate end) {
		this.start = start;
		this.end = end;

		openSet.add(start);
	}

	void update() {
		if(openSet.isEmpty() == false) {
			//go on
		}else if(openSet.isEmpty() == true) {
			//no solution
		}
		while () {

		}
	}
}
