package Curio.Renderer;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.FluidMap;
import Curio.TileMap;
import Curio.TileList;
import Curio.Viewport;
import Curio.ItemSystem.ItemList;
import Curio.ItemSystem.ItemMap;
import Curio.PlantSystem.PlantMap;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Player;

public class MouseStatsDisplay extends TextDisplay implements Renderer {
	private Viewport viewPort;
	private ArrayList<Player> playerList;
	private PlantMap plantMap;
	private ItemMap itemMap;
	private TileMap tileMap;
	private FluidMap fluidMap;

	private Transform worldPosition;
	private Transform screenPosition;
	private CellCoordinate cellPosition;

	private boolean tileDisplayActive = false;

	public MouseStatsDisplay(Viewport viewPort, TileMap tileMap, ItemMap itemMap, PlantMap plantMap,
			FluidMap fluidMap) {
		super();
		this.playerList = new ArrayList<Player>();
		this.viewPort = viewPort;
		this.tileMap = tileMap;
		this.itemMap = itemMap;
		this.plantMap = plantMap;
		this.fluidMap = fluidMap;

		this.screenPosition = new Transform();
		this.worldPosition = new Transform();
		this.cellPosition = new CellCoordinate();
	}

	public MouseStatsDisplay addPlayer(Player player) {
		playerList.add(player);
		return this;
	}

	public void getCellData() {
		String data = "";
		for (Player p : playerList) {
			if (worldPosition.dist(p.transform) < p.psize) {
				data += " \n";
				data += "Player: \n";
				data += "HP: " + p.getCurrentHealth() + "\n";
				data += "Food: " + p.getCurrentFood() + "\n";
			}
		}
		int itemID = itemMap.getItemID(cellPosition.getCellX(), cellPosition.getCellY());
		if (itemID != 0) {
			data += " \n";
			data += "Item ID: " + itemID + "\n";
			data += "Name: " + ItemList.list.get(itemID).getName() + "\n";
			data += "Description: " + ItemList.list.get(itemID).getDescription() + "\n";
		}
		int plantID = plantMap.getTile(cellPosition, 0);
		if (plantID != 0) {
			data += " \n";
			data += "Plant ID:" + plantID + "\n";
			data += "Name: " + plantMap.getName(plantID) + "\n";
			data += "Description: " + plantMap.getDescription(plantID) + "\n";
			data += "HP: " + plantMap.getHealth(cellPosition.getCellX(), cellPosition.getCellY()) + "\n";
		}
		int fluidCount = fluidMap.getTile(cellPosition.getCellX(), cellPosition.getCellY(), 0);
		if (fluidCount > 0) {
			data += " \n";
			data += "Fluid: \n";
			data += "Count:" + fluidCount + "\n";
		}
		if (tileDisplayActive == true) {
			int tileID = tileMap.getTile(cellPosition, 0);
			data += " \n";
			data += "Tile ID: " + tileID + "\n";
			data += "Name: " + TileList.getTile(tileID).getName() + "\n";
			data += "canBreak: " + TileList.getTile(tileID).getUnbreakable() + "\n";
			data += "canBurn: " + TileList.getTile(tileID).isFlammable() + "\n";
			data += "canMove: " + TileList.getTile(tileID).isMoveable() + "\n";
			data += "HP: " + TileList.getTile(tileID).getTileMaxHP() + "\n";
		}
		super.updateString(data);
		super.setPosition(screenPosition);

	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	@Override
	public void inputEvent(Input input) {
		screenPosition.position.x = input.getMouseX();
		screenPosition.position.y = input.getMouseY();

		worldPosition = viewPort.ScreenToWorldPosition(screenPosition.position.x, screenPosition.position.y);
		cellPosition = tileMap.worldPostoCellPosition(worldPosition);
	}

	@Override
	public void loopStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loopEnd() {
		// TODO Auto-generated method stub

	}

}
