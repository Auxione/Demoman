package Curio.ItemSystem;

import java.util.HashMap;

import Curio.ItemSystem.Items.ItemBerries;
import Curio.ItemSystem.Items.ItemDefaultBomb;
import Curio.ItemSystem.Items.ItemMedpack;
import Curio.ItemSystem.Items.ItemNapalmBomb;
import Curio.ItemSystem.Items.ItemPizza;
import Curio.ItemSystem.Items.ItemPizzaSlice;
import Curio.ItemSystem.Items.ItemSausage;
import Curio.ItemSystem.Items.ItemSausageSeed;
import Curio.ItemSystem.Items.ItemStimpack;
import Curio.ItemSystem.Items.ItemTorch;

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
