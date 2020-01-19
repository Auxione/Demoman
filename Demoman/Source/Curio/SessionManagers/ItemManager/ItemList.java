package Curio.SessionManagers.ItemManager;

import java.util.HashMap;

import Curio.SessionManagers.ItemManager.Items.ItemBerries;
import Curio.SessionManagers.ItemManager.Items.ItemDefaultBomb;
import Curio.SessionManagers.ItemManager.Items.ItemMedpack;
import Curio.SessionManagers.ItemManager.Items.ItemNapalmBomb;
import Curio.SessionManagers.ItemManager.Items.ItemPizza;
import Curio.SessionManagers.ItemManager.Items.ItemPizzaSlice;
import Curio.SessionManagers.ItemManager.Items.ItemSausage;
import Curio.SessionManagers.ItemManager.Items.ItemSausageSeed;
import Curio.SessionManagers.ItemManager.Items.ItemStimpack;
import Curio.SessionManagers.ItemManager.Items.ItemTorch;

public class ItemList {
	public static HashMap<Integer, Item> list = new HashMap<Integer, Item>();
	public static HashMap<Item, Integer> IDlist = new HashMap<Item, Integer>();

	public ItemList() {
		put(1, new ItemMedpack());
		put(2, new ItemStimpack());
		put(3, new ItemPizza());
		put(4, new ItemPizzaSlice());
		put(5, new ItemDefaultBomb());
		put(6, new ItemNapalmBomb());
		put(7, new ItemSausage());
		put(8, new ItemBerries());
		put(9, new ItemSausageSeed());
		put(10, new ItemTorch());
	}

	void put(int id, Item item) {
		list.put(id, item);
		IDlist.put(item, id);
	}
}
