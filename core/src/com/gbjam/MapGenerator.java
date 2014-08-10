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
			PointM playerPos, GameScreen world) {
		// We can reuse this cell over and over to set up the drawable portion of the map
		TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
		cell.setTile(new StaticTiledMapTile(Art.dungeonBrix.textures[0][0]));

		TiledMapTileLayer drawingLayer = (TiledMapTileLayer) map.getLayers()
				.get("Foreground");
		MapLayer wallLayer = map.getLayers().get("Walls");
		MapObjects objects = wallLayer.getObjects();
		
		boolean[][] occupiedCells = new boolean[chunkSizeWidth][chunkSizeHeight];
		occupiedCells[playerPos.x][playerPos.y] = true;
		occupiedCells[playerPos.x][playerPos.y + 1] = true;
		
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
						for (int tilesChecked = 0; tilesChecked < platformOffsets.size(); tilesChecked++) {
							PointM pointToCheck = platformOffsets.get(tilesChecked).clone();
							pointToCheck.addPoint(x, y);
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
								pointToAdd.addPoint(x, y);
								if(lastY != pointToAdd.y) {
									horizLength = 1;
									lastY = pointToAdd.y;
								}
								else {
									horizLength ++;
								}
								
								if(horizLength > 2) {
									Entity newEnemy = EntityFactory.getRandomEnemy(2, world);
									newEnemy.setX(pointToAdd.x * 16);
									newEnemy.setY(pointToAdd.y * 16 + 16);
									world.addEntity(newEnemy);
								}
								
								Utility.reserveSpace(occupiedCells, pointToAdd.x, pointToAdd.y);

								Polygon wallGon = new Polygon(new float[]{0,0, 16,0, 0,16, 16,16});
								PolygonMapObject wallObject = new PolygonMapObject(wallGon);
								MapProperties props = wallObject.getProperties();
								props.put("x", pointToAdd.x*16);
								props.put("y", pointToAdd.y*16);
								
								objects.add(wallObject);

								int textureX = Utility.keepInRange(pointToAdd.x, 3) + 3;
								int textureY = Utility.keepInRange(pointToAdd.y, 3);
								System.out.println("Point at " + pointToAdd.x + ", " + pointToAdd.y + " with texture " + textureX + ", " + textureY);
								
								cell.setTile(new StaticTiledMapTile(Art.dungeonBrix.textures[pointToAdd.x % 3 + 3][pointToAdd.y % 3]));
								drawingLayer.setCell(pointToAdd.x, pointToAdd.y, cell);
							}
						}
							
					} // end "y" for loop
				} // end "x" for loop
			}
		}
	}
}
