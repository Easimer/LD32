package com.easimer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.RenderTarget;

/**
 *
 * @author easimer
 */
public class SoundContainer implements Entity {
    private HashMap<String, SoundBuffer> sbs;
    public String LoadSound(String fn)
    {
        return LoadSound(fn, Paths.get(fn).getFileName().toString());
    }
    
    public String LoadSound(String fn, String name)
    {
        System.out.printf("Loading sound \"%s\" with name %s\n", fn, name);
        SoundBuffer sb = new SoundBuffer();
        try
        {
            sb.loadFromFile(Paths.get(fn));
            sbs.put(name, sb);
        } catch (IOException ex) {
            Logger.getLogger(SoundContainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
    public SoundBuffer GetSoundBufferByString(String name)
    {
        if(sbs.containsKey(name))
        {
            return sbs.get(name);
        }
        return null;
    }

    @Override
    public void Init() {
        sbs = new HashMap<>();
    }

    @Override
    public void Update(long dt) {
    }

    @Override
    public void Draw(RenderTarget rt) {
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
