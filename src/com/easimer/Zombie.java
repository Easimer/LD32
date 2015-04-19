package com.easimer;

import java.nio.file.Paths;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

/**
 *
 * @author easimer
 */
public class Zombie extends Monster {
    Sprite current;
    int alpha = 0;

    Zombie(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    @Override
    public void Init() {
        ai = new BadAI();
        current = new Sprite();
        try
        {
            Texture t = new Texture();
            t.loadFromFile(Paths.get("data/zombie.png"));
            current.setTexture(t);
            current.scale(2, 2);
        } catch (Exception ex) { System.out.println("fuck");}
    }
    Entity target;
    @Override
    public void Update(long dt)
    {
        if(alpha < 128)
            alpha++;
        if(GetHealth() <= 0)
        {
            dead = true;
            EntityContainer.GetInstance().RemoveById(EntityContainer.GetInstance().GetEntityByObject(this));
        }
        if(y > 0)
        {
            y--;
        }
        ai.Think(dt, this);
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
    
    public void Draw(RenderTarget rt) {
        int realy = Game.GetInstance().height - y - 59 - current.getTexture().getSize().y * 2;
        current.move(x, realy);
        current.setColor(new Color(255, 255, 255, alpha));
        //RectangleShape rs = new RectangleShape();
        //rs.setPosition(x, realy);
        //rs.setSize(new Vector2f(GetW(), GetH()));
        //rs.setFillColor(Color.RED);
        Game.GetInstance().Draw(current);
        //Game.GetInstance().Draw(rs);
        current.move(-x, -realy);
    }
    
    @Override
    public int GetX()
    {
        return x;
    }
    
    @Override
    public int GetY()
    {
        return Game.GetInstance().height - y - 59 - current.getTexture().getSize().y * 2;
    }
    
    @Override
    public int GetW()
    {
        return current.getTexture().getSize().x;
    }
    
    @Override
    public int GetH()
    {
        return current.getTexture().getSize().y;
    }
}
