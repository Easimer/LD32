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
public class MonsterMaker implements Entity {
    Sprite sprite;
    int x, y;
    
    long timeSinceLast = 0;
    int lim = -1;
    
    public MonsterMaker()
    {
        this(-1);
    }
    
    public MonsterMaker(int limit)
    {
        lim = limit;
    }
    
    @Override
    public void Init() {
        Texture t = new Texture();
        try
        {
            t.loadFromFile(Paths.get("data/monstermaker.png"));
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
        timeSinceLast += dt;
        if(Game.GetInstance().GetRandom().nextInt() % 32 == 0 && timeSinceLast > Game.GetInstance().GetRandom().nextInt(1000) + 250 && (lim > 0 || lim == -1))
        {
            int randx = (Game.GetInstance().GetRandom().nextBoolean()) ? 0 : 1024;
            timeSinceLast = 0;
            Monster m = new Monster();
            switch(Game.GetInstance().GetRandom().nextInt(2))
            {
                case 0:
                    m = new Zombie(randx, 32);
                    break;
                case 1:
                    //m = new DamnedOne(randx, 32);
                    break;
                default:
                    break;
            }
            EntityContainer.GetInstance().AddEntity(m);
            if(lim > 0)
                lim--;
        }
    }

    @Override
    public void Draw(RenderTarget rt) {
        /*int realy = Game.GetInstance().height - y - 59 - sprite.getTexture().getSize().y;
        sprite.move(x, realy);
        Game.GetInstance().Draw(sprite);
        sprite.move(-x, -realy);*/
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
        return sprite.getTexture().getSize().x;
    }
    
}
