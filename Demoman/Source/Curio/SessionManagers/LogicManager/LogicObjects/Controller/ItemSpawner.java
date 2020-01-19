package Curio.SessionManagers.LogicManager.LogicObjects.Controller;

import org.newdawn.slick.Image;

import Curio.SessionManagers.ItemManager.Item;
import Curio.SessionManagers.ItemManager.ItemList;
import Curio.SessionManagers.ItemManager.ItemManager;
import Curio.SessionManagers.LogicManager.LogicMap;
import Curio.SessionManagers.LogicManager.Interfaces.LogicController;
import Curio.SessionManagers.LogicManager.LogicObjects.LogicObject;
import Curio.Utilities.Math.Transform;
import Default.Constants;

//test edilmedi
public class ItemSpawner extends LogicObject implements LogicController {
	final String debugcode = "<ItemSpawner>: ";
	final boolean debugActive = true;

	public Image img = Constants.itemspawner;
	private boolean activated = false;
	private Item item;

	private ItemManager itemManager;
	private boolean spawnItem = false;
	private boolean triggerOnce = true;

	public ItemSpawner(ItemManager itemManager, Transform transform, int itemID) {
		super(Constants.itemspawner, transform);
		this.itemManager = itemManager;
		this.item = ItemList.list.get(itemID);
	}

	@Override
	public void update(LogicMap logicMap) {
		boolean readState = logicMap.getState(super.cellCoordinate);

		if (readState == true && triggerOnce == false) {
			this.activated = true;
			this.triggerOnce = true;
		}

		else if (readState == false) {
			this.triggerOnce = false;
		}

		if (activated == true) {
			itemManager.put(super.cellCoordinate, item);
			this.activated = false;
		}
	}
}