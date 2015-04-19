package com.easimer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;

/**
 *
 * @author easimer
 */
public class DebugText implements Entity {
    Font f;
    String text = "";
    @Override
    public void Init() {
        f= new Font();
        try {
            f.loadFromFile(Paths.get("data/UbuntuMono-R.ttf"));
        } catch (IOException ex) {
            Logger.getLogger(DebugText.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void Update(long dt) {
        Player p = Game.GetInstance().player;
        long ecc = EntityContainer.GetInstance().GetEntityList().size();
        text = String.format("Player\n  x:%d\n  y:%d\n  velx:%s\n  vely:%s\n  mp:%s\n\nEntityContainer\n  Count:%d\nFPS:%d\n", p.GetX(), p.GetY(), p.GetVelX(), p.GetVelY(), p.GetMP(), ecc, (dt < 1) ? 0 : 1000 / dt);
    }

    @Override
    public void Draw(RenderTarget rt) {
        Text t = new Text(text, f, 16);
        t.setColor(Color.BLACK);
        rt.draw(t);
    }

    @Override
    public void Event(org.jsfml.window.event.Event e) {
    }

    @Override
    public int GetX() {
        return 0;
    }

    @Override
    public int GetY() {
        return 0;
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
