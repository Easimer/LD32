package com.easimer;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.window.event.Event;

/**
 *
 * @author easimer
 */
public interface Entity {
    public void Init();
    public void Update(long dt);
    public void Draw(RenderTarget rt);
    public void Event(Event e);
    public int GetX();
    public int GetY();
    public int GetW();
    public int GetH();
}
