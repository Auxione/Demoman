package Curio.LogicSystem.Controller;

import org.newdawn.slick.Graphics;

import Curio.FireManager.FireManager;
import Curio.LogicSystem.Logic;
import Curio.LogicSystem.LogicMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class FireStarter implements Logic {
	public CellCoordinate objectCellPosition;
	public FireManager fm;
	private boolean Activate = false;

	public FireStarter(FireManager fm, int x, int y) {
		this.fm = fm;
		this.objectCellPosition = new CellCoordinate(x, y);

	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(objectCellPosition.getWorldX(), objectCellPosition.getWorldY());
		g.drawImage(Constants.firestarter, 0, 0);
		g.popTransform();
	}

	@Override
	public void keyEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LogicMap logicMap) {
		Activate = logicMap.getState(objectCellPosition);
		if (Activate == true) {
			fm.create(objectCellPosition.getCellX(), objectCellPosition.getCellY());
			Activate = false;
		}
	}

}
