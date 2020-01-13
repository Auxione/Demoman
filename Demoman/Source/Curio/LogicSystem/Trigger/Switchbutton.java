package Curio.LogicSystem.Trigger;

import org.newdawn.slick.Graphics;

import Curio.LogicSystem.Logic;
import Curio.LogicSystem.LogicMap;
import Curio.Utilities.CellCoordinate;
import Default.Constants;

public class Switchbutton implements Logic {
	final String debugcode = "<Pushbutton>:";
	final boolean debugActive = false;

	public CellCoordinate objectCellPosition;
	private CellCoordinate outputCellPosition;

	private boolean state = false;
	private boolean Activated = false;

	public Switchbutton(int x, int y, CellCoordinate outputCellPosition) {
		this.objectCellPosition = new CellCoordinate(x, y);
		this.outputCellPosition = outputCellPosition;
	}

	public void render(Graphics g) {
		g.pushTransform();
		g.translate(objectCellPosition.getWorldX(), objectCellPosition.getWorldY());
		if (state == true) {
			g.drawImage(Constants.switchon, 0, 0);
		} else if (state == false) {
			g.drawImage(Constants.switchoff, 0, 0);
		}
		g.popTransform();
	}

	public void keyEvent() {
		state = !state;
		Activated = true;
	}

	public void update(LogicMap logicMap) {
		if (Activated == true) {
			logicMap.setState(outputCellPosition, state);
			Activated = false;
		}
	}
}