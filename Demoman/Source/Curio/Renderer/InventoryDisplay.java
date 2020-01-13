package Curio.Renderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import Curio.Functions;
import Curio.ItemSystem.Inventory;
import Curio.ItemSystem.ItemList;
import Curio.ItemSystem.ItemMap;
import Curio.Utilities.Math.Transform;

public class InventoryDisplay extends HUD implements HUDInterface, Renderer {
	Inventory inventory;
	private ItemMap itemMap;
	static int itemIconSize = 32;
	private int itemCounterBoxSize = 20;

	private TrueTypeFont trueTypeFont = super.getTTF();

	public InventoryDisplay(Transform transform, Inventory inventory, ItemMap itemMap) {
		super(transform, inventory.getMap().length * itemIconSize, itemIconSize);
		this.itemMap = itemMap;
		this.inventory = inventory;
	}

	@Override
	public void render(Graphics g) {
		g.pushTransform();
		g.translate(transform.position.x, transform.position.y);
		g.setLineWidth(2);
		// background
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);
		// lines
		g.setColor(Color.black);
		g.drawRect(0, 0, width, height);

		g.setColor(Color.black);
		for (int x = 0; x < inventory.getMap().length; x++) {
			g.drawRect(x * itemIconSize, 0, itemIconSize, itemIconSize);
			// if the array contains draw item image
			if (inventory.getMap()[x][0] > 0 && inventory.getMap()[x][1] > 0) {
				g.drawImage(ItemList.list.get(inventory.getMap()[x][0]).getImage(), x * itemIconSize, 0);
				g.setColor(Color.black);
				// draw item count
				g.setColor(Color.lightGray);
				g.fillRect(x * itemIconSize, -itemCounterBoxSize, itemCounterBoxSize, itemCounterBoxSize);
				g.setColor(Color.black);
				g.drawRect(x * itemIconSize, -itemCounterBoxSize, itemCounterBoxSize, itemCounterBoxSize);

				int stringLineWidth = trueTypeFont.getWidth(Integer.toString(inventory.getMap()[x][1]));
				int stringLineHeight = trueTypeFont.getHeight(Integer.toString(inventory.getMap()[x][1]));
				float Stringx = x * itemIconSize + itemCounterBoxSize / 2 - stringLineWidth / 2;
				float Stringy = -itemCounterBoxSize - itemCounterBoxSize / 2 + stringLineHeight / 2;

				trueTypeFont.drawString(Stringx, Stringy, Integer.toString(inventory.getMap()[x][1]), Color.black);
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
				inventory.itemIndex = (int) Functions.map(input.getMouseX(), transform.position.x,
						transform.position.x + width, 0, inventory.inventoryCellCount);
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

	@Override
	public void keyPressed(int key, char chr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int key, char chr) {
		// TODO Auto-generated method stub

	}

}
