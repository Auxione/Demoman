package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Renderer.Interface.AlphaMaskRenderer;
import Curio.Renderer.Interface.AnimationRenderer;
import Curio.Renderer.Interface.Renderer;
import Curio.SessionManagers.LogicManager.Interfaces.LogicController;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.FireStarter;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.ItemSpawner;
import Curio.SessionManagers.LogicManager.LogicObjects.Controller.LightBulb;
import Curio.SessionManagers.LogicManager.LogicObjects.Processor.Delay;
import Curio.SessionManagers.LogicManager.LogicObjects.Processor.PushToSwitch;
import Curio.SessionManagers.LogicManager.LogicObjects.Trigger.MoveTrigger;
import Curio.SessionManagers.LogicManager.LogicObjects.Trigger.Pushbutton;
import Curio.SessionManagers.LogicManager.LogicObjects.Trigger.Switchbutton;
import Default.Constants;

public class LogicObjectRenderer implements Renderer, AlphaMaskRenderer, AnimationRenderer {
	private LogicObject logicObject;

	public LogicObjectRenderer(LogicObject logicObject) {
		this.logicObject = logicObject;
	}

	@Override
	public void renderAnimation(Graphics g) {
		g.pushTransform();
		g.translate(logicObject.transform.position.x, logicObject.transform.position.y);
		g.rotate(0, 0, logicObject.transform.rotation.degrees());
		
		g.popTransform();
	}

	@Override
	public void renderAlphaMask(Graphics g) {
		g.pushTransform();
		g.translate(logicObject.transform.position.x, logicObject.transform.position.y);
		g.rotate(0, 0, logicObject.transform.rotation.degrees());
		if (logicObject instanceof LightBulb) {
			LightBulbAlphaMaskRender((LightBulb) logicObject, g);
		}
		g.popTransform();
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(logicObject.transform.position.x, logicObject.transform.position.y);
		g.rotate(0, 0, logicObject.transform.rotation.degrees());
		if (logicObject instanceof Switchbutton) {
			switchButtonRender((Switchbutton) logicObject, g);
		}

		else if (logicObject instanceof Pushbutton) {
			pushButtonRender((Pushbutton) logicObject, g);
		}

		else if (logicObject instanceof MoveTrigger) {
			moveTriggerRender((MoveTrigger) logicObject, g);
		}

		else if (logicObject instanceof PushToSwitch) {
			PushToSwitchRender((PushToSwitch) logicObject, g);
		}

		else if (logicObject instanceof Delay) {
			delayRender((Delay) logicObject, g);
		}

		else if (logicObject instanceof FireStarter) {
			FireStarterRender((FireStarter) logicObject, g);
		}

		else if (logicObject instanceof ItemSpawner) {
			ItemSpawnerRender((ItemSpawner) logicObject, g);
		} 
		
		else if (logicObject instanceof LightBulb) {
			LightBulbRender((LightBulb) logicObject, g);
		}
		g.popTransform();
	}
	void switchButtonRender(Switchbutton object, Graphics g) {
		if (object.state == true) {
			g.drawImage(object.switchon, 0, 0);
		} else if (object.state == false) {
			g.drawImage(object.switchoff, 0, 0);
		}
	}

	void pushButtonRender(Pushbutton object, Graphics g) {
		if (object.activated == true) {
			g.setColor(Color.green);
		} else if (object.activated == false) {
			g.setColor(Color.red);
		}
		g.fillRect(7, 7, Constants.CellSize - 14, Constants.CellSize - 14);
		g.drawImage(object.img, 0, 0);
	}

	void moveTriggerRender(MoveTrigger object, Graphics g) {
		g.drawImage(object.img, 0, 0);
	}

	void PushToSwitchRender(PushToSwitch object, Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(0, 0, Constants.CellSize, Constants.CellSize);
	}

	void delayRender(Delay object, Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, Constants.CellSize, Constants.CellSize);
	}

	void FireStarterRender(FireStarter object, Graphics g) {
		g.drawImage(object.img, 0, 0);
	}

	void ItemSpawnerRender(ItemSpawner object, Graphics g) {
		g.drawImage(object.img, 0, 0);
	}

	void LightBulbRender(LightBulb object, Graphics g) {
		g.setColor(object.lightColor);
		g.fillRect(8, 8, 17, 17);
		g.drawImage(object.image, 0, 0);
	}

	void LightBulbAlphaMaskRender(LightBulb object, Graphics g) {
		if (object.state == true) {
			object.alphaMaskImage.setImageColor(object.lightColor.r,object.lightColor.g,object.lightColor.b,object.intensity);
			object.alphaMaskImage.drawCentered(Constants.CellSize/2,Constants.CellSize/2);
		}
	}
}
