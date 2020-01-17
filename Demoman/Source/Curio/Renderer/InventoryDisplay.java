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
	private Inventory inventory;
	static int itemIconSize = 32;
	private int itemCounterBoxSize = 20;

	private TrueTypeFont trueTypeFont = super.getTTF();

	public InventoryDisplay(Transform transform, Inventory inventory, ItemMap itemMap) {
		super(transform, inventory.getXAxisMaxCell() * itemIconSize, inventory.getYAxisMaxCell() * itemIconSize);
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
		for (int x = 0; x < inventory.getXAxisMaxCell(); x++) {
			for (int y = 0; y < inventory.getYAxisMaxCell(); y++) {
				g.drawRect(x * itemIconSize, y * itemIconSize, itemIconSize, itemIconSize);
				// if the array contains draw item image
				if (inventory.getTile(x, y, 0) > 0 && inventory.getTile(x, y, 1) > 0) {
					g.drawImage(ItemList.list.get(inventory.getTile(x, y, 0)).getImage(), x * itemIconSize,
							y * itemIconSize);
					g.setColor(Color.black);
					// draw item count
					g.setColor(Color.lightGray);
					g.fillRect(x * itemIconSize, -itemCounterBoxSize, itemCounterBoxSize, itemCounterBoxSize);
					g.setColor(Color.black);
					g.drawRect(x * itemIconSize, -itemCounterBoxSize, itemCounterBoxSize, itemCounterBoxSize);

					int stringLineWidth = trueTypeFont.getWidth(Integer.toString(inventory.getTile(x, y, 1)));
					int stringLineHeight = trueTypeFont.getHeight(Integer.toString(inventory.getTile(x, y, 1)));
					float Stringx = x * itemIconSize + itemCounterBoxSize / 2 - stringLineWidth / 2;
					float Stringy = y * itemIconSize -itemCounterBoxSize - itemCounterBoxSize / 2 + stringLineHeight / 2;

					trueTypeFont.drawString(Stringx, Stringy, Integer.toString(inventory.getTile(x, y, 1)),
							Color.black);
				}
			}
		}
		// selection index
		g.setColor(Color.red);
		g.drawRect(inventory.itemIndex.getCellX() * itemIconSize + 2, inventory.itemIndex.getCellY() * itemIconSize + 2,
				itemIconSize - 4, itemIconSize - 4);
		g.popTransform();

	}

	@Override
	public void inputEvent(Input input) {
		if (inRange(input.getMouseX(), input.getMouseY()) == true) {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				int cellx = (int) Functions.map(input.getMouseX(), transform.position.x, transform.position.x + width,
						0, inventory.getXAxisMaxCell());
				int celly = (int) Functions.map(input.getMouseY(), transform.position.y, transform.position.y + height,
						0, inventory.getYAxisMaxCell());
				inventory.itemIndex.setCell(cellx, celly);
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
