package com.easimer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 *
 * @author easimer
 */
public class DamnedProjectile implements Entity {
    double vel_x, vel_y;
    int x, y;
    int distance_flied;
    boolean dir = false;
    
    Sprite left, right, current;
    
    @Override
    public void Init() {
        distance_flied = 0;
        try
        {
            Texture t = new Texture();
            t.loadFromFile(Paths.get("data/dproj_left.png"));
            left = new Sprite();
            left.setTexture(t);
            //left.setScale(2, 2);
        } catch(IOException ex)
        {
            Logger.getLogger(CactusProjectile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            Texture t = new Texture();
            t.loadFromFile(Paths.get("data/dproj_right.png"));
            right = new Sprite();
            right.setTexture(t);
            //right.setScale(2, 2);
        } catch(IOException ex)
        {
            Logger.getLogger(CactusProjectile.class.getName()).log(Level.SEVERE, null, ex);
        }
        current = left;
        dir = false;
    }

    @Override
    public void Update(long dt) {
        if(vel_x == 0.0)
            vel_x = (dir) ? -6.5 : 6.5;
        current = (dir) ? right : left;
        x += (vel_x * ((double)dt / 10.0));
        y += (vel_y * ((double)dt / 10.0));
        if(vel_x < 0)
            current = left;
        else
            current = right;
        distance_flied += Math.abs(vel_x * ((double)dt / 10.0));
        distance_flied += Math.abs(vel_y * ((double)dt / 10.0));
        if(distance_flied > 1000)
        {
            EntityContainer.GetInstance().RemoveById(EntityContainer.GetInstance().GetEntityByObject(this)); //kill myself
        }
        for(String entid : EntityContainer.GetInstance().GetEntityList())
        {
            Entity e = EntityContainer.GetInstance().GetEntityByString(entid);
            if(Player.class.isInstance(e))
            {
                if(CollisionCheck(e.GetX(), e.GetY(), e.GetW(), e.GetH(), GetX(), GetY(), GetW(), GetH()))
                {
                    System.out.println("HIT");
                    Player m = (Player)e;
                    m.GetDamage(10);
                    EntityContainer.GetInstance().RemoveById(EntityContainer.GetInstance().GetEntityByObject(this));
                }
            }
        }
    }

    @Override
    public void Draw(RenderTarget rt) {
        int realy = Game.GetInstance().height - y - 59 - current.getTexture().getSize().y;
        current.move(x, realy);
        //RectangleShape rs = new RectangleShape();
        //rs.setPosition(x, y);
        //rs.setSize(new Vector2f(GetW(), GetH()));
        Game.GetInstance().Draw(current);
        //Game.GetInstance().Draw(rs);
        current.move(-x, -realy);
    }

    @Override
    public void Event(org.jsfml.window.event.Event e) {
        
    }
    
    public void Shoot(int x, int y, boolean direction) //left: false, right: true
    {
        if(direction)
            this.vel_x = 6.5;
        else
            this.vel_x = -6.5;
        this.x = x + ((direction) ? 24 : -24);
        this.y = (Game.GetInstance().height - y - 64 - 60);
        current = (direction) ? right : left;
        dir = direction;
        System.out.printf("DP shot X:%d Y:%d\n", this.x, this.y);
    }

    @Override
    public int GetX() {
        return x;
    }

    @Override
    public int GetY() {
        return y;
    }

    @Override
    public int GetW() {
        return current.getTexture().getSize().x;
    }

    @Override
    public int GetH() {
        return current.getTexture().getSize().y;
    }
    
    public boolean CollisionCheck(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2)
    {
        //1 : me
        //2 : enemy
        //return (abs(a.x - b.x) * 2 < (a.width + b.width)) && (abs(a.y - b.y) * 2 < (a.height + b.height));
        //if not ((me_y + me_h <= entity.position.y) or (me_y >= entity.position.y + mat_h) or (me_x >= entity.position.x + mat_w) or (me_x + me_w <= entity.position.x)):
        //not (x2 > x1 + w1 or x2 + w2 < x1 or y2 > y1 + h1 or y2 + h2 < y1)
        boolean b = (x1 + w1) >= x2 && x1 <= x2 + w2 && y1 + h1 >= y2 && y1 <= y2 + h2;
        //System.out.println(x1 + " " + y1 + " " + w1 + " " + h1 + " " + x2 + " " + y2 + " " + w2 + " " + h2 + " ");
        if(b) System.out.println("Collision!");
        return b;
    }
}
