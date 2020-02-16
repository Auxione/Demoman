package Curio.SessionManagers.RailSystemManager;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import Curio.Renderer.Interface.Renderer;
import Curio.Utilities.CellCoordinate;

public class RailSystemManager implements Renderer {
	private ArrayList<Station> stations;
	private ArrayList<Train> trains;
	private RailroadMap railroadMap;

	public RailSystemManager(RailroadMap railroadMap) {
		this.railroadMap = railroadMap;
		this.stations = new ArrayList<Station>();
	}

	public Station createStation(CellCoordinate cellCoordinate) {
		if (railroadMap.getCell(cellCoordinate, 0) == 0) {
			Station s = new Station().createNode(cellCoordinate);
			stations.add(s);
			railroadMap.setCell(cellCoordinate, 0, 1);
			return s;
		}
		return null;
	}

	public void removeStation(Station station) {
		if (stations.contains(station) == true) {
			stations.remove(station);
		}
	}

	public void render(Graphics g) {
		g.pushTransform();
		for (Station s : stations) {
			s.render(g);
		}
		g.popTransform();
	}
}
