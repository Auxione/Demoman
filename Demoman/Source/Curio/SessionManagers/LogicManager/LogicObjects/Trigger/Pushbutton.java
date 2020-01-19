package Curio.SessionManagers.LogicManager.LogicObjects.Trigger;

import org.newdawn.slick.Image;

import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class Pushbutton extends LogicObject implements LogicTrigger {
	public boolean activated = false;
	public Image img = Constants.pushbutton;

	public CellCoordinate outputCC;
	private boolean onTop;

	public Pushbutton(Transform transform, CellCoordinate outputCC) {
		super(null, transform);
		this.outputCC = outputCC;
	}

	@Override
	public void update(LogicMap logicMap) {
		if (activated == true) {
			
			logicMap.sendTick(outputCC, activated);
			activated = false;
		}
	}

	@Override
	public void onTopEvent(boolean onTop) {
		this.onTop = onTop;
	}

	@Override
	public void keyEvent(boolean ActionUse) {
		if (onTop == true) {
			activated = ActionUse;
		}
	}
}