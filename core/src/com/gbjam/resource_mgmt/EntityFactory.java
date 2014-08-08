package com.gbjam.resource_mgmt;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.game_components.collision.CollisionComponent;
import com.gbjam.game_components.collision.CollisionComponent.ColliderType;
import com.gbjam.game_components.generator.GeneratorComponent;
import com.gbjam.game_components.graphics.GraphicsComponent;
import com.gbjam.game_components.input.InputComponent;
import com.gbjam.game_components.physics.PhysicsComponent;


public class EntityFactory {
	public static class EntityInfo {
		public Entity entity;
		
		public String type;
		public int id, health;
		public float[] polygon;
		public ColliderType colliderType;

		public GraphicsComponent graphics;
		public InputComponent input;
		public CollisionComponent collision;
		public PhysicsComponent physics;
		public GeneratorComponent generator;
		public String template;
		
		public ArrayList<EntityInfo> subTypes;
	}
	
	public static ArrayList<EntityInfo> entityInfos;
	public static Entity[] entities;
	
	@SuppressWarnings("unchecked")
	public static void load() {
		Json json = new Json();
		json.addClassTag("entity", EntityInfo.class);
		entityInfos = new ArrayList<EntityInfo>();
		entityInfos = json.fromJson(entityInfos.getClass(), Gdx.files.internal("entities.json"));
	
		// Initialize the components of every entity, since they weren't
		// Properly initialized during instantiation
		for(EntityInfo entityInfo : entityInfos) {
			if(entityInfo.graphics != null)
				entityInfo.graphics.init(Art.getArt(entityInfo.type));
			if(entityInfo.collision != null)
				entityInfo.collision.init(entityInfo.colliderType);
			
			// Create Entity
			if(entityInfo.entity == null)
				entityInfo.entity = new Entity();
		}
		
		// Do another pass to grab the templates
		for(EntityInfo entityInfo : entityInfos) {
			if(entityInfo.generator != null) {
				entityInfo.generator.setTemplate(generate(entityInfo.template, null));
				entityInfo.generator.setSoundToPlay(Sounds.getSound(entityInfo.generator.soundName));
			}
		}
	}
	
	public static int getEntityID(String name) {
		for(EntityInfo e : entityInfos) {
			if(e.type.equals(name)) {
				return e.id;
			}
		}
		Gdx.app.log("WARNING", "Entity type " + name + " not found.");
		return -1;
	}
	
	public static EntityInfo getEntityInfo(int id) {
		if(id >= 0)
			return entityInfos.get(id);
		else
			return null;
	}
	
	public static EntityInfo getEntityInfo(String name) {
		for(EntityInfo e : entityInfos) {
			if(e.type.equals(name)) {
				return e;
			}
		}
		Gdx.app.log("WARNING", "Entity type " + name + " not found.");
		return null;
	}

	public static Entity generate(String name, GameScreen world) {
		for(EntityInfo e : entityInfos) {
			if(e.type.equals(name)) {
				if(e.generator != null)
					e.generator.setWorld(world);
				
				if(!e.entity.initialized())
					e.entity.init(e.graphics, e.collision, e.physics, e.input, e.generator);
				
				return e.entity.clone();
			}
		}
		Gdx.app.log("WARNING", "Entity type " + name + " not found.");
		return null;
	}
}
