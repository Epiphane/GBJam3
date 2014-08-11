package com.gbjam.resource_mgmt;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Json;
import com.gbjam.Entity;
import com.gbjam.GameScreen;
import com.gbjam.game_components.collision.CollisionComponent;
import com.gbjam.game_components.collision.CollisionComponent.ColliderType;
import com.gbjam.game_components.generator.GeneratorComponent;
import com.gbjam.game_components.graphics.GraphicsComponent;
import com.gbjam.game_components.input.InputComponent;
import com.gbjam.game_components.physics.PhysicsComponent;
import com.gbjam.game_components.status.AttributeComponent;
import com.gbjam.game_components.status.AttributeComponent.AttribType;
import com.gbjam.game_components.status.StatusComponent;
import com.gbjam.game_components.status.StatusComponent.StatusType;
import com.gbjam.utility.Point;
import com.gbjam.utility.Utility;


public class EntityFactory {
	public static class EntityInfo {
		public Entity entity;
		
		public String type;
		public int id;
		public float[] polygon;
		public ColliderType colliderType;
		
		public Point graphicOffset;

		public GraphicsComponent graphics;
		public InputComponent input;
		public StatusComponent status;
		public ArrayList<StatusTick> statusTicks;
		public AttributeComponent attributes;
		public ArrayList<Attrib> attribs;
		public CollisionComponent collision;
		public PhysicsComponent physics;
		public GeneratorComponent generator;
		public String template;
		
//		public ArrayList<EntityInfo> subTypes;
	}
	
	public static class StatusTick {
		StatusType type;
		int tick;
	}
	
	public static class Attrib {
		AttribType type;
		int val;
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
			if(entityInfo.graphicOffset != null)
				entityInfo.graphics.setTextureOffset(entityInfo.graphicOffset);
			
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
				
				if(!e.entity.initialized()) {
					e.entity.init(e.graphics, e.collision, e.physics, e.input, e.status, e.attributes, e.generator);

					if(e.statusTicks != null)
						for(StatusTick statusTick : e.statusTicks) {
							e.entity.getStatusComponent().statusTicks[statusTick.type.ordinal()] = statusTick.tick;
							e.entity.getStatusComponent().setStatus(StatusType.ALIVE, true);
						}
					if(e.attribs != null)
						for(Attrib attrib : e.attribs) {
							e.entity.setAttribute(attrib.type, attrib.val);
						}
					
					if(e.polygon != null)
						e.entity.setPolygon(new Polygon(e.polygon));
				}
				
				return e.entity.clone();
			}
		}
		Gdx.app.log("WARNING", "Entity type " + name + " not found.");
		return null;
	}
	
	public static Entity getRandomEnemy(float difficulty, GameScreen world) {
		ArrayList<String> enemies = new ArrayList<String>();
		
		if(difficulty >= 1) {
			enemies.add("slime");
			
			if(difficulty >= 2)	
				enemies.add("darkknight");
			
			int newEnemy = Utility.random(0, enemies.size());
			return generate(enemies.get(newEnemy), world);
		}
		else
			return null;
	}
}
