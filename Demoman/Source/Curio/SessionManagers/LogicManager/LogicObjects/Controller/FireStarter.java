package Curio.SessionManagers.LogicManager.LogicObjects.Controller;

import org.newdawn.slick.Image;

import Curio.SessionManagers.FireManager.FireManager;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicController;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class FireStarter extends LogicObject implements LogicController {
	public FireManager fireManager;

	public Image img = Constants.firestarter;
	private boolean Activate = false;

	private CellCoordinate inputCC;

	public FireStarter(FireManager fireManager, Transform transform, CellCoordinate inputCC) {
		super(null, transform);
		this.fireManager = fireManager;
		this.inputCC = inputCC;
	}

	@Override
	public void update(LogicMap logicMap) {
		Activate = logicMap.getState(inputCC);
		if (Activate == true) {
			fireManager.create(super.cellCoordinate.getCellX(), super.cellCoordinate.getCellY());
			Activate = false;
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "FireStarter";
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return Activate;
	}

	@Override
	public String getCustomInfo() {
		// TODO Auto-generated method stub
		return null;
	}
}
