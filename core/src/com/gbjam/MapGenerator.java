package com.gbjam;


import java.util.ArrayList;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.gbjam.resource_mgmt.Art;
import com.gbjam.resource_mgmt.EntityFactory;
import com.gbjam.utility.PointM;
import com.gbjam.utility.Utility;

public class MapGenerator {
	public static void initSection(TiledMap map, int baseX, int baseY, 
			int chunkSizeWidth, int chunkSizeHeight,
			PointM playerPos, GameScreen world, boolean isBossLevel) {
		// We can reuse this cell over and over to set up the drawable portion of the map
		TiledMapTileLayer.Cell cells[][] = new TiledMapTileLayer.Cell[4][2];
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[0].length; y++) {
				cells[x][y] = new TiledMapTileLayer.Cell();
				cells[x][y].setTile(new StaticTiledMapTile(Art.platform.textures[x][y]));
			}
		}
		
		TiledMapTileLayer drawingLayer = (TiledMapTileLayer) map.getLayers()
				.get("Foreground");
		MapLayer wallLayer = map.getLayers().get("Walls");
		MapObjects objects = wallLayer.getObjects();
		
		boolean[][] occupiedCells = new boolean[chunkSizeWidth][chunkSizeHeight];
		occupiedCells[playerPos.x][playerPos.y] = true;
		occupiedCells[playerPos.x][playerPos.y + 1] = true;
		occupiedCells[playerPos.x + 1][playerPos.y] = true;
		occupiedCells[playerPos.x + 1][playerPos.y + 1] = true;
		occupiedCells[playerPos.x + 2][playerPos.y] = true;
		occupiedCells[playerPos.x + 2][playerPos.y + 1] = true;
		
		if (!isBossLevel) {
			for (int ox = -5; ox < 1; ox++) {
				for (int oy = -5; oy < 1; oy++) {
					Utility.setCoordsToWhat(occupiedCells, chunkSizeWidth + ox, chunkSizeHeight + oy, true);
				}
			}
		}
		
		for (int platformSize = 5; platformSize > 2; platformSize --) {

			boolean doneWithSize = false;
			while (!doneWithSize) {
				ArrayList<PointM> platformOffsets = new ArrayList<PointM>();
				PointM currPosition = new PointM(0, 0);

				int currDirection = Utility.random(0, 2) * 4 + 2;

				for (int tilesMade = 0; tilesMade < Utility.random(2, platformSize); tilesMade++) {
					if (platformOffsets.indexOf(currPosition) == -1)
						platformOffsets.add(currPosition.clone());
					
					int whereToGo = Utility.random(0, 12);
					// 0-3, stay same direction (do nothing)
					// 2, turn left
					if (whereToGo == 2) {
						currDirection = Utility.keepInRange(currDirection - 2, 8);
					}

					// 3, turn right
					if (whereToGo == 3) {
						currDirection = Utility.keepInRange(currDirection + 2, 8);
					}
					
					// Change the position of the point
					currPosition.addPoint(Utility.pointMFromDir(currDirection));
				}


				// Look for a new home for our platform
				doneWithSize = true;
				for (int x = 0; x < chunkSizeWidth && doneWithSize; x++) {
					for (int y = 0; y < chunkSizeHeight && doneWithSize; y++) {
						// Go through each of the points of the platform and see if they fit
						boolean foundHome = true;
						
						int randomX, randomY;
						
						if (isBossLevel) {
							randomX = Utility.random(0, chunkSizeWidth);
							randomY = Utility.random(0, chunkSizeHeight);
						}
						else {
							randomX = x;
							randomY = y;
						}
						for (int tilesChecked = 0; tilesChecked < platformOffsets.size(); tilesChecked++) {
							PointM pointToCheck = platformOffsets.get(tilesChecked).clone();
							pointToCheck.addPoint(randomX, randomY);
							if (!Utility.pointInBounds(pointToCheck, baseX, chunkSizeWidth, baseY, chunkSizeHeight) || 
									occupiedCells[(int) pointToCheck.x][(int) pointToCheck.y]) {
								foundHome = false;
								break;
							}
						}
						
						if (foundHome) {
							doneWithSize = false;  // this breaks us out of the 'x' and 'y' loop and keeps us IN the 'generating platforms for this level' while loop
							
							// Put our cool new platform into the array
							int horizLength = 0, lastY = 0;;
							for (int tilesPlaced = 0; tilesPlaced < platformOffsets.size(); tilesPlaced++) {
								// Occupy the two tiles above and below you lel
								PointM pointToAdd = platformOffsets.get(tilesPlaced).clone();
								PointM above = pointToAdd.clone();
								pointToAdd.addPoint(randomX, randomY);
								if(lastY != pointToAdd.y) {
									horizLength = 1;
									lastY = pointToAdd.y;
								}
								else {
									horizLength ++;
								}
								
								if(horizLength > 0 && tilesPlaced < platformOffsets.size() - 1) {
									
									PointM nextPoint = platformOffsets.get(tilesPlaced + 1).clone();
									nextPoint.addPoint(randomX, randomY);
									
									if(lastY == nextPoint.y){ 
										Entity newEnemy = EntityFactory.getRandomEnemy(((float) (x + Utility.random(0,  10)) / chunkSizeWidth) * 30, world);
										if(newEnemy != null) {
											newEnemy.setX(pointToAdd.x * 16);
											newEnemy.setY(pointToAdd.y * 16 + 16);
											world.addEntity(newEnemy);											
										}
									}
								}
								
								Utility.reserveSpace(occupiedCells, pointToAdd.x, pointToAdd.y);

								Polygon wallGon = new Polygon(new float[]{0,0, 16,0, 0,16, 16,16});
								PolygonMapObject wallObject = new PolygonMapObject(wallGon);
								MapProperties props = wallObject.getProperties();
								props.put("x", pointToAdd.x*16);
								props.put("y", pointToAdd.y*16);
								
								objects.add(wallObject);

								int textureX = Utility.random(0, 4), textureY = 0;
								
								above.addPoint(0, 1);
								
								if (platformOffsets.indexOf(above) != -1) {
									textureY = 1;
								}

								
								drawingLayer.setCell(pointToAdd.x, pointToAdd.y, cells[textureX][textureY]);
							}
						}
							
					} // end "y" for loop
				} // end "x" for loop
			}
		}
	}
	
	/**
	 * Make a ending door at the bottom right corner
	 */
	public static void initDoor(TiledMap map, int baseX, int baseY, 
			int chunkSizeWidth, int chunkSizeHeight) {
		// We can reuse this cell over and over to set up the drawable portion of the map
		TiledMapTileLayer.Cell doorCell = new TiledMapTileLayer.Cell();
		doorCell.setTile(new StaticTiledMapTile(Art.door.textures[0][0]));

		TiledMapTileLayer drawingLayer = (TiledMapTileLayer) map.getLayers()
				.get("Foreground");
		MapLayer wallLayer = map.getLayers().get("Walls");
		MapObjects objects = wallLayer.getObjects();
		
		drawingLayer.setCell(2, 2, doorCell);
	}
}
