package com.easimer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

/**
 *
 * @author easimer
 */
public class PlayerShield implements Entity {

    Sprite left, right, current;
    
    int x, y;
    
    @Override
    public void Init() {
        left = new Sprite();
        right = new Sprite();
        try
        {
            Texture t = new Texture();
            t.loadFromFile(Paths.get("data/shield_left.png"));
            left.setTexture(t);
            left.setScale(2, 2);
        } catch (IOException ex) {
            Logger.getLogger(PlayerShield.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            Texture t = new Texture();
            t.loadFromFile(Paths.get("data/shield_right.png"));
            right.setTexture(t);
            right.setScale(2, 2);
        } catch (IOException ex) {
            Logger.getLogger(PlayerShield.class.getName()).log(Level.SEVERE, null, ex);
        }
        current = left;
    }

    @Override
    public void Update(long dt) {
        
    }

    @Override
    public void Draw(RenderTarget rt) {
        current.move(x, GetY());
        Game.GetInstance().Draw(current);
        current.move(-x, -GetY());
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
        return Game.GetInstance().height - 59 - current.getTexture().getSize().y * 2 - y;
    }

    @Override
    public int GetW() {
        return 64;
    }

    @Override
    public int GetH() {
        return 128;
    }
    
    public void PlayerMove(int x, int y, boolean dir) {
        this.x = x;
        this.y = y;
        current = (dir) ? right : left;
    }
}
