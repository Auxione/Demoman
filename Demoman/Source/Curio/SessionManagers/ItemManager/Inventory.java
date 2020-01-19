package Curio.SessionManagers.ItemManager;

import Curio.Console;
import Curio.CellularMap.CellularMap;
import Curio.Utilities.CellCoordinate;

@SuppressWarnings("serial")
public class Inventory extends CellularMap {
	private Console console = null;
	private final String consolePrefix = "Inventory: ";

	public final int inventoryCellSize;
	public CellCoordinate itemIndex = new CellCoordinate(0, 0);

	public Inventory(int inventoryXSize, int inventoryYSize, int inventoryCellSize) {
		super(inventoryXSize, inventoryYSize, 2);
		this.inventoryCellSize = inventoryCellSize;
		super.clearCells();
	}

	public Inventory setConsole(Console console) {
		this.console = console;
		String cmd = consolePrefix + "Initialized and assigned.";
		console.Add(0, cmd);
		return this;
	}

	public CellCoordinate searchItem(Item item) {
		int itemID = ItemList.IDlist.get(item);
		for (int x = 0; x < super.getXAxisMaxCell(); x++) {
			for (int y = 0; y < super.getYAxisMaxCell(); y++) {
				if (super.getCell(x, y, 0) == itemID) {
					if (console != null) {
						console.Add(0, consolePrefix + "Item found at " + x + "-" + y + " .");
					}
					return new CellCoordinate(x, y);
				}
			}
		}
		if (console != null) {
			console.Add(0, consolePrefix + "Item not found.");
		}
		return null;
	}

	public Item getItemFromIndex() {
		return getItemFromCell(itemIndex.getCellX(), itemIndex.getCellY());
	}

	public Item getItemFromCell(int x, int y) {
		if (super.getCell(x, y, 0) != 0) {
			if (console != null) {
				console.Add(0, consolePrefix + "Theres an item in cell: " + x + "-" + y + " .");
			}
			return ItemList.list.get(super.getCell(x, y, 0));
		}
		return null;
	}

	public void removeItemFromIndex(int count) {
		removeItem(itemIndex.getCellX(), itemIndex.getCellY(), count);
	}

	public void removeItem(int x, int y, int count) {
		super.setCell(x, y, 1, super.getCell(x, y, 1) - count);
		// check the itemcount if its 0
		if (super.getCell(x, y, 1) <= 0) {
			super.clearCell(x, y);
		}
		if (console != null) {
			console.Add(0, consolePrefix + "Item removed from cell: " + x + "-" + y + " .");
		}
	}

	public void changeIndex() {
		itemIndex.setCellX(itemIndex.getCellX() + 1);
		if (itemIndex.getCellX() >= super.getXAxisMaxCell()) {
			itemIndex.setCellY(itemIndex.getCellY() + 1);
			itemIndex.setCellX(0);
			if (itemIndex.getCellY() >= super.getYAxisMaxCell()) {
				itemIndex.setCellY(0);
			}
		}
	}

	public boolean putItemToCell(Item item) {
		int itemID = ItemList.IDlist.get(item);
		// check if theres a empty cell
		for (int y = 0; y < super.getYAxisMaxCell(); y++) {
			for (int x = 0; x < super.getXAxisMaxCell(); x++) {
				if (super.isEmpty(x, y) == true) {
					super.setCell(x, y, 0, itemID);
					super.setCell(x, y, 1, 1);
					if (console != null) {
						console.Add(0, consolePrefix + "Item added to cell: " + x + "-" + y + " ID: " + itemID);
					}
					return true;
				}
				// if theres an item
				else if (super.isEmpty(x, y) == false) {
					// check if the item has the same id
					if (super.getCell(x, y, 0) == itemID) {
						// just increase count
						if (super.getCell(x, y, 1) < inventoryCellSize) {
							super.setCell(x, y, 1, super.getCell(x, y, 1) + 1);
							if (console != null) {
								console.Add(0, consolePrefix + "Item added to cell: " + x + "-" + y + " ID: " + itemID);
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
