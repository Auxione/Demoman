package Curio.HUD;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Functions;
import Curio.ItemMap.Inventory;
import Curio.ItemMap.ItemMap;

public class InventoryDisplay extends HUD {
	Inventory inventory;
	private ItemMap itemMap;
	static int itemIconSize = 32;

	public InventoryDisplay(float x, float y, Inventory _inventory, ItemMap itemMap) {
		super(x, y, _inventory.getInventoryMap().length * itemIconSize, itemIconSize);
		this.itemMap = itemMap;
		inventory = _inventory;
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(Position.x, Position.y);
		g.setLineWidth(2);
		// background
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);
		// lines
		g.setColor(Color.black);
		g.drawRect(0, 0, width, height);

		g.setColor(Color.black);
		for (int x = 0; x < inventory.getInventoryMap().length; x++) {
			g.drawRect(x * itemIconSize, 0, itemIconSize, itemIconSize);
			// if the array contains draw item image
			if (inventory.getInventoryMap()[x][0] > 0 && inventory.getInventoryMap()[x][1] > 0) {
				g.drawImage(itemMap.itemList.get(inventory.getInventoryMap()[x][0]).getImage(), x * itemIconSize, 0);
				g.setColor(Color.black);
				// draw item count
				g.setColor(Color.lightGray);
				g.fillRect(x * itemIconSize, -20, 20, 20);
				g.setColor(Color.black);
				g.drawRect(x * itemIconSize, -20, 20, 20);

				g.drawString(Integer.toString(inventory.getInventoryMap()[x][1]), x * itemIconSize + 2, -20);
			}
		}
		// selection index
		g.setColor(Color.red);
		g.drawRect(inventory.itemIndex * itemIconSize + 2, +2, itemIconSize - 4, itemIconSize - 4);
		g.popTransform();
	}

	@Override
	public void inputEvent(Input input) {
		if (inRange(input.getMouseX(), input.getMouseY()) == true) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				inventory.itemIndex = (int) Functions.map(input.getMouseX(), Position.x, Position.x + width, 0,
						inventory.inventorySize);
			}
		}
	}

	@Override
	public void loopStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopEnd() {
		// TODO Auto-generated method stub

	}

}
