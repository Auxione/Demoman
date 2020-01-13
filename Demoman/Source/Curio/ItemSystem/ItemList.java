package Curio.ItemSystem;

import java.util.HashMap;

import Curio.ItemSystem.Items.Berries;
import Curio.ItemSystem.Items.DefaultBomb;
import Curio.ItemSystem.Items.Medpack;
import Curio.ItemSystem.Items.NapalmBomb;
import Curio.ItemSystem.Items.Pizza;
import Curio.ItemSystem.Items.PizzaSlice;
import Curio.ItemSystem.Items.Sausage;
import Curio.ItemSystem.Items.SausageSeed;
import Curio.ItemSystem.Items.Stimpack;
import Curio.ItemSystem.Items.Torch;


public class ItemList {
	public static HashMap<Integer, Item> list= new HashMap<Integer, Item>();
	
	public ItemList() {
		list.put(1, new Medpack());
		list.put(2, new Stimpack());
		list.put(3, new Pizza());
		list.put(4, new PizzaSlice());
		list.put(5, new DefaultBomb());
		list.put(6, new NapalmBomb());
		list.put(7, new Sausage());
		list.put(8, new Berries());
		list.put(9, new SausageSeed());
		list.put(10, new Torch());
	}
}
