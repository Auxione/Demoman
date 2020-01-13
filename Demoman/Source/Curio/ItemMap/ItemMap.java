package Curio.ItemMap;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import Curio.Console;
import Curio.ItemMap.Items.Berries;
import Curio.ItemMap.Items.DefaultBomb;
import Curio.ItemMap.Items.Medpack;
import Curio.ItemMap.Items.NapalmBomb;
import Curio.ItemMap.Items.Pizza;
import Curio.ItemMap.Items.PizzaSlice;
import Curio.ItemMap.Items.Sausage;
import Curio.ItemMap.Items.SausageSeed;
import Curio.ItemMap.Items.Stimpack;
import Curio.Tilemap.TileMap;
import Default.Constants;

public class ItemMap {
	private int[][][] itemMap;
	public HashMap<Integer, Item> itemList;
	private int tileMaxItems = 5;
	private Console console;
	
	public int[][][] getMap() {
		return itemMap;
	}

	private void putItems() {
		itemList.put(1, new Medpack());
		itemList.put(2, new Stimpack());
		itemList.put(3, new Pizza());
		itemList.put(4, new PizzaSlice());
		itemList.put(5, new DefaultBomb());
		itemList.put(6, new NapalmBomb());
		itemList.put(7, new Sausage());
		itemList.put(8, new Berries());
		itemList.put(9, new SausageSeed());
	}

	public ItemMap(TileMap level, int tileMaxItems, Console console) {
		this.console = console;
		this.tileMaxItems = tileMaxItems;
		// coordinate system x,y
		// itemMap[][][0] = item id;
		// itemMap[][][1] = item count;
		itemMap = new int[level.get_MaxCellX()][level.get_MaxCellY()][2];

		itemList = new HashMap<Integer, Item>();

		for (int x = 0; x < itemMap.length; x++) {
			for (int y = 0; y < itemMap[x].length; y++) {
				itemMap[x][y][0] = 0;
				itemMap[x][y][1] = 0;
			}
		}
		putItems();
		console.Add(0, "Item: System initialized");
	}
	
	public void updateMap(int[][][] map) {
		itemMap = map;
	}
	
	public ItemMap(TileMap level, int tileMaxItems) {
		this.console = null;
		this.tileMaxItems = tileMaxItems;
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
		putItems();
	}

	public void render(Graphics g) {
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
	public boolean put(int x, int y, int id) {
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

	public void applyDamage(int x, int y, int damage) {
		clearCell(x, y);
	}

	private void clearCell(int x, int y) {
		for (int i = 0; i < itemMap[0][0].length; i++) {
			itemMap[x][y][i] = 0;
		}
	}

	// this function returns item id if conditions are met
	public int getItemID(int x, int y) {
		// check if theres a item on position
		if (x >= 0 && x < itemMap.length && y >= 0 && y < itemMap[0].length) {
			// if its inside the tilemap array
			if (itemMap[x][y][0] > 0 && itemMap[x][y][1] > 0) {
				return itemMap[x][y][0];
			}
		}
		return 0;
	}

	public Item getItem(int itemID) {
		return this.itemList.get(itemID);
	}

	public void remove(int x, int y) {
		itemMap[x][y][1] -= 1;
		if (itemMap[x][y][0] == 0 || itemMap[x][y][1] <= 0) {
			clearCell(x, y);
		}
		if (console != null) {
			String cmd = "removed item from:" + x + "-" + y + ".";
			console.Add(0, cmd);
		}
	}
}
