package com.easimer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.window.event.Event;

/**
 *
 * @author easimer
 */
public class Player implements Entity {

    int x, y;
    double vel_x, vel_y;
    boolean jumping, double_jumped;
    //boolean left, right;
    
    private int magic = 100;
    private int maxmagic = 100;
    
    private int hp = 100;
    private int maxhp = 100;
    private long flashTime = 0;
    
    private PlayerShield shield;
    
    Sprite left, right, flash_left, flash_right, current;
    
    @Override
    public int GetX()
    {
        return x;
    }
    
    @Override
    public int GetY()
    {
        return Game.GetInstance().height - 59 - current.getTexture().getSize().y * 3;
    }
    
    public double GetVelX()
    {
        return vel_x;
    }
    
    public double GetVelY()
    {
        return vel_y;
    }
    
    @Override
    public void Init() {
        if(shield == null)
        {
            shield = new PlayerShield();
            shield.Init();
        }
        if(left == null)
        {
            Texture t = new Texture();
            left = new Sprite();
            try {
                t.loadFromFile(Paths.get("data/camera_left.png"));
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            left.setTexture(t);
            left.scale(3.0f, 3.0f);
        }
        if(right == null)
        {
            Texture t = new Texture();
            right = new Sprite();
            try {
                t.loadFromFile(Paths.get("data/camera_right.png"));
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            right.setTexture(t);
            right.scale(3.0f, 3.0f);
        }
        if(flash_left == null)
        {
            Texture t = new Texture();
            flash_left = new Sprite();
            try {
                t.loadFromFile(Paths.get("data/camera_flash_left.png"));
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            flash_left.setTexture(t);
            flash_left.scale(3.0f, 3.0f);
        }
        if(flash_right == null)
        {
            Texture t = new Texture();
            flash_right = new Sprite();
            try {
                t.loadFromFile(Paths.get("data/camera_flash_right.png"));
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
            flash_right.setTexture(t);
            flash_right.scale(3.0f, 3.0f);
        }
        current = left;
        x = 0;
        y = 0;
    }

    @Override
    public void Update(long dt) {
        if(current == flash_left || current == flash_right)
        {
            flashTime += dt;
            for(String entid : EntityContainer.GetInstance().GetEntityList())
            {
                    Entity e = EntityContainer.GetInstance().GetEntityByString(entid);
                    if(!Monster.class.isInstance(e))
                    {
                        continue;
                    }
                    Monster m = (Monster)e;
                    if(current == flash_left)
                    {
                        if(CollisionCheck(m.GetX(), m.GetY(), m.GetW(), m.GetH(), GetX() - GetW() / 2, GetY(), GetW(), GetH()))
                        {
                            m.GetDamage(100);
                        }
                    }
                    if(current == flash_right)
                    {
                        if(CollisionCheck(m.GetX(), m.GetY(), m.GetW(), m.GetH(), GetX() + GetW() / 2, GetY(), GetW(), GetH()))
                        {
                            m.GetDamage(100);
                        }
                    }
                    
            }
        }
        if(current == flash_left && flashTime > 500)
        {
            current = left;
            flashTime = 0;
        }
        if(current == flash_right && flashTime > 500)
        {
            current = left;
            flashTime = 0;
        }
        if(magic < 0)
            magic = 0;
        if(magic < maxmagic)
        {
            magic += 1.0 * (dt / 10);
            if(magic > maxmagic)
                magic = maxmagic;
        }
        x += vel_x * ((double)dt / 10.0);
        y += vel_y * (dt / 10);
        if(jumping)
        {
            vel_y += -1.0 * ((double)dt / 10.0);
        }
        
        if(y <= 0)
        {
            jumping = false;
            vel_y = 0;
            double_jumped = false;
            if(y < 0)
                y = 0;
        }
        //speed decay
        /*if(vel_x > 0)
            vel_x -= 0.5;
        if(vel_x < 0)
            vel_x += 0.5;
        if(vel_y > 0)
            vel_y -= 0.01;
        if(vel_y < 0)
            vel_y += 0.01;
        if(left && vel_x > -7.5)
            vel_x -= 1.0;
        if(right && vel_x < 7.5)
            vel_x += 1.0;*/
        //((Background)EntityContainer.GetInstance().GetEntityByString("background")).PlayerMove(x, y);
        Game.GetInstance().PlayerMove(x, y);
        shield.Update(dt);
        //shield.PlayerMove(Game.GetInstance().width / 2, y, (sprite_current != sprite_left));
    }

    @Override
    public void Draw(RenderTarget rt) {
        //y is the distance from the floor
        int realy = Game.GetInstance().height - 59 - current.getTexture().getSize().y * 3;
        current.move(Game.GetInstance().width / 2, realy - 108);
        rt.draw(current);
        current.move(-(Game.GetInstance().width / 2), -(realy - 108));
        shield.Draw(rt);
    }

    @Override
    public void Event(org.jsfml.window.event.Event e) {
        if(e.type == Event.Type.KEY_PRESSED)
        {
            switch(e.asKeyEvent().key)
            {
                case D:
                    //right = true;
                    if(current == left)
                        current = right;
                    if(current == flash_left)
                        current = flash_right;
                    break;
                case A:
                    //left = true;
                    if(current == right)
                        current = left;
                    if(current == flash_right)
                        current = flash_left;
                    break;
                //case SPACE:
                case W:
                    //if(vel_y == 0)
                    if(false)
                    {
                        jumping = true;
                        vel_y = 25.0;
                    }
                    //else
                    if(false)
                    {
                        if(!double_jumped)
                        {
                            vel_y += 20.0;
                            double_jumped = true;
                        }
                    }
                    break;
                case S:
                    break;
                case LEFT:
                    /*CactusProjectile prjl = new CactusProjectile();
                    EntityContainer.GetInstance().AddEntity(prjl);
                    prjl.Shoot(x, y, vel_x, vel_y, false);
                    sprite_current = sprite_left;*/
                    if(current == left && magic >= 10)
                    {
                        current = flash_left;
                        magic -= 50;
                    }
                    else
                    {
                        current = left;
                    }
                    break;
                case RIGHT:
                    /*CactusProjectile prjr = new CactusProjectile();
                    EntityContainer.GetInstance().AddEntity(prjr);
                    prjr.Shoot(x, y, vel_x, vel_y, true);
                    sprite_current = sprite_right;*/
                    if(current == right && magic >= 10)
                    {
                        current = flash_right;
                        magic -= 50;
                    }
                    else
                    {
                        current = right;
                    }
                    break;
            }
        }
        if(e.type == Event.Type.KEY_RELEASED)
        {
            switch(e.asKeyEvent().key)
            {
                case D:
                    //right_dir = false;
                    break;
                case A:
                    //left_dir = false;
                    break;
                case S:
                    vel_y -= vel_y / 2.0;
                    break;
            }
        }
    }

    @Override
    public int GetW() {
        return current.getTexture().getSize().x;
    }

    @Override
    public int GetH() {
        return current.getTexture().getSize().y;
    }
    
    public int GetHealth()
    {
        return hp;
    }
    
    private void SetHealth(int newhp)
    {
        hp = newhp;
    }
    
    public int GetMaxHealth()
    {
        return maxhp;
    }
    
    public void SetMaxHealth(int newmhp)
    {
        maxhp = newmhp;
    }
    
    public void GetDamage(int dmg)
    {
        hp -= dmg;
    }
    
    public int GetMP()
    {
        return magic;
    }
    
    public boolean CollisionCheck(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2)
    {
        return (x1 + w1) >= x2 && x1 <= x2 + w2 && y1 + h1 >= y2 && y1 <= y2 + h2;
    }
}
