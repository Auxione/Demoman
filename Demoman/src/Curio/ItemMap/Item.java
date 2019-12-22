package Curio.ItemMap;

import org.newdawn.slick.Image;

import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Player;

public interface Item {

	public Image getImage();

	public void apply(Player p, TileMap level, BombManager bm);

	public boolean condition(Player p, TileMap level, BombManager bm);
}
