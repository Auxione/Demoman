package Curio.Renderer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import Curio.Functions;
import Curio.TileList;
import Curio.Viewport;
import Curio.ItemSystem.ItemList;
import Curio.ItemSystem.ItemMap;
import Curio.SessionManagers.PlantManager;
import Curio.SessionManagers.PlayerManager;
import Curio.SessionManagers.WorldManager;
import Curio.Utilities.CellCoordinate;
import Curio.Utilities.Math.Transform;
import Default.Player;

public class MouseStatsDisplay extends TextDisplay implements Renderer {
	private Viewport viewPort;
	private ItemMap itemMap;

	private PlantManager plantManager;
	private PlayerManager playerManager;
	private WorldManager worldManager;
	
	private Transform worldPosition;
	private Transform screenPosition;
	private CellCoordinate cellPosition;
	private boolean tileDisplayActive = true;
	
	public MouseStatsDisplay(Viewport viewPort, WorldManager worldManager, PlantManager plantManager,
			PlayerManager playerManager, ItemMap itemMap) {
		super();
		this.viewPort = viewPort;
		this.itemMap = itemMap;
		this.plantManager = plantManager;
		this.playerManager = playerManager;
		this.worldManager = worldManager;

		this.screenPosition = new Transform();
		this.worldPosition = new Transform();
		this.cellPosition = new CellCoordinate();
	}

	public void getCellData() {
		String data = "";
		for (Player p : playerManager.playerList) {
			if (worldPosition.dist(p.transform) < p.psize) {
				data += " \n";
				data += "Player: \n";
				data += "HP: " + p.getCurrentHealth() + "\n";
				data += "Food: " + p.getCurrentFood() + "\n";
			}
		}
		int itemID = itemMap.getTile(cellPosition, 0);
		if (itemID != 0) {
			data += " \n";
			data += "Item ID: " + itemID + "\n";
			data += "Name: " + ItemList.list.get(itemID).getName() + "\n";
			data += "Description: " + ItemList.list.get(itemID).getDescription() + "\n";
		}
		int plantID = plantManager.plantMap.getTile(cellPosition, 0);
		if (plantID != 0) {
			data += " \n";
			data += "Plant ID:" + plantID + "\n";
			data += "Name: " + plantManager.getName(plantID) + "\n";
			data += "Description: " + plantManager.getDescription(plantID) + "\n";
			data += "HP: " + plantManager.getHealth(cellPosition.getCellX(), cellPosition.getCellY()) + "\n";
		}
		if (tileDisplayActive == false) {
			int tileID = worldManager.tileMap.getTile(cellPosition, 0);
			data += " \n";
			data += "Tile ID: " + tileID + "\n";
			data += "Name: " + TileList.getTile(tileID).getName() + "\n";
			data += "canBreak: " + TileList.getTile(tileID).getUnbreakable() + "\n";
			data += "canBurn: " + TileList.getTile(tileID).isFlammable() + "\n";
			data += "canMove: " + TileList.getTile(tileID).isSolid() + "\n";
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
		cellPosition = Functions.worldPostoCellPosition(worldPosition);
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
