package com.easimer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

/**
 *
 * @author easimer
 */
public class DamnedOne extends Monster {

    private Sprite sprite;
    private Entity target;
    
    private int alpha;
    
    private long timeSinceLastShot = 2001;

    DamnedOne(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void Init() {
        ai = new BadAI();
        try
        {
            Texture t = new Texture();
            t.loadFromFile(Paths.get("data/damned_one.png"));
            sprite = new Sprite();
            sprite.setTexture(t);
            //sprite.setScale(2, 2);
        } catch (IOException ex)
        {
            Logger.getLogger(DebugText.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(long dt) {
        if(alpha < 256)
            alpha++;
        timeSinceLastShot += dt;
        if(timeSinceLastShot > 3500)
        {
            if(target != null)
            {
                timeSinceLastShot = 0;
                DamnedProjectile prj = new DamnedProjectile();
                if((target.GetX() + 512) < GetX())
                {
                    prj.Shoot(GetX(), GetY(), false);
                }
                else
                {
                    prj.Shoot(GetX(), GetY(), true);
                }
                EntityContainer.GetInstance().AddEntity(prj);
            }
        }
        ai.Think(dt, this);
        if(GetHealth() <= 0)
        {
            dead = true;
            EntityContainer.GetInstance().RemoveById(EntityContainer.GetInstance().GetEntityByObject(this));
        }
        target = ai.Attack();
        if(target != null)
        {
            if((target.GetX() + 512) < GetX())
            {
                x -= 2 * ((double)dt / 10.0);
            }
            else
            {
                x += 2 * ((double)dt / 10.0);
            }
        }
    }

    @Override
    public void Draw(RenderTarget rt) {
        int realy = Game.GetInstance().height - y - 59 - sprite.getTexture().getSize().y;
        sprite.move(x, realy);
        sprite.setColor(new Color(255, 255, 255, alpha));
        Game.GetInstance().Draw(sprite);
        sprite.move(-x, -realy);
    }

    @Override
    public void Event(org.jsfml.window.event.Event e) {
    }

    @Override
    public int GetX() {
        return x;
    }

    @Override
    public int GetY() {
        return Game.GetInstance().height - y - 59 - sprite.getTexture().getSize().y;
    }

    @Override
    public int GetW() {
        return sprite.getTexture().getSize().x;
    }

    @Override
    public int GetH() {
        return sprite.getTexture().getSize().y;
    }
    
}
