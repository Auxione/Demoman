package Curio.Items;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Curio.Tilemap.Tilemap;
import Default.Constants;
import Default.DynamicPlayer;

public abstract class Item {
	public static int[][][] itemMap;
	public static HashMap<Integer, Item> itemList;
	private static int tileMaxItems = 5;

	public static void createItemMap(Tilemap level) {
		// coordinate system x,y
		// itemMap[][][0] = item id;
		// itemMap[][][1] = item count;
		itemMap = new int[level.get_MaxCellX()][level.get_MaxCellY()][2];

		itemList = new HashMap<Integer, Item>();

		for (int x = 0; x < itemMap.length; x++) {
			for (int y = 0; y < itemMap[0].length; y++) {
				itemMap[x][y][0] = 0;
				itemMap[x][y][1] = 0;
			}
		}
		itemList.put(1, new Medpack());
		itemList.put(2, new Stimpack());
		itemList.put(3, new Pizza());
		itemList.put(4, new PizzaSlice());
	}

	public static void mainRender(Graphics g) {
		for (int x = 0; x < itemMap.length; x++) {
			for (int y = 0; y < itemMap[0].length; y++) {
				if (itemMap[x][y][0] > 0 && itemMap[x][y][1] > 0) {
					g.drawImage(itemList.get(itemMap[x][y][0]).getImage(), x * Constants.CellSize,
							y * Constants.CellSize);
					g.setColor(Color.black);
					g.drawString(Integer.toString(itemMap[x][y][1]), x * Constants.CellSize - 5,
							y * Constants.CellSize - 5);
				}
			}
		}
	}

	// this function returns true if item added or item count modified
	public static boolean put(int x, int y, int id) {
		// check if theres a item on position
		if (itemMap[x][y][1] <= tileMaxItems) {
			if (itemMap[x][y][0] == 0) {
				itemMap[x][y][0] = id;
				itemMap[x][y][1] = itemMap[x][y][1] + 1;
				return true;
			}
			// if theres a item on that position just edit counter
			else if (itemMap[x][y][0] == id) {
				itemMap[x][y][1] = itemMap[x][y][1] + 1;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	// this function returns item id if conditions are met
	public static int get(int x, int y) {
		// check if theres a item on position
		if (itemMap[x][y][0] > 0 && itemMap[x][y][1] > 0) {
			return itemMap[x][y][0];
		} else {
			return 0;
		}
	}

	public static void remove(int x, int y) {
		itemMap[x][y][1] -= 1;
		if (itemMap[x][y][0] == 0 || itemMap[x][y][1] <= 0) {
			itemMap[x][y][0] = 0;
			itemMap[x][y][1] = 0;
		}
	}

	public abstract Image getImage();

	public abstract void apply(DynamicPlayer dp);

	public abstract void apply(Tilemap level);

	public abstract boolean condition(DynamicPlayer dp);
}
