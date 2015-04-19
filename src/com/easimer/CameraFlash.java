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
public class CameraFlash implements Entity {
    long timeAlive = 0;
    Sprite current, left, right;
    int x = 0;
    int y = 0;
    int alpha = 255;
    boolean dir = false;
    public CameraFlash(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void Init() {
        if(left == null)
        {
            Texture t = new Texture();
            try {
                t.loadFromFile(Paths.get("data/flash_left.png"));
            } catch (IOException ex) {
                Logger.getLogger(CameraFlash.class.getName()).log(Level.SEVERE, null, ex);
            }
            left = new Sprite();
            left.setTexture(t);
            left.setScale(2, 2);
        }
        if(right == null)
        {
            Texture t = new Texture();
            try {
                t.loadFromFile(Paths.get("data/flash_right.png"));
            } catch (IOException ex) {
                Logger.getLogger(CameraFlash.class.getName()).log(Level.SEVERE, null, ex);
            }
            right = new Sprite();
            right.setTexture(t);
            right.setScale(2, 2);
        }
        current = left;
    }

    @Override
    public void Update(long dt) {
        current = (dir) ? right : left;
        timeAlive += dt;
        if(alpha > 0)
        {
            alpha -= dt * 2;
            if(alpha < 0)
            {
                alpha = 0;
                timeAlive = 1001;
            }
        }
        if(timeAlive > 1000)
        {
            EntityContainer.GetInstance().RemoveById(EntityContainer.GetInstance().GetEntityByObject(this)); //kill myself
        }
    }

    @Override
    public void Draw(RenderTarget rt) {
        int nx = (dir) ? Game.GetInstance().width / 2 + current.getTexture().getSize().x : Game.GetInstance().width / 2 - current.getTexture().getSize().x;
        int ny = Game.GetInstance().player.GetY();
        current.move(nx, ny);
        current.setColor(new Color(255, 255, 255, alpha));
        rt.draw(current);
        current.move(-nx, -ny);
    }

    @Override
    public void Event(org.jsfml.window.event.Event e) {
        
    }

    @Override
    public int GetX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetW() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetH() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void SetDir(boolean dir)
    {
        this.dir = dir;
    }
}
