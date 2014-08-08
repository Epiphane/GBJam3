package com.gbjam.resource_mgmt;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.gbjam.game_components.collision.CollisionComponent.ColliderType;


public class EntityFactory {
	public static class EntityInfo {
		public String type;
		public int id, health;
		public float[] polygon;
		public ColliderType colliderType;
		public ArrayList<EntityInfo> subTypes;
	}
	
	public static ArrayList<EntityInfo> entities;
	
	@SuppressWarnings("unchecked")
	public static void load() {
		Json json = new Json();
		json.addClassTag("entity", EntityInfo.class);
		entities = new ArrayList<EntityInfo>();
		entities = json.fromJson(entities.getClass(), Gdx.files.internal("entities.json"));
	}
	
	public static int getEntityID(String name) {
		for(EntityInfo e : entities) {
			if(e.type.equals(name)) {
				return e.id;
			}
		}
		Gdx.app.log("WARNING", "Entity type " + name + " not found.");
		return -1;
	}
	
	public static EntityInfo getEntityInfo(int id) {
		if(id >= 0)
			return entities.get(id);
		else
			return null;
	}
	
	public static EntityInfo getEntityInfo(String name) {
		for(EntityInfo e : entities) {
			if(e.type.equals(name)) {
				return e;
			}
		}
		Gdx.app.log("WARNING", "Entity type " + name + " not found.");
		return null;
	}
}
