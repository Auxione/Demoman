package Curio.SessionManagers.LogicManager.LogicObjects.Controller;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicController;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Constants;

public class LightBulb extends LogicObject implements LogicController {
	private String objectName = "DynamicWall";
	
	public Image image = Constants.lightBulb;
	public Image alphaMaskImage = Constants.CircularAlphaMask;
	public Boolean state = false;
	private CellCoordinate inputCC;
	public Color lightColor;
	public float intensity = 0.4f;

	public LightBulb(Transform transform, CellCoordinate inputCC, Color lightColor) {
		super(null, transform);
		this.inputCC = inputCC;
		this.lightColor = lightColor;
		this.alphaMaskImage.setImageColor(lightColor.r,lightColor.g,lightColor.b,intensity);
		this.alphaMaskImage = alphaMaskImage.getScaledCopy(196, 196);
	}

	@Override
	public void update(LogicMap logicMap) {
		this.state = logicMap.getState(inputCC);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return objectName;
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return state;
	}

	@Override
	public String getCustomInfo() {
		// TODO Auto-generated method stub
		return "Color Values: "+lightColor.r +":"+lightColor.g +":"+lightColor.b;
	}

}
