package Curio.LogicSystem.Trigger;

import org.newdawn.slick.Graphics;

import Curio.LogicSystem.Logic;
import Curio.LogicSystem.LogicMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class Pushbutton implements Logic {

	public CellCoordinate objectCellPosition;
	private CellCoordinate outputCellPosition;

	private boolean activated = false;

	public Pushbutton(int x, int y, CellCoordinate outputCellPosition) {
		this.objectCellPosition = new CellCoordinate(x, y);
		this.outputCellPosition = outputCellPosition;
	}

	@Override
	public void keyEvent() {
		activated = true;
	}

	@Override
	public void update(LogicMap logicMap) {
		if (activated == true) {
			logicMap.sendTick(outputCellPosition, activated);
			activated = false;
		}
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(objectCellPosition.getWorldX(), objectCellPosition.getWorldY());
		g.drawImage(Constants.pushbutton, 0, 0);
		g.popTransform();
	}

}