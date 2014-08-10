package com.gbjam;


import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Polygon;
import com.gbjam.resource_mgmt.Art;

public class MapGenerator {
	public static void initMap(TiledMap map) {
		TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
		cell.setTile(new StaticTiledMapTile(Art.platform.textures[0][0]));

		TiledMapTileLayer drawingLayer = (TiledMapTileLayer) map.getLayers()
				.get("Foreground");
		MapLayer wallLayer = map.getLayers().get("Walls");
		MapObjects objects = wallLayer.getObjects();

		Polygon testGon = new Polygon(new float[]{0,0, 8,0, 0,8, 8,8});

		PolygonMapObject coolNewObject = new PolygonMapObject(testGon);
		MapProperties props = coolNewObject.getProperties();
		props.put("x", 20*8);
		props.put("y", 16*8);
		objects.add(coolNewObject);

		drawingLayer.setCell(20, 16, cell);
	}
}
