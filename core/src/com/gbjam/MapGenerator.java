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
import com.gbjam.utility.PointM;
import com.gbjam.utility.Utility;

public class MapGenerator {
	private static int chunkSizeWidth = 100, chunkSizeHeight = 90;

	public static void initSection(TiledMap map, int baseX, int baseY) {
		// We can reuse this cell over and over to set up the drawable portion of the map
		TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
		cell.setTile(new StaticTiledMapTile(Art.platform.textures[0][0]));

		TiledMapTileLayer drawingLayer = (TiledMapTileLayer) map.getLayers()
				.get("Foreground");
		MapLayer wallLayer = map.getLayers().get("Walls");
		MapObjects objects = wallLayer.getObjects();
		
		boolean[][] occupiedCells = new boolean[chunkSizeWidth][chunkSizeHeight];

		for (int platformSize = 8; platformSize > 2; platformSize --) {

			boolean doneWithSize = false;
			while (!doneWithSize) {
				ArrayList<PointM> platformOffsets = new ArrayList<PointM>();
				PointM currPosition = new PointM(0, 0);

				int currDirection = Utility.random(0, 4) * 2;

				for (int tilesMade = 0; tilesMade < Utility.random(2, platformSize); tilesMade++) {
					if (platformOffsets.indexOf(currPosition) == -1)
						platformOffsets.add(currPosition.clone());
					
					int whereToGo = Utility.random(0, 4);
					// 0-1, stay same direction (do nothing)
					// 2, turn left
					if (whereToGo == 2) {
						currDirection = Utility.keepInRange(currDirection - 2, 8);
					}

					// 3, turn right
					if (whereToGo == 3) {
						currDirection = Utility.keepInRange(currDirection + 2, 8);
					}
					
					System.out.println("Directino is now " + currDirection);

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
							if (!Utility.pointInBounds(pointToCheck, 0, chunkSizeWidth, 0, chunkSizeHeight) || 
									occupiedCells[(int) pointToCheck.x][(int) pointToCheck.y]) {
								foundHome = false;
								break;
							}
						}
						
						if (foundHome) {
							doneWithSize = false;  // this breaks us out of the 'x' and 'y' loop and keeps us IN the 'generating platforms for this level' while loop
							
							// Put our cool new platform into the array
							for (int tilesPlaced = 0; tilesPlaced < platformOffsets.size(); tilesPlaced++) {
								// Occupy the two tiles above and below you lel
								PointM pointToAdd = platformOffsets.get(tilesPlaced).clone();
								pointToAdd.addPoint(x, y);
								
								Utility.reserveSpace(occupiedCells, pointToAdd.x, pointToAdd.y);

								Polygon wallGon = new Polygon(new float[]{0,0, 16,0, 0,16, 16,16});
								PolygonMapObject wallObject = new PolygonMapObject(wallGon);
								MapProperties props = wallObject.getProperties();
								props.put("x", pointToAdd.x*16);
								props.put("y", pointToAdd.y*16);
								
								objects.add(wallObject);
								
								drawingLayer.setCell(pointToAdd.x, pointToAdd.y, cell);
							}
						}
							
					} // end "y" for loop
				} // end "x" for loop
			}
		}

		
		
		

//		MapProperties props = coolNewObject.getProperties();
//		props.put("x", 20*8);
//		props.put("y", 16*8);
//		objects.add(coolNewObject);
//
//		drawingLayer.setCell(20, 16, cell);
//		drawingLayer.setCell(21, 16, cell);
	}
}
