package Curio.PlantMap;

import org.newdawn.slick.Image;

import Curio.Tilemap.TileMap;
import Curio.Tilemap.Bomb.BombManager;
import Default.Player;

public interface Plant {

	public boolean plantCondition(Player p, TileMap level, BombManager bm);

	public boolean harvestCondition(Player p, TileMap level, BombManager bm);

	public void growthRate(float percentage);

	public Image getImage();

	public int getMaxHealth();

	public int getMaxGrowth();
}
