package com.easimer;

import java.util.HashMap;
import java.util.Set;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.window.event.Event;
import java.util.UUID;

/**
 *
 * @author easimer
 */
public class EntityContainer implements Entity {
    private static EntityContainer instance;
    
    public static EntityContainer GetInstance()
    {
        if(instance == null)
        {
            instance = new EntityContainer();
        }
        return instance;
    }
    
    private HashMap<String, Entity> entities;
    private HashMap<String, Entity> entities_db;
    
    private boolean is_running = false;
    
    private EntityContainer()
    {
        entities = new HashMap<>();
    }
    
    @Override
    public void Update(long dt)
    {
        is_running = true;
        entities_db = (HashMap<String, Entity>)entities.clone();
        entities.get("core_background").Update(dt);
        for(String entid : entities.keySet())
        {
            if(entid.equals("core_background")) continue;
            //System.out.printf("Updating entity: %s\n", entid);
            entities.get(entid).Update(dt);
        }
        entities = (HashMap<String, Entity>)entities_db.clone();
        entities_db = null;
        is_running = false;
    }
    
    @Override
    public void Draw(RenderTarget rt)
    {
        //nincs szükség másolásra, kirajzolás közben nem szabadna egy entitásnak módosítani a másikat
        entities.get("core_background").Draw(rt); //először mindig a háttér rajzolódjon ki
        for(String entid : entities.keySet())
        {
            if(entid.equals("core_background")) continue;
            entities.get(entid).Draw(rt);
        }
    }

    @Override
    public void Init() {
        GetInstance();
    }
    
    public Entity GetEntityByString(String name)
    {
        Entity e = entities.get(name);
        return e;
    }

    @Override
    public void Event(Event e) {
        is_running = true;
        entities_db = (HashMap<String, Entity>)entities.clone();
        for(String entid : entities.keySet())
        {
            //System.out.printf("Updating entity: %s\n", entid);
            entities.get(entid).Event(e);
        }
        entities = (HashMap<String, Entity>)entities_db.clone();
        entities_db = null;
        is_running = false;
    }
    
    public String AddEntity(Entity ent)
    {
        String id = UUID.randomUUID().toString();
        return AddEntity(ent, id);
    }
    
    public String AddEntity(Entity ent, String name)
    {
        ent.Init();
        if(entities.containsKey(name))
        {
            return AddEntity(ent);
        }
        else
        {
            if(is_running)
                entities_db.put(name, ent);
            else
                entities.put(name, ent);
            System.out.println("Added entity " + name);
            return name;
        }
    }
    
    public Set<String> GetEntityList()
    {
        return entities.keySet();
    }

    @Override
    public int GetX() {
        return 0;
    }

    @Override
    public int GetY() {
        return 0;
    }
    
    public String GetEntityByObject(Entity e)
    {
        for(String entid : entities.keySet())
        {
            if(entities.get(entid).equals(e))
                return entid;
        }
        return null;
    }
    
    public Entity RemoveById(String entid)
    {
        if(is_running)
        {
            return entities_db.remove(entid);
        }
        else
        {
            return entities.remove(entid);
        }
    }

    @Override
    public int GetW() {
        return 0;
    }

    @Override
    public int GetH() {
        return 0;
    }
}
