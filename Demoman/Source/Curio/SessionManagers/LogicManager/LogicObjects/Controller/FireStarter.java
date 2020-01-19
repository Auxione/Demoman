package Curio.SessionManagers.LogicManager.LogicObjects.Controller;

import org.newdawn.slick.Image;

import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicController;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class FireStarter extends LogicObject implements LogicController {
	public FireManager fireManager;
	
	public Image img = Constants.firestarter;
	private boolean Activate = false;

	public FireStarter(FireManager fireManager, Transform transform) {
		super(null, transform);
		this.fireManager = fireManager;
	}

	@Override
	public void update(LogicMap logicMap) {
		Activate = logicMap.getState(super.cellCoordinate);
		if (Activate == true) {
			fireManager.create(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY());
			Activate = false;
		}
	}
}
