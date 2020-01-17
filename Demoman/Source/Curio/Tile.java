package Curio;

import org.newdawn.slick.Image;

public class Tile {
	private String name; // name of the tile
	private boolean isSolid; // player and other items collide with this tile
	private boolean unbreakable; // take damage
	private int tileMaxHP; // maximum damage can block take before gets destroyed
	private boolean flammable; // maximum damage can block take before gets destroyed
	private Image texture; // block texture
	private boolean canplant;
	private boolean canflow;

	public Tile() {
	}
	public Tile setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public Tile setSolid(boolean isSolid) {
		this.isSolid = isSolid;
		return this;
	}
	public boolean isSolid() {
		return isSolid;
	}
	
	public Tile setUnbreakable(boolean unbreakable) {
		this.unbreakable = unbreakable;
		return this;
	}
	public boolean getUnbreakable() {
		return unbreakable;
	}
	
	public Tile setTileMaxHP(int tileMaxHP) {
		this.tileMaxHP = tileMaxHP;
		return this;
	}
	public int getTileMaxHP() {
		return tileMaxHP;
	}
	
	public Tile setFlammable(boolean flammable) {
		this.flammable = flammable;
		return this;
	}
	public boolean isFlammable() {
		return flammable;
	}
	public Tile setTexture(Image texture) {
		this.texture = texture;
		return this;
	}
	public Image getTexture() {
		return texture;
	}
	
	public Tile setCanPlant(boolean canplant) {
		this.canplant = canplant;
		return this;
	}	
	public boolean getCanplant() {
		return canplant;
	}
	
	public Tile setCanFlow(boolean canflow) {
		this.canflow = canflow;
		return this;
	}
	public boolean getCanflow() {
		return canflow;
	}

}
