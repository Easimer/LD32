package com.easimer;

/**
 *
 * @author easimer
 */
public interface AI {
    public void Think(double dt, Entity e);
    public Entity Attack();
}
